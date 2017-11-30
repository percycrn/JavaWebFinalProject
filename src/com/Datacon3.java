package com;

import java.io.IOException;
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

/**
 * Servlet implementation class com.Datacon3
 */
@WebServlet("/com.Datacon3")
public class Datacon3 extends HttpServlet {
	private static final long serialVersionUID = 3L;
      /**
     * @see HttpServlet#HttpServlet()
     */
	DataSource datasource;
    public Datacon3() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init()
    {
    	try {
    		
    		Context context =new InitialContext();
    		datasource = (DataSource)context.lookup("java:comp/env/jdbc/sampleDS");
    	}
    	catch(NamingException ne){
    		System.out.println("�쳣��"+ne);
    	}
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//JDBC��Э�顢���������˿ںš���Ҫ���ӵ����ݿ�
	    String url="jdbc:mysql://localhost:3306/mytest";
	    //��¼MySQL���ݿ���û���
		String user="root";
		
		//��¼����
		String passwd="web2013";
		String sql1="SELECT * FROM student";
        String sql2="INSERT INTO student(id,stuname,classid) VALUES(21,'wang',1)";
        String sq23="UPDATE studeng SET stuname='wang' WHERE id=6";
        String sql3="DELETE FROM student WHERE id=6";
			
		
        
		try {
			 System.out.println("Ԥ������ʼ11");
			//ָ��MySQL����
			//Class.forName("com.mysql.jdbc.Driver");
		    //������MySQL������
		   // Connection con = DriverManager.getConnection(url, user, passwd);
			Connection con=datasource.getConnection();
			 
			 System.out.println("�������ݿ�������ɹ�");
			//����Statement����
			Statement stat=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			//ִ��SELECT���
			ResultSet rs=stat.executeQuery(sql1);
			//��˳���ȡ������е�ÿһ����¼
			rs.first();
			rs.relative(3);
			int id=rs.getInt("id");
			int classid=rs.getInt("classid");
			String name=rs.getString("stuname");
		
			rs.updateString("stuname", "wang6");
			rs.updateRow();
			System.out.println(id+"  "+name+"  "+classid);
                         System.out.println("��ѯִ�����");
			//ִ��INSERT���
			//int i=stat.executeUpdate(sql2);
			//if(i!=0){
			//	System.out.println("INSERT���ִ�гɹ�");
		//	}
			//ִ��DELETE���
			//int j=stat.executeUpdate(sql3);
			//if(j!=0){
			//	System.out.println("DELETE���ִ�гɹ�");
			//}
			//ִ��UPDATE���
			//int K=stat.executeUpdate(sq23);
			//if(K!=0){
			//	System.out.println("UPDATE���ִ�гɹ�");
			//}

		}  catch (SQLException e) {
			System.out.print("�������ݿ�����������쳣");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
