package com.revature.services;

import java.util.List;

import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.models.User;

public class UserService {
	
	private UserDAO uDao = new UserDAOImpl();
	
	public boolean register(User u) {
		return uDao.addUser(u);
	}
	
	public boolean update(User u) {
		return uDao.updateUser(u);
	}
	
	public List<User> findUsers(){
		return uDao.findUsers();
	}
	
	public User findUserById(int id) {
		return uDao.findUserById(id);
	}
	
	
	
}
