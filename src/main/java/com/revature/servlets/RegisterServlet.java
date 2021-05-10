package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.AccountService;
import com.revature.services.UserService;

public class RegisterServlet extends HttpServlet{
	//private AccountService aService = new AccountService();
	private UserService uService = new UserService();
	private ObjectMapper om = new ObjectMapper();
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doPost(req, resp);
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
		User u = om.readValue(body, User.class);
		
		if (uService.register(u)) {
			resp.setStatus(201);
		}else {
			PrintWriter out = resp.getWriter();
			String message = new String("message : Invalid fields");
			out.print(message);
			resp.setStatus(400);
		}

	}

}
