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
import com.revature.models.User;
import com.revature.services.UserService;

public class UserServlet extends HttpServlet{

	/**
	 * 
	 */
	private UserService uService = new UserService();
	private ObjectMapper om = new ObjectMapper();
	
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String url = req.getRequestURI();
		String[] sections = url.split("/");
		String json = null;
		
		//System.out.println(url);
		//System.out.println(sections.length);
		
		if(sections.length == 3) {
			List<User> list = uService.findUsers();
			json = om.writeValueAsString(list);
		}
		else if(sections.length == 4) {
			int id = Integer.parseInt(sections[3]);
			User u = uService.findUserById(id);
			json = om.writeValueAsString(u);
		}
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		resp.setStatus(200);
		resp.setContentType("application/json");
	}
	
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doGet(req, resp);
		/*StringBuilder sb = new StringBuilder();
		
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
		}*/
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
		}
		
}
