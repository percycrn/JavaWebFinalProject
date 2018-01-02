package com;

import com.data_structure.Customer;
import com.data_structure.MonthReport;
import com.data_structure.Orders;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ConnDB {
    private DataSource dataSource;

    public ConnDB() {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/HotelSystem");
        } catch (NamingException e) {
            System.out.println("Exception: " + e);
        }
    }

    private Statement getStatement() throws SQLException {
        return dataSource.getConnection().createStatement();
    }

    public boolean accountExist(String customer_telNumber) {
        try {
            ResultSet resultSet = getStatement().executeQuery(
                    "SELECT customer_telNumber FROM CUSTOMER WHERE customer_telNumber='" + customer_telNumber + "'");
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to check account exist or not");
            return false;
        }
    }

    public String getPassword(String customer_telNumber) {
        try {
            ResultSet resultSet = getStatement().executeQuery(
                    "SELECT customer_password FROM CUSTOMER WHERE customer_telNumber='" + customer_telNumber + "'");
            resultSet.next();
            return resultSet.getString("customer_password").trim();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get password");
            return "";
        }
    }

    public boolean addNewCustomer(Customer customer) {
        try {
            getStatement().executeUpdate("insert into customer values('" + customer.getCustomer_idCard() + "','"
                    + customer.getCustomer_teleNumber() + "','" + customer.getCustomer_name() + "','"
                    + customer.getCustomer_password() + "','" + customer.getCustomer_occupation() + "')");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to add new customer");
            return false;
        }
    }

    public ResultSet queryTest() throws SQLException {
        return getStatement().executeQuery("SELECT * FROM CUSTOMER");
    }

    public boolean addNewOrder(Orders orders) {
        try {
            // get new order_no
            ResultSet resultSet = getStatement().executeQuery("select count(*) from orders");
            resultSet.next();
            int m = resultSet.getInt(1);
            // get suitable room_no
            ResultSet resultSet1 = getStatement().executeQuery("select distinct s.room_no from orders s,room r " +
                    "where s.room_no=r.room_no and r.type='" + orders.getType() + "'");
            boolean flag = false;
            while (resultSet1.next()) {
                ResultSet resultSet2 = getStatement().executeQuery("select * from orders where room_no='"
                        + resultSet1.getString("room_no") + "'");
                flag = true;
                while (resultSet2.next()) {
                    if (!(orders.getStartDate().after(resultSet2.getDate("endDate"))
                            || orders.getEndDate().before(resultSet2.getDate("startDate")))) {
                        flag = false;
                    }
                }
                // 从订单中找
                if (flag) {
                    orders.setRoom_no(resultSet1.getString("room_no"));
                    break;
                }
            }
            // 订单中找不到合适的就从库存中找
            if (!flag) {
                ResultSet resultSet2 = getStatement().executeQuery("select s.room_no from room s,room r " +
                        "where s.status = 0 and s.room_no=r.room_no and r.type='" + orders.getType() + "'");
                resultSet2.next();
                orders.setRoom_no(resultSet2.getString("room_no"));
                getStatement().executeUpdate("update room set status=1 where room_no='"
                        + resultSet2.getString("room_no") + "'");
            }
            getStatement().executeUpdate("insert into orders values('ORD" + (m + 1) + "','"
                    + orders.getRoom_no() + "','" + orders.getCustomer_idCard() + "','" + orders.getHotel_no() + "','"
                    + orders.getCustomerNum() + "','" + orders.getRoomNum() + "','" + orders.getStartDate() + "','"
                    + orders.getEndDate() + "')");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to add new order");
            return false;
        }
    }

    public String receptionistSignIn(String receptionist_no, String password) {
        try {
            ResultSet resultSet = getStatement().executeQuery(
                    "select * from receptionist where receptionist_no='" + receptionist_no + "'");
            if (!resultSet.next()) {
                return "AccountNoteExist";
            } else if (!password.equals(resultSet.getString("receptionist_password"))) {
                return "IncorrectPassword";
            } else {
                return "SuccessSignIn";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get receptionist information");
            return "";
        }
    }

    public ArrayList<Orders> getOrdersByIdCard(String customer_idCard) {
        try {
            ResultSet resultSet = getStatement().executeQuery(
                    "select * from orders where customer_idCard='" + customer_idCard + "'");
            ArrayList<Orders> orders = new ArrayList<>();
            while (resultSet.next()) {
                Orders order = new Orders();
                order.setOrder_no(resultSet.getString("order_no"));
                order.setRoom_no(resultSet.getString("room_no"));
                order.setCustomer_idCard(resultSet.getString("customer_idCard"));
                order.setHotel_no(resultSet.getString("hotel_no"));
                order.setCustomerNum(resultSet.getInt("customerNum"));
                order.setRoomNum(resultSet.getInt("roomNum"));
                order.setStartDate(resultSet.getDate("startDate"));
                order.setEndDate(resultSet.getDate("endDate"));
                ResultSet resultSet1 = getStatement().executeQuery("select type from room where room_no='"
                        + resultSet.getString("room_no") + "'");
                resultSet1.next();
                order.setType(resultSet1.getString("type"));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get orders information");
            return null;
        }
    }

    public ArrayList<Orders> getOrders() {
        try {
            ResultSet resultSet = getStatement().executeQuery(
                    "select * from orders where");
            ArrayList<Orders> orders = new ArrayList<>();
            while (resultSet.next()) {
                Orders order = new Orders();
                order.setOrder_no(resultSet.getString("order_no"));
                order.setRoom_no(resultSet.getString("room_no"));
                order.setCustomer_idCard(resultSet.getString("customer_idCard"));
                order.setHotel_no(resultSet.getString("hotel_no"));
                order.setCustomerNum(resultSet.getInt("customerNum"));
                order.setRoomNum(resultSet.getInt("roomNum"));
                order.setStartDate(resultSet.getDate("startDate"));
                order.setEndDate(resultSet.getDate("endDate"));
                ResultSet resultSet1 = getStatement().executeQuery("select type from room where room_no='"
                        + resultSet.getString("room_no") + "'");
                resultSet1.next();
                order.setType(resultSet1.getString("type"));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get orders information");
            return null;
        }
    }

    public boolean stayOver(Date nextDate, String idCard) {
        System.out.println(nextDate);
        try {
            ResultSet resultSet = getStatement().executeQuery(
                    "select * from orders where customer_idCard='" + idCard + "'");
            resultSet.next();
            getStatement().executeUpdate("update orders set endDate='" + nextDate + "' where order_no='"
                    + resultSet.getString("order_no") + "'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to stay over for customer");
            return false;
        }
    }

    public String changeRoom(String idCard) {
        try {
            ResultSet resultSet = getStatement().executeQuery(
                    "select * from orders where customer_idCard='" + idCard + "'");
            resultSet.next();
            // get type
            ResultSet resultSet1 = getStatement().executeQuery("select type from room where room_no='"
                    + resultSet.getString("room_no") + "'");
            resultSet1.next();
            // get room from specific type
            ResultSet resultSet2 = getStatement().executeQuery("select * from room where type='"
                    + resultSet1.getString("type") + "' and status=0");
            resultSet2.next();
            // update status
            getStatement().executeUpdate("update room set status=1 where room_no='"
                    + resultSet2.getString("room_no") + "'");
            // 检查该房间是否被其他订单使用
            ResultSet resultSet3 = getStatement().executeQuery(
                    "select * from orders where room_no='"
                            + resultSet.getString("room_no")
                            + "' and customer_idCard!='" + idCard + "'");
            if (!resultSet3.next()) {
                getStatement().executeUpdate("update room set status=0 where room_no='"
                        + resultSet.getString("room_no") + "'");
            }
            return resultSet2.getString("room_no");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to change room");
            return "failToChangeRoom";
        }
    }

    public boolean checkout(String idCard) {
        try {
            ResultSet resultSet = getStatement().executeQuery(
                    "select * from orders where customer_idCard='" + idCard + "'");
            resultSet.next();
            ResultSet resultSet1 = getStatement().executeQuery(
                    "select * from orders where room_no='"
                            + resultSet.getString("room_no")
                            + "' and customer_idCard!='" + idCard + "'");
            if (!resultSet1.next()) {
                getStatement().executeUpdate("update room set status=0 where room_no='"
                        + resultSet.getString("room_no") + "'");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to checkout");
            return false;
        }
    }

    public MonthReport getMonthReport() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        String currentDate = df.format(new java.util.Date());
        try {
            int customerNum = 0;
            int roomNum = 0;
            float price = 0;
            ResultSet resultSet = getStatement().executeQuery("select * from orders");
            while (resultSet.next()) {
                if (df.format(resultSet.getDate("startDate")).equals(currentDate)) {
                    int m = resultSet.getInt("roomNum");
                    customerNum += resultSet.getInt("customerNum");
                    roomNum += m;
                    ResultSet resultSet1 = getStatement().executeQuery("select price from room where room_no='"
                            + resultSet.getString("room_no") + "'");
                    resultSet1.next();
                    price += m * resultSet1.getFloat("price");
                }
            }
            MonthReport monthReport = new MonthReport();
            monthReport.setCustomerNum(customerNum);
            monthReport.setRoomNum(roomNum);
            monthReport.setPrice(price);
            return monthReport;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get order information for month report");
            return null;
        }
    }

    public boolean setRoomRepair(String room_no) {
        try {
            getStatement().executeUpdate(
                    "update room set status = 1 where room_no='" + room_no + "'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to set room repair");
            return false;
        }
    }

    public boolean setRoomAvailable(String room_no) {
        try {
            getStatement().executeUpdate(
                    "update room set status = 0 where room_no='" + room_no + "'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to set room available");
            return false;
        }
    }
}