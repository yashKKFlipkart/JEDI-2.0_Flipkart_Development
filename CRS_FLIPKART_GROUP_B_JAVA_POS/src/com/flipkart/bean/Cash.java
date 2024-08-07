package com.flipkart.bean;

public class Cash {
	
	private String cashier;
	private String branch;
	
	
	
	public Cash(String cashier, String branch) {
		
		this.cashier = cashier;
		this.branch = branch;
	}
	
	public String getCashier() {
		return cashier;
	}
	public void setCashier(String cashier) {
		this.cashier = cashier;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
}
