package com.dao;

import java.sql.Connection;
import java.text.DateFormat;  
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.model.Register;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import com.model.TransactionHistory;

public class TransactionDAO {
	
	Myconnection m=new Myconnection();
	public int Save(List<TransactionHistory> lst) {
		Connection con=m.getConnection();
		int i=0;
		try {
			PreparedStatement pstate= con.prepareStatement("insert into transaction values(?,?,?,?)");
			TransactionHistory obj=lst.get(0);
			pstate.setString(1, obj.getUsername());
			pstate.setString(2,obj.getTraType());
			pstate.setDouble(3, obj.getAmount());
			pstate.setString(4,obj.getDate().toString());
			i=pstate.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		return i;
	}

}
