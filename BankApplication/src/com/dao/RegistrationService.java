package com.dao;

import java.util.List;

import com.model.Login;
import com.model.Register;



public interface RegistrationService {
	public int Save(List<Register> lst);
//	public int deleteRecord(int n);
//	
//	public int updateRecord(List<Register> lst);
//	public List<Register> searchRecord(int n);
//	public List<Register> displayAll();
	
	public boolean validate(List<Login> lst);
}
