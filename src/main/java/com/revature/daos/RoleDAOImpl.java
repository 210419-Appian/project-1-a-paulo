package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Role;
import com.revature.utils.ConnectionUtil;

public class RoleDAOImpl implements RoleDAO{

	@Override
	public Role findByRoleId(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM roles WHERE roleid = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();

			Role role = new Role();

			while (result.next()) {
				role.setRoleId(result.getInt("roleid"));
				role.setRole(result.getString("rolename"));
			}

			return role;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
