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
import com.revature.daos.AccountDAO;
import com.revature.daos.AccountDAOImpl;
import com.revature.models.Account;
import com.revature.models.BalanceDTO;
import com.revature.models.TimeDTO;
import com.revature.services.AccountService;

public class PassTimeServlet extends HttpServlet{
	
	//private AccountDAO aDAO = new AccountDAOImpl();
	private AccountService aService = new AccountService();
	private ObjectMapper om = new ObjectMapper();
	
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		TimeDTO timeDTO = new TimeDTO();
		
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		String body = new String(sb);
		timeDTO = om.readValue(body, TimeDTO.class);
		
		double interest = .02;
		
		List<Account> list = aService.findAccountsByType(2, req, resp);
		for(Account a : list) {
			a.setBalance((timeDTO.numOfMonths * interest * a.getBalance()) + a.getBalance());
			aService.updateAccountBalance(a, req, resp);
		}
		PrintWriter out = resp.getWriter();
		String message = new String("message : " + timeDTO.numOfMonths + " months of interest has been accrued for all Savings Accounts");
		out.print(message);
		

		
	}

}
