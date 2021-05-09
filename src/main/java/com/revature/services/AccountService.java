package com.revature.services;

import java.util.List;

import com.revature.daos.AccountDAOImpl;
import com.revature.models.Account;
import com.revature.models.User;

public class AccountService {
	
	private AccountDAOImpl aDao = new AccountDAOImpl();
	
	public List<Account> findAccounts(){
		return aDao.findAccounts();
	}

}
