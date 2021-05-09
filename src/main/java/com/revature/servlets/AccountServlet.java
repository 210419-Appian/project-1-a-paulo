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
import com.revature.models.Account;
//import com.revature.models.User;
import com.revature.services.AccountService;
//import com.revature.services.UserService;

public class AccountServlet extends HttpServlet{
	
	private AccountService aService = new AccountService();
	private ObjectMapper om = new ObjectMapper();
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		List<Account> list = aService.findAccounts();
		
		
		String json = om.writeValueAsString(list);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		resp.setStatus(200);
		resp.setContentType("application/json");
	}
	
	
	
	/*protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
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
		User u = om.readValue(body, User.class);
		
		if (uService.register(u)) {
			resp.setStatus(201);
		}else {
			resp.setStatus(400);
		}
	}
		
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
			User u = om.readValue(body, User.class);
			
			if (uService.update(u)) {
				resp.setStatus(201);
			}else {
				resp.setStatus(400);
			}
		}*/

}
