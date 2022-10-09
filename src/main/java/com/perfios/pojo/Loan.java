package com.perfios.pojo;

public class Loan {
	private int lid;
	private int cibilScore;
	private int age;
	private double salary;
	private double amount;
	private String designation;
	private String company;
	private String name;
	private int tenure;
	private String status;
	public int getCibilScore() {
		return cibilScore;
	}
	public void setCibilScore(int cibilScore) {
		this.cibilScore = cibilScore;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Loan(int lid, int cibilScore, int age, double salary, double amount, String designation, String company,
			String name, int tenure, String status) {
		super();
		this.lid = lid;
		this.cibilScore = cibilScore;
		this.age = age;
		this.salary = salary;
		this.amount = amount;
		this.designation = designation;
		this.company = company;
		this.name = name;
		this.tenure = tenure;
		this.status = status;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public Loan() {
		super();
	}
	
	
	
}
