package com.revature.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.daos.AccountDAOImpl;
import com.revature.daos.UserDAOImpl;
import com.revature.models.Account;
import com.revature.models.User;

public class AccountService {
	
	private AccountDAOImpl aDao = new AccountDAOImpl();
	private UserDAOImpl uDao = new UserDAOImpl();
	
	public List<Account> findAccounts(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		HttpSession ses = req.getSession();
		String sessionname = (String) ses.getAttribute("username");
		if(uDao.findUserByUsername(sessionname).getRole().getRoleId() >= 3) {
			return aDao.findAccounts();
		}
		else {
			PrintWriter out = resp.getWriter();
			String message = new String("message : The requested action is not permitted.");
			out.print(message);
			resp.setStatus(401);
			return null;
		}
		
	}
	
	public boolean submitAccount(Account a, HttpServletRequest req, HttpServletResponse resp) throws IOException{
		HttpSession ses = req.getSession();
		String sessionname = (String) ses.getAttribute("username");
		if(uDao.findUserByUsername(sessionname).getRole().getRoleId() >= 3 || uDao.findUserByUsername(sessionname).getUserId() == a.getUser().getUserId()) {
			return aDao.submitAccount(a);
		}
		else {
			PrintWriter out = resp.getWriter();
			String message = new String("message : The requested action is not permitted.");
			out.print(message);
			resp.setStatus(401);
			return false;
		}
		
	}
	
	public boolean updateAccount(Account a, HttpServletRequest req, HttpServletResponse resp) throws IOException{
		HttpSession ses = req.getSession();
		String sessionname = (String) ses.getAttribute("username");
		if(uDao.findUserByUsername(sessionname).getRole().getRoleId() == 4) {
			return aDao.updateAccount(a);
		}
		else {
			PrintWriter out = resp.getWriter();
			String message = new String("message : The requested action is not permitted.");
			out.print(message);
			resp.setStatus(401);
			return false;
		}
		
	}
	
	//public boolean updateAccountBalance(Account a) {
	//	return aDao.updateAccountBalance(a);
	//}
	
	public boolean updateAccountBalance(Account a, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession ses = req.getSession();
		String sessionname = (String) ses.getAttribute("username");
		if(uDao.findUserByUsername(sessionname).getRole().getRoleId() == 4 || uDao.findUserByUsername(sessionname).getUserId() == a.getUser().getUserId()) {
			return aDao.updateAccountBalance(a);
		}
		else {
			PrintWriter out = resp.getWriter();
			String message = new String("message : The requested action is not permitted.");
			out.print(message);
			resp.setStatus(401);
			return false;
		}
	}
	
	public Account findAccountsById(int id, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession ses = req.getSession();
		String sessionname = (String) ses.getAttribute("username");
		if(uDao.findUserByUsername(sessionname).getRole().getRoleId() >= 3 || aDao.findAccountsById(id).getAccountId() == id) {
			return aDao.findAccountsById(id);
		}
		else {
			System.out.println("in the else loop");
			PrintWriter out = resp.getWriter();
			String message = new String("message : The requested action is not permitted.");
			out.print(message);
			resp.setStatus(401);
			return null;
		}
		
	}
	
	public List<Account> findAccountsByStatus(int id, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession ses = req.getSession();
		String sessionname = (String) ses.getAttribute("username");
		if(uDao.findUserByUsername(sessionname).getRole().getRoleId() >= 3) {
			return aDao.findAccountsByStatus(id);
		}
		else {
			PrintWriter out = resp.getWriter();
			String message = new String("message : The requested action is not permitted.");
			out.print(message);
			resp.setStatus(401);
			return null;
		}
		
	}
	public List<Account> findAccountsByType(int id, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession ses = req.getSession();
		String sessionname = (String) ses.getAttribute("username");
		if(uDao.findUserByUsername(sessionname).getRole().getRoleId() == 4) {
			return aDao.findAccountsByType(id);
		}
		else {
			PrintWriter out = resp.getWriter();
			String message = new String("message : The requested action is not permitted.");
			out.print(message);
			resp.setStatus(401);
			return null;
		}
		
	}
	
	public Account findAccountsByUserId(int id, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession ses = req.getSession();
		String sessionname = (String) ses.getAttribute("username");
		if(uDao.findUserByUsername(sessionname).getRole().getRoleId() >= 3 || uDao.findUserByUsername(sessionname).getUserId() == id) {
			return aDao.findAccountsByUserId(id);
		}
		else {
			PrintWriter out = resp.getWriter();
			String message = new String("message : The requested action is not permitted.");
			out.print(message);
			resp.setStatus(401);
			return null;
		}
		
		
		
	}

}
