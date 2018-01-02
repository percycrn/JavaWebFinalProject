package com.servlet;

import com.ConnDB;
import com.data_structure.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CustomerSignInServlet")
public class CustomerSignInServlet extends javax.servlet.http.HttpServlet {

    private ConnDB connDB = new ConnDB();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String customer_telNumber = request.getParameter("telNo");
        String customer_password = request.getParameter("password");
        String action = request.getParameter("action");
        System.out.println(customer_telNumber);
        System.out.println(customer_password);
        System.out.println(action);
        switch (action) {
            case "signin":
                if (!connDB.accountExist(customer_telNumber)) {
                    response.setContentType("text/plain;charset=UTF-8");
                    response.getWriter().write("AccountNotFind");
                    System.out.println("account doesn't exist");
                } else if (!connDB.getPassword(customer_telNumber).equals(customer_password)) {
                    System.out.println("incorrect password");
                    response.getWriter().write("IncorrectPassword");
                } else {
                    System.out.println("success to login");
                    response.getWriter().write("SuccessSignIn");
                }
                break;
            case "signup":
                if (connDB.accountExist(customer_telNumber)) {
                    System.out.println("account already exist");
                    return;
                }
                Customer customer = new Customer();
                customer.setCustomer_idCard(request.getParameter("customer_idCard"));
                customer.setCustomer_teleNumber(customer_telNumber);
                customer.setCustomer_name(request.getParameter("customer_name"));
                customer.setCustomer_password(customer_password);
                customer.setCustomer_occupation(request.getParameter("customer_occupation"));
                if (connDB.addNewCustomer(customer)) {
                    System.out.println("success to add new customer");
                } else {
                    System.out.println("fail to add new customer");
                }
                // TODO signin automatically or turn to signin web
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
