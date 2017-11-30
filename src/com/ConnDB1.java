package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "ConnDB1", urlPatterns = {"/connDB1.do"})
public class ConnDB1 extends HttpServlet {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = "jdbc:mysql://localhost:3306/javawebdbtest";
        String user = "root";
        String password = "699685";
        String sql1 = "SELECT * FROM student";
        String sql2 = "INSERT INTO student VALUES('小张',111,1512480433,'水母楼')";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql1);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int id = resultSet.getInt("id");
                System.out.println(id + " " + name);
            }
            statement.executeUpdate(sql2);
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