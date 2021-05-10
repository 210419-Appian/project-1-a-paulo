package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	
	public boolean addUser(User u);
	public boolean updateUser(User u);
	public User findUserById(int id);
	public User findUserByUsername(String username);
	public List<User> findUsers();

}
