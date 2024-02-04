package com.model;

public class Register {
	
	private int accNo;
	private String custName;
	private String userName;
	private String password;
	private double accbal;
	public Register(int accNo, String custName, String userName, String password, double accbal) {
		super();
		this.accNo = accNo;
		this.custName = custName;
		this.userName = userName;
		this.password = password;
		this.accbal = accbal;
	}
	
	public int getAccNo() {
		return accNo;
	}
	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getAccbal() {
		return accbal;
	}
	public void setAccbal(double accbal) {
		this.accbal = accbal;
	}
	
	

}
