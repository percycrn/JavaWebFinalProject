package com;

import java.sql.*;

public class ConnDB {
    private Connection connection;
    private Statement statement;

    public ConnDB() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=JavaWebDB;", "JavaWeb", "1234");
        statement = connection.createStatement();
    }

    public boolean accountExist(String teleNumber) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT TELENUMBER FROM CUSTOMER WHERE TELENUMBER='" + teleNumber + "'");
        resultSet.next();
        return resultSet.getString("TELENUMBER").trim().equals(teleNumber);
    }

    public String getPassword(String teleNumber) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT PASSWORD FROM CUSTOMER WHERE TELENUMBER='" + teleNumber + "'");
        resultSet.next();
        return resultSet.getString("PASSWORD").trim();
    }
}



/*
        private DataSource dataSource;
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/JavaWebDB");
        } catch (NamingException e) {
            System.out.println("Exception: " + e);
        }
        Connection conn = dataSource.getConnection();
         */