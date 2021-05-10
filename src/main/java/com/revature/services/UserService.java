package com.revature.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.models.User;

public class UserService {
	
	private UserDAO uDao = new UserDAOImpl();
	
	public boolean register(User u) {
		return uDao.addUser(u);
	}
	
	public boolean update(User u, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession ses = req.getSession();
		String sessionname = (String) ses.getAttribute("username");
		if(uDao.findUserByUsername(sessionname).getRole().getRoleId() == 4 || uDao.findUserByUsername(sessionname).getUserId() == u.getUserId()) {
			return uDao.updateUser(u);
		}
		else {
			PrintWriter out = resp.getWriter();
			String message = new String("message : The requested action is not permitted.");
			out.print(message);
			resp.setStatus(401);
			return false;
		}
	}
	
	/*public boolean update(User u) {
		return uDao.updateUser(u);
	}*/
	
	public List<User> findUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		HttpSession ses = req.getSession();
		String sessionname = (String) ses.getAttribute("username");
		if(uDao.findUserByUsername(sessionname).getRole().getRoleId() >= 3) {
			return uDao.findUsers();
		}
		else {
			PrintWriter out = resp.getWriter();
			String message = new String("message : The requested action is not permitted.");
			out.print(message);
			resp.setStatus(401);
			return null;
		}
		
	}
	
	public User findUserById(int id, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession ses = req.getSession();
		String sessionname = (String) ses.getAttribute("username");
		if(uDao.findUserByUsername(sessionname).getRole().getRoleId() >= 3 || uDao.findUserByUsername(sessionname).getUserId() == id) {
			return uDao.findUserById(id);
		}
		else {
			PrintWriter out = resp.getWriter();
			String message = new String("message : The requested action is not permitted.");
			out.print(message);
			resp.setStatus(401);
			return null;
		}
		
	}
	
	public User findUserByUsername(String username) {
		return uDao.findUserByUsername(username);
	}
	
	
	
}
