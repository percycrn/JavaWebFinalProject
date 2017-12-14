package com.login;

import com.ConnDB;
import com.Customer;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends javax.servlet.http.HttpServlet {

    private ConnDB connDB;

    @Override
    public void init() {
        connDB = new ConnDB();
        try {
            connDB.setConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String action = request.getParameter("@action@");
        request.setCharacterEncoding("UTF-8");
        System.out.println(action);
        try {
            switch (action) {
                case "login":
                    String teleNumberL = request.getParameter("teleNumber");
                    String passwordL = request.getParameter("password");
                    System.out.println(teleNumberL);
                    System.out.println(passwordL);
                    if (!connDB.accountExist(teleNumberL)) {
                        System.out.println("account doesn't exist");
                    } else if (!connDB.getPassword(teleNumberL).equals(passwordL)) {
                        System.out.println("incorrect password");
                    } else {
                        System.out.println("success to login");
                    }
                    break;
                case "register":
                    if (connDB.accountExist(request.getParameter("teleNumber"))){
                        System.out.println("account already exist");
                        return;
                    }
                    Customer customer = new Customer();
                    customer.setIdCard(request.getParameter("idCard"));
                    customer.setName(request.getParameter("name"));
                    customer.setTeleNumber(request.getParameter("teleNumber"));
                    customer.setEmail(request.getParameter("email"));
                    customer.setPassword(request.getParameter("password"));
                    connDB.addNewAccount(customer);
                    break;
                case "query":
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
