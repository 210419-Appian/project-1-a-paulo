package com.revature.models;

public class BalanceDTO {
	public int accountId;
	public double balance;
	
	public BalanceDTO() {
		super();
	}
	
	public BalanceDTO(int accountId, double balance) {
		super();
		this.accountId = accountId;
		this.balance = balance;
	}

}
