package com.login;

import com.ConnDB;

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
                    String teleNumber = request.getParameter("teleNumber");
                    String password = request.getParameter("password");
                    System.out.println(teleNumber);
                    System.out.println(password);
                    if (!connDB.accountExist(teleNumber)) {
                        System.out.println("account doesn't exist");
                    } else if (!connDB.getPassword(teleNumber).equals(password)) {
                        System.out.println("incorrect password");
                    } else {
                        System.out.println("success to login");
                    }
                    break;
                case "register":
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
