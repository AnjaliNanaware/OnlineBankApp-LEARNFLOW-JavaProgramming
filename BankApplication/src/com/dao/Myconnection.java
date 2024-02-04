package com.dao;

import java.sql.*;

public class Myconnection {
	
		public  Connection getConnection() {
			
			Connection con=null;
			String driver="oracle.jdbc.OracleDriver";
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			String userName="system";
			String password="9876";
			
			try {
				Class.forName(driver);
				con=DriverManager.getConnection(url,userName,password);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return con;
		}
}
