package com;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class ConnDB {
    private Connection connection;
    private Statement statement;
    private DataSource dataSource;

    public ConnDB() {
        /*try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/JavaWebDB");
        } catch (NamingException e) {
            System.out.println("Exception: " + e);
        }
    }

    public void setConnection() throws SQLException {
        /*connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=JavaWebDB;", "JavaWeb", "1234");
        statement = connection.createStatement();*/
        connection = dataSource.getConnection();
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

    public void addNewAccount(Customer customer) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO CUSTOMER VALUES(?,?,?,?,?)");
        preparedStatement.setString(1, customer.getIdCard());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getTeleNumber());
        preparedStatement.setString(4, customer.getEmail());
        preparedStatement.setString(5, customer.getPassword());
    }

    public ResultSet queryTest() throws SQLException {
        return statement.executeQuery("SELECT * FROM CUSTOMER");
    }
}





