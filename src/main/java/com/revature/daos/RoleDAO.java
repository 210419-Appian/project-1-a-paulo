package com.revature.daos;

import com.revature.models.Role;
import com.revature.models.User;

public interface RoleDAO {
	
	//public User findByUserId(int id);
	public Role findByRoleId(int id);

}
