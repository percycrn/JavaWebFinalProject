package com;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import javax.naming.*;

@WebServlet("/ConnDB3")
public class ConnDB3 extends HttpServlet {
    private DataSource datasource;

    @Override
    public void init() throws ServletException {
        try {
            Context context = new InitialContext();
            datasource = (DataSource) context.lookup("java:comp/env/jdbc/sampleDS");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql1 = "SELECT * FROM student";
        // String sql2 = "INSERT INTO student VALUES('小放',121,1512480439,'能动楼')";

        try {
            Connection con = datasource.getConnection();
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stat.executeQuery(sql1);
            rs.first();
            StudentTuple studentTuple = new StudentTuple();
            studentTuple.setName(rs.getString("name"));
            studentTuple.setPassword(rs.getInt("password"));
            studentTuple.setId(rs.getInt("id"));
            studentTuple.setAddress(rs.getString("address"));
            // rs.updateString("name", "小王");
            // rs.updateRow();
            request.getSession().setAttribute("studentTuple", studentTuple);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/displayCustomer.jsp");
            requestDispatcher.forward(request, response);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
