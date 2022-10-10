package com.perfios.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.perfios.dao.LoanDao;
import com.perfios.dao.TransactionsDao;
import com.perfios.dao.UsersDao;
import com.perfios.pojo.Loan;
import com.perfios.pojo.Transactions;
import com.perfios.pojo.Users;

class TestCases {

	LoanDao loan=new LoanDao();
	Loan obj1;
	@Test
    public void insertLoan() throws Exception{
		obj1=new Loan(100,750,25,75000.0,800.0,"SDE","Tesdt1","test100",6,"pending");
        assertEquals(1, loan.insertLoan(obj1));
    }
	
	
	
	@Test
    public void deleteLoan() throws Exception{
        assertEquals(true, loan.deleteLoan(6));
    }
	
	
	@Test
	public void changeStatus()throws Exception{
		assertEquals(true,loan.changeStatus(1));
	}
	@Test
	public void selectAllLoans() throws Exception{
		
		assertNotNull( loan.selectAllLoans());
	}
	TransactionsDao transactions=new TransactionsDao();
	long millis=System.currentTimeMillis();  
    java.sql.Date date=new java.sql.Date(millis);  
	Transactions transaction=new Transactions(1,2,3,2000.0,date,"UPI");
	@Test
	public void insertTransaction()throws Exception{
		assertEquals(1,transactions.insertTransaction(transaction));
	}
	@Test
	public void deletetransaction()throws Exception{
		assertEquals(true,transactions.deletetransaction(7));
	}
	@Test
	public void selectAllTransactions() throws Exception{
		
		assertNotNull( transactions.selectAllTransactions());
	}
	
	UsersDao user=new UsersDao();
	Users obj2=new Users(100,"Test200",89000,"test100@gmail.com","DPUPR5604K","123","6290527295","Bangalore");
	@Test
	public void insertUser()throws Exception{
		assertEquals(1,user.insertUser(obj2));
	}
	@Test
	public void deleteUser()throws Exception{
		assertEquals(true,user.deleteUser(8));
}
	@Test
	public void changePassword()throws Exception{
		assertEquals(true,user.changePassword(2, "1234"));
	}
	
	@Test
	public void getBalance()throws Exception{
		assertEquals(9058,user.getBalance(2));
	}
	@Test
	public void selectAllUsers() throws Exception{
		
		assertNotNull( user.selectAllUsers());
	}
}
