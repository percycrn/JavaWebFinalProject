package com;

import com.data_structure.MonthReport;
import com.data_structure.Orders;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnDB connDB = new ConnDB();
        Orders orders = new Orders();
        orders.setType("双人海景房");
        orders.setCustomer_idCard("44444444444444444");
        orders.setHotel_no("CHARLOTTE1");
        orders.setCustomerNum(2);
        orders.setRoomNum(1);
        orders.setStartDate(Date.valueOf("2020-02-02"));
        orders.setEndDate(Date.valueOf("2020-02-03"));
        connDB.addNewOrder(orders);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
