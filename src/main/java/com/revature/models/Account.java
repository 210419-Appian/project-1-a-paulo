package com.revature.models;

public class Account {

    private int accountId; // primary key
    private double balance;  // not null
    private User user;
    private AccountStatus status;
    private AccountType type;


    public Account(){
        super();
    }

    public Account(int accountId, double balance, User user, AccountStatus status, AccountType type){
        super();
        this.accountId = accountId;
        this.balance = balance;
        this.user = user;
        this.status = status;
        this.type = type;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
    

	
}
