package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.model.Login;
import com.model.Register;



public class RegistrationServiceImplDao implements RegistrationService{
	
	Myconnection m=new Myconnection();
	
	public int Save(List<Register> lst) {
		Connection con=m.getConnection();
		int i=0;
		try {
			PreparedStatement pstate= con.prepareStatement("insert into BankCust values(?,?,?,?,?)");
			Register obj=lst.get(0);
			pstate.setLong(1, obj.getAccNo());
			pstate.setString(2,obj.getCustName());
			pstate.setString(3, obj.getUserName());
			pstate.setString(4,obj.getPassword());
			pstate.setDouble(5, obj.getAccbal());
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

	@Override
	public boolean validate(List<Login> lst) {
		Connection con=m.getConnection();
		Login lobj=lst.get(0);
		boolean b=true;
		try {
			PreparedStatement pstate=con.prepareStatement("select * from BankCust where username=? and password=?");
			pstate.setString(1,lobj.getUsername());
			pstate.setString(2,lobj.getPassword());
			ResultSet rs=pstate.executeQuery();
			if(rs.next()) {
				b=true;
			}
			else {
				b=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return b;
	}
	
	public List<Register> search(int accno){
		Connection con=m.getConnection();
		List<Register> lst=null;
		try {
			PreparedStatement pstate=con.prepareStatement("Select * from BankCust where accno=?");
			pstate.setInt(1, accno);
			ResultSet rs=pstate.executeQuery();
			
			if(rs.next()) {
				lst=new ArrayList<Register>();
				int accno1=rs.getInt(1);
				String CustName=rs.getString(2);
				String UserName=rs.getString(3);
				String Password=rs.getString(4);
				double accbal=rs.getDouble(5);
				Register r=new Register(accno1, CustName, CustName, Password, accbal);
				lst.add(r);
			}
			else {
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lst;
		
	}
	
	public boolean delete(int accno) {
		
		Connection con=m.getConnection();
		int i=0;
		boolean b=false;
		try {
			PreparedStatement pstate=con.prepareStatement("delete from BankCust where accno=?");
			pstate.setInt(1, accno);
			i=pstate.executeUpdate();
			if(i>0) {
				b=true;
			}
			else {
				b=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
		
	}
	


}
