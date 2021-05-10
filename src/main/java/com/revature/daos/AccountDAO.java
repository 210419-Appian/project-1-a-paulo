package com.revature.daos;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.AccountStatus;
import com.revature.models.User;

public interface AccountDAO {
	
	
	
	public List<Account> findAccounts();
	public Account findAccountsById(int id);
	public List<Account> findAccountsByStatus(int id);
	public List<Account> findAccountsByType(int id);
	public Account findAccountsByUserId(int id);
	public boolean submitAccount(Account a);
	public boolean updateAccount(Account a);
	public boolean updateAccountBalance(Account a);
	
	
	
	//public Account findAccountsByAccountId(int id);
	//public List<Account> findAccountsByStatusId(int id);
	//public List<Account> findAccountsByUserId(int id);
	
	//public boolean addAccountWithUser(Account a);
	

}
