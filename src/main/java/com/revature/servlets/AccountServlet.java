package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.AccountDAOImpl;
import com.revature.models.Account;
import com.revature.models.BalanceDTO;
import com.revature.models.TransferDTO;
import com.revature.models.User;
import com.revature.models.UserDTO;
//import com.revature.models.User;
import com.revature.services.AccountService;
//import com.revature.services.UserService;

public class AccountServlet extends HttpServlet{
	
	private AccountService aService = new AccountService();
	private AccountDAOImpl aDao = new AccountDAOImpl();
	private ObjectMapper om = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String url = req.getRequestURI();
		String[] sections = url.split("/");
		String json = null;
		
		//System.out.println(url);
		//System.out.println(sections.length);
		
		if(sections.length == 3) {
			List<Account> list = aService.findAccounts(req, resp);
			json = om.writeValueAsString(list);
		}
		else if(sections.length == 4) {
			int id = Integer.parseInt(sections[3]);
			Account a = aService.findAccountsById(id, req, resp);
			json = om.writeValueAsString(a);
		}
		else if(sections.length == 5  && sections[3].equalsIgnoreCase("owner")) {
			int id = Integer.parseInt(sections[4]);
			//System.out.println(id);
			Account a = aService.findAccountsByUserId(id, req, resp);
			json = om.writeValueAsString(a);
			//System.out.println("in right loop");	
		}
		else if(sections.length == 5  && sections[3].equalsIgnoreCase("status")) {
			int id = Integer.parseInt(sections[4]);
			List<Account> list = aService.findAccountsByStatus(id, req, resp);
			json = om.writeValueAsString(list);
		}
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		resp.setStatus(200);
		resp.setContentType("application/json");
	}
	
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		StringBuilder sb = new StringBuilder();
		//The request object has a built in method to return an object that can read the body line by line. 
		BufferedReader reader = req.getReader();
		String line = reader.readLine();
		while (line != null) {
			sb.append(line);
			//advance to the next line
			line = reader.readLine();
		}
		
		String body = new String(sb);
		
		
		//Jackson will convert the json that is in the body to a java object we tell it to. 
		Account a = om.readValue(body, Account.class);
		
		if (aService.submitAccount(a, req, resp)) {
			resp.setStatus(201);
		}else {
			resp.setStatus(400);
		}
	}
		
		@Override
		protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
			StringBuilder sb = new StringBuilder();
			
			//The request object has a built in method to return an object that can read the body line by line. 
			BufferedReader reader = req.getReader();
			
			String line = reader.readLine();
			
			while (line != null) {
				sb.append(line);
				//advance to the next line
				line = reader.readLine();
			}
			
			String body = new String(sb);
			
			//Jackson will convert the json that is in the body to a java object we tell it to. 
			Account a = om.readValue(body, Account.class);
			
			if (aService.updateAccount(a, req, resp)) {
				resp.setStatus(201);
			}else {
				resp.setStatus(400);
			}
		}
		
		
		protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String url = req.getRequestURI();
			String[] sections = url.split("/");
			//String json = null;
			
			BalanceDTO bal = new BalanceDTO();
			TransferDTO trans = new TransferDTO();
			
			BufferedReader reader = req.getReader();
			StringBuilder sb = new StringBuilder();
			String line = reader.readLine();
			while (line != null) {
				sb.append(line);
				line = reader.readLine();
			}
			
			System.out.println(url);
			System.out.println(sections.length);
			
			String body = new String(sb);
			
			PrintWriter out = resp.getWriter();
			
			if(sections[3].equalsIgnoreCase("withdraw")) {
				bal = om.readValue(body, BalanceDTO.class);
				//System.out.println(aService.findAccountsByUserId(bal.accountId).getBalance());
				//System.out.println(bal.balance);
				//System.out.println(aService.findAccountsByUserId(bal.accountId).toString());
				Account a = aService.findAccountsById(bal.accountId, req, resp);
				a.setBalance((a.getBalance()) - bal.balance);
				aService.updateAccountBalance(a, req, resp);
				String message = new String("message :" + bal.balance + " has been withdrawn from Account " + bal.accountId);
				out.print(message);
				
			}
			else if(sections[3].equalsIgnoreCase("deposit")) {
				bal = om.readValue(body, BalanceDTO.class);
				Account a = aService.findAccountsById(bal.accountId, req, resp);
				a.setBalance((a.getBalance()) + bal.balance);
				aService.updateAccountBalance(a, req, resp);
				String message = new String("message :" + bal.balance + " has been deposited to Account " + bal.accountId);
				out.print(message);
			}
			else if(sections[3].equalsIgnoreCase("transfer")) {
				trans = om.readValue(body, TransferDTO.class);
				Account a = aService.findAccountsById(trans.sourceAccountId, req, resp);
				Account b = aService.findAccountsById(trans.targetAccountId, req, resp);
				
				//System.out.println(aDao.findAccountsByUserId(trans.targetAccountId).getBalance());
				a.setBalance(a.getBalance() - trans.amount);
				b.setBalance(b.getBalance() + trans.amount);
				aService.updateAccountBalance(a, req, resp);
				aService.updateAccountBalance(b, req, resp);
				String message = new String("message :" + trans.amount + " has been transferred from Account " + trans.sourceAccountId +
						"to Account" + trans.targetAccountId);
				out.print(message);
			}
		}
		
		@Override
	    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        if (req.getMethod().equals("PATCH")) {
	            doPatch(req, resp);

	        } else {
	            super.service(req, resp);
	        }
	    }

}
