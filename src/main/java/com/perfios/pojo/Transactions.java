package com.perfios.pojo;

import java.util.Date;

public class Transactions {
	private int id;
	private int from;
	private int to;
	private double amount;
	private Date date;
	private String type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Transactions(int id, int from, int to, double amount, Date date, String type) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.amount = amount;
		this.date = date;
		this.type = type;
	}
	public Transactions() {
		super();
	}
	
	
}
