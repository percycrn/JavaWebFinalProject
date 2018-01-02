package com.servlet;

import com.ConnDB;
import com.data_structure.Orders;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet(name = "/ReceptionistServlet")
public class ReceptionistServlet extends HttpServlet {
    private ConnDB connDB = new ConnDB();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        switch (request.getParameter("action")) {
            case "signin":
                String receptionist_no = request.getParameter("receptionist_no");
                String reception_password = request.getParameter("password");
                response.getWriter().write(connDB.receptionistSignIn(receptionist_no, reception_password));
                break;
            case "query":
                ArrayList<Orders> orders = connDB.getOrdersByIdCard(request.getParameter("idCard"));
                break;
            case "stayover":
                if (connDB.stayOver(Date.valueOf(request.getParameter("nextDate")),
                        request.getParameter("idCard"))) {
                    System.out.println("success to stay over");
                }
                break;
            case "changeroom":
                response.getWriter().write(connDB.changeRoom(request.getParameter("idCard")));
                break;
            case "checkout":
                if (connDB.checkout(request.getParameter("idCard"))) {
                    System.out.println("success to checkout");
                }
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
