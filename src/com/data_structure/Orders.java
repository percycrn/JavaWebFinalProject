package com.data_structure;

import java.util.Date;

public class Orders {
    private String order_no;
    private String room_no;
    private String customer_idCard;
    private String hotel_no;
    private int customerNum;
    private int roomNum;
    private Date startDate;
    private Date endDate;
    private String type;

    public Orders() {
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getRoom_no() {
        return room_no;
    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }

    public String getCustomer_idCard() {
        return customer_idCard;
    }

    public void setCustomer_idCard(String customer_idCard) {
        this.customer_idCard = customer_idCard;
    }

    public String getHotel_no() {
        return hotel_no;
    }

    public void setHotel_no(String hotel_no) {
        this.hotel_no = hotel_no;
    }

    public int getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(int customerNum) {
        this.customerNum = customerNum;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
