package com.perfios.pojo;

public class Users {
	private int id;
	private String uname;
	private double balance;
	private String email;
	private String pan;
	private String pwd;
	private String mobile;
	private String address;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Users(int id, String uname, double balance, String email, String pan, String pwd, String mobile,
			String address) {
		super();
		this.id = id;
		this.uname = uname;
		this.balance = balance;
		this.email = email;
		this.pan = pan;
		this.pwd = pwd;
		this.mobile = mobile;
		this.address = address;
	}
	
	
}
