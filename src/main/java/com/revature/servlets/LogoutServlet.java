package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		HttpSession ses = req.getSession(false);
		
		if(ses != null) {
			ses.invalidate();
			resp.setStatus(200);
		}
		else{
			resp.setStatus(400);
		}
		//resp.sendRedirect("");  //when left empty sends to base url of my application
		//resp.sendRedirect("http://www.google.com");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doGet(req, resp);
	}
	

}
