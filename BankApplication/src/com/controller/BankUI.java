package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dao.BankServices;
import com.dao.RegistrationServiceImplDao;
import com.model.Login;
import com.model.Register;

public class BankUI {
	public void bankui()
	{
		Scanner sc=new Scanner(System.in);
		String UserID;
		String password;
		int ch;
		String custName;
		do {
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("\t\tWelcome to Bank\t\t");
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("1.Register  \n2.Login  \n3. Exit ");
			System.out.println("Enter your choice ");
			ch=sc.nextInt();
			
			switch(ch) {
			
			case 1: 	System.out.println("Enter Account no.: ");
						int AcNo=sc.nextInt();
						sc.nextLine();
						System.out.println("Enter Customer name: ");
						custName=sc.nextLine();
						System.out.println("Enter User ID: ");
						UserID=sc.next();
						System.out.println("Enter Password: ");
						password=sc.next();
						System.out.println("Enter Account balance: ");
						double AcBal=sc.nextDouble();
	
						Register r=new Register(AcNo, custName,UserID, password,AcBal);
						RegistrationServiceImplDao rimpl = new RegistrationServiceImplDao();
						List<Register> ls=new ArrayList<Register>();
						ls.add(r);
						int i=rimpl.Save(ls);
						if(i>0) {
							
							System.out.println("You have registered successfully ");
							
						}
						else {
							System.out.println("Failed to registered  ");
						}
						
						break;
						
			case 2: 	System.out.println("Enter User ID: ");
						UserID=sc.next();
						System.out.println("Enter Password: ");
						password=sc.next();
						
						Login l=new Login(UserID,password);
						RegistrationServiceImplDao ri = new RegistrationServiceImplDao();
						List<Login> lst=new ArrayList<Login>();
						lst.add(l);
						boolean v=ri.validate(lst);
						if(v) {
							System.out.println("Login Successfully");
							BankServices bs=new BankServices();
							bs.LoginUI(lst);
						}
						else {
							System.out.println("Username or password incorrect");
						}
						
						break;
			
			case 3:   System.out.println("Thank you for using our bank service!!!!!!");
					  break;
						
			default:    System.out.println("Invalid choice");
						break;
			}
				
	 }while(ch!=3 );
	}
}
