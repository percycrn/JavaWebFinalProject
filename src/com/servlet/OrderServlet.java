package com.servlet;

import com.ConnDB;
import com.data_structure.Orders;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "OrderServlet")
public class OrderServlet extends HttpServlet {
    private ConnDB connDB = new ConnDB();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Orders orders = new Orders();
        orders.setType(request.getParameter("type"));
        Cookie[] cookie = request.getCookies();
        for (Cookie aCookie : cookie) {
            if (aCookie.getName().equals("customer_idCard")) {
                orders.setCustomer_idCard(aCookie.getValue());
                break;
            }
        }
        System.out.println(orders.getCustomer_idCard());
        orders.setHotel_no("CHARLOTTE1");
        orders.setCustomerNum(Integer.valueOf(request.getParameter("customerNum")));
        orders.setRoomNum(Integer.valueOf(request.getParameter("roomNum")));
        orders.setStartDate(Date.valueOf(request.getParameter("startDate")));
        orders.setEndDate(Date.valueOf(request.getParameter("endDate")));
        connDB.addNewOrder(orders);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
