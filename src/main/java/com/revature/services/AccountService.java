package com.revature.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.revature.daos.AccountDAOImpl;
import com.revature.daos.UserDAOImpl;
import com.revature.models.Account;
import com.revature.models.User;

public class AccountService {
	
	private AccountDAOImpl aDao = new AccountDAOImpl();
	private UserDAOImpl uDao = new UserDAOImpl();
	
	public List<Account> findAccounts(){
		return aDao.findAccounts();
	}
	
	public boolean submitAccount(Account a){
		return aDao.submitAccount(a);
	}
	
	public boolean updateAccount(Account a){
		return aDao.updateAccount(a);
	}
	
	//public boolean updateAccountBalance(Account a) {
	//	return aDao.updateAccountBalance(a);
	//}
	
	public boolean updateAccountBalance(Account a, HttpServletRequest req) {
		HttpSession ses = req.getSession();
		String sessionname = (String) ses.getAttribute("username");
		if(uDao.findUserByUsername(sessionname).getRole().getRoleId() == 4 || uDao.findUserByUsername(sessionname).getUserId() == a.getUser().getUserId()) {
			return aDao.updateAccountBalance(a);
		}
		return false;
	}
	
	public Account findAccountsById(int id) {
		return aDao.findAccountsById(id);
	}
	
	public Account findAccountsByStatus(int id) {
		return aDao.findAccountsByStatus(id);
	}
	
	public Account findAccountsByUserId(int id) {
		return aDao.findAccountsByUserId(id);
		
	}

}
