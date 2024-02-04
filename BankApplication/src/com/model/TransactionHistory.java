package com.model;

import java.sql.Date;
import java.time.LocalDate;

public class TransactionHistory {

	String username;//credit or debit
	String TraType; 
	double amount;
	LocalDate date;
	
	public TransactionHistory(String username, String traType, double amount, LocalDate localDate) {
		super();
		
		this.username = username;
		TraType = traType;
		this.amount = amount;
		this.date = localDate;
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTraType() {
		return TraType;
	}
	public void setTraType(String traType) {
		TraType = traType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
}
