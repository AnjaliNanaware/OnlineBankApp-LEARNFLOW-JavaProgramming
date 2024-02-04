package com.dao;

import java.sql.Connection;
import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter;  
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.model.Login;
import com.model.TransactionHistory;

public class BankServices {
	Scanner sc=new Scanner(System.in);
	Myconnection m=new Myconnection();
	PreparedStatement pstate;
	
	int ch,i;
	public void LoginUI(List<Login> ls)
	{	
		Connection con=m.getConnection();
		
		Login lobj=ls.get(0);
		System.out.println("-----------------------------------------------------------------");
		System.out.println("\t\t Welcome "+ lobj.getUsername());
		System.out.println("-----------------------------------------------------------------");
		do {
			System.out.println("\n1. Credit \n2. Debit \n3.Money Transfer \n4.Show Balance \n5.Transaction History \n6.Log out  ");
			System.out.println("Enter your choice: ");
			ch=sc.nextInt();
		
		
			switch(ch)
			{
			case 1: System.out.println("Enter Amount you want to deposit: ");
					double creditamt= sc.nextDouble();
					
					try {
						pstate = con.prepareStatement("update Bankcust set accbal=accbal+? where username=?");
						pstate.setDouble(1, creditamt);
						pstate.setString(2,lobj.getUsername());
						i=pstate.executeUpdate();
						if(i>0) {
							System.out.println("Amount credited successfully ");
							TransactionHistory th=new TransactionHistory(lobj.getUsername(),"Credit",creditamt, java.time.LocalDate.now());
							List<TransactionHistory> l=new ArrayList<TransactionHistory>();
							l.add(th);
							TransactionDAO td=new TransactionDAO();
							td.Save(l);
						}
						else {
							System.out.println("Transaction failed ");
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					break;
					
					
			case 2: System.out.println("Enter amount you want to debit : ");
					double debitamt=sc.nextDouble();
					
					try {
						pstate=con.prepareStatement("Update Bankcust set accbal=accbal-? where username=?");
					pstate.setDouble(1, debitamt);
					pstate.setString(2, lobj.getUsername());
						
						ResultSet rs=pstate.executeQuery();
						if(rs.next()) {
							System.out.println(" Amount Debited successfully ");
							TransactionHistory th=new TransactionHistory(lobj.getUsername(),"Debit",debitamt, java.time.LocalDate.now());
							List<TransactionHistory> l=new ArrayList<TransactionHistory>();
							l.add(th);
							TransactionDAO td=new TransactionDAO();
							td.Save(l);
						}
						else {
							System.out.println("Transaction failed ");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
					break;
					
			case 3: System.out.println("Enter username of receiver : ");
					String username=sc.next();
					System.out.println("Enter Amount : ");
					double amt=sc.nextDouble();
					try {
						pstate=con.prepareStatement("Update Bankcust set accbal=accbal-? where username=?");
						pstate.setDouble(1,amt);
						pstate.setString(2, lobj.getUsername());
						
						PreparedStatement pstate2=con.prepareStatement("Update Bankcust set accbal=accbal+? where username=?");
						pstate2.setDouble(1,amt);
						pstate2.setString(2, username);
						ResultSet rs1=pstate.executeQuery();
						ResultSet rs2=pstate2.executeQuery();
						if(rs1.next() && rs2.next()) {
						
							System.out.println(" Transaction Successful  ");
							TransactionHistory th=new TransactionHistory(lobj.getUsername(),"Debit",amt, java.time.LocalDate.now());
							List<TransactionHistory> l=new ArrayList<TransactionHistory>();
							l.add(th);
							TransactionDAO td=new TransactionDAO();
							td.Save(l);
							
							TransactionHistory th2=new TransactionHistory(username,"Credit",amt, java.time.LocalDate.now());
							List<TransactionHistory> l2=new ArrayList<TransactionHistory>();
							l2.add(th2);
							TransactionDAO td2=new TransactionDAO();
							td2.Save(l2);
							
							
						}
						else {
							System.out.println("Transaction failed ");
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
					}
					break;
							
			case 4: 
					try {
						pstate=con.prepareStatement("Select accbal from Bankcust where username=?");
						pstate.setString(1, lobj.getUsername());
						
						ResultSet rs=pstate.executeQuery();
						if(rs.next()) {
							System.out.println("Your current balance is : "+rs.getDouble(1));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
					
					
			case 5: try {
						pstate=con.prepareStatement("Select trantype,amount,tdate from transaction where username=?");
						pstate.setString(1,lobj.getUsername());
						ResultSet rs=pstate.executeQuery();
						
						System.out.println("----------------------------------------------------------------");
						System.out.println("Transaction Type  Amount\t\tDate");
						System.out.println("----------------------------------------------------------------");
						while(rs.next())
						{
							System.out.println(rs.getString(1)+"\t\t"+rs.getDouble(2)+"\t\t"+rs.getString(3));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
					
			case 6: System.out.println("Log out successfully");
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
			}
		}while(ch!=6);
		
		
		
	}
}
