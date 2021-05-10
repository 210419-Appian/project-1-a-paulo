package com.revature.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.models.User;

public class UserService {
	
	private UserDAO uDao = new UserDAOImpl();
	
	public boolean register(User u) {
		return uDao.addUser(u);
	}
	
	/*public boolean update(User u, HttpServletRequest req) {
		HttpSession ses = req.getSession();
		String sessionname = (String) ses.getAttribute("username");
		if(uDao.findUserByUsername(sessionname).getRole().getRoleId() == 4 || uDao.findUserByUsername(sessionname).getUserId() == u.getUserId()) {
			return uDao.updateUser(u);
		}
		return false;
	}*/
	
	public boolean update(User u) {
		return uDao.updateUser(u);
	}
	
	public List<User> findUsers(){
		return uDao.findUsers();
	}
	
	public User findUserById(int id) {
		return uDao.findUserById(id);
	}
	
	public User findUserByUsername(String username) {
		return uDao.findUserByUsername(username);
	}
	
	
	
}
