package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.services.UserService;

public class LoginServlet extends HttpServlet{
	
	UserDAO uDao = new UserDAOImpl();
	UserService uService = new UserService();
	private static ObjectMapper om = new ObjectMapper();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		UserDTO u = new UserDTO();

		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		
		String body = new String(sb);
		u = om.readValue(body, UserDTO.class);
		PrintWriter out = resp.getWriter();
		
		if(u.password.equals(uDao.findUserByUsername(u.username).getPassword())) {
			HttpSession ses = req.getSession();  //create a session
			ses.setAttribute("username", u.username);  //save username in the session
			String message = new String("message : You have logged in : " + u.username);
			out.print(message);
			resp.setStatus(200);
		}else {
			String message = new String("message : Invalid Credentials");
			out.print(message);
			resp.setStatus(400);
		}
		
	}

}
