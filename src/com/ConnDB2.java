package com;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "ConnDB2", urlPatterns = {"/connDB2.do"})
public class ConnDB2 extends HttpServlet {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = "jdbc:mysql://localhost:3306/javawebdbtest";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, "root", "699685");
            Statement statement = connection.createStatement();
            //String name = new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
            request.setCharacterEncoding("UTF-8");
            String name = request.getParameter("name");
            resultSet = statement.executeQuery("SELECT * FROM student");
            System.out.println(name);
            int i = 0, j = 0;
            while (resultSet.next()) {
                i++;
                if (resultSet.getString("name").equals(name)) {
                    break;
                }
                j++;
            }
            HttpSession session = request.getSession();
            if (i != j) {
                int password = Integer.valueOf(request.getParameter("password"));
                if (password == resultSet.getInt("password")) {
                    session.setAttribute("loginMessage", "登陆成功");
                    StudentTuple studentTuple = new StudentTuple();
                    studentTuple.setName(name);
                    studentTuple.setPassword(password);
                    studentTuple.setId(resultSet.getInt("id"));
                    studentTuple.setAddress(resultSet.getString("address"));
                    session.setAttribute("studentTuple", studentTuple);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/displayCustomer.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    session.setAttribute("loginMessage", "密码不正确");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/inputCustomer.jsp");
                    requestDispatcher.forward(request, response);
                }
            } else {
                session.setAttribute("loginMessage", "账号不存在");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/inputCustomer.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet = null;
            }
            if (statement != null) {
                statement = null;
            }
            if (connection != null) {
                connection = null;
            }
        }
    }
}