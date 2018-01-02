package com.servlet;

import com.ConnDB;
import com.data_structure.MonthReport;
import com.data_structure.Orders;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ManagerServlet")
public class ManagerServlet extends HttpServlet {
    private ConnDB connDB = new ConnDB();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        switch (request.getParameter("action")) {
            case "CheckOrders":
                ArrayList<Orders> orders = connDB.getOrders();
                break;
            case "MonthReport":
                MonthReport monthReport = connDB.getMonthReport();
                break;
            case "repair":
                if (connDB.setRoomRepair(request.getParameter("room_no"))) {
                    System.out.println("success to set room repair");
                }
                break;
            case "available":
                if (connDB.setRoomAvailable(request.getParameter("room_no"))) {
                    System.out.println("success to set room available");
                }
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
