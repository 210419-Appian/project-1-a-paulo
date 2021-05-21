package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.AccountStatus;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDAOImpl implements UserDAO{
	
	
	private static RoleDAO rDao = new RoleDAOImpl();


	@Override
	public boolean addUser(User u) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			//There is no chance for sql injection with just an integer so this is safe. 
			String sql = "INSERT INTO users (username, userpassword, firstname, lastname, email)"
					+ "	VALUES (?, ?, ?, ?, ?);";

			
			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;
			statement.setString(++index, u.getUsername());
			statement.setString(++index, u.getPassword());
			statement.setString(++index, u.getFirstName());
			statement.setString(++index, u.getLastName());
			statement.setString(++index, u.getEmail());
			//statement.setInt(++index, u.getRole().getRoleId());//need to convert role to intID
			
			statement.execute();
			return true;


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateUser(User u) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE users " + "SET username = ?, " + "userpassword = ?, " + "firstName = ?, " + "lastName = ?, " +
					"email = ?, " + 
					"roleid = ? " + 
					"WHERE userid = ?;";
					
					
			PreparedStatement statement = conn.prepareStatement(sql);
			int index = 0;
			statement.setString(++index, u.getUsername());
			statement.setString(++index, u.getPassword());
			statement.setString(++index, u.getFirstName());
			statement.setString(++index, u.getLastName());
			statement.setString(++index, u.getEmail());
			statement.setInt(++index, u.getRole().getRoleId());
			
			
			
			statement.setInt(++index, u.getUserId());
			
			statement.execute();
			return true;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User findUserById(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM users WHERE userid = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();

			User u = new User();

			while (result.next()) {
				u.setUserId(result.getInt("userid"));
				u.setUsername(result.getString("username"));
				u.setPassword(result.getString("userpassword"));
				u.setFirstName(result.getString("firstname"));
				u.setLastName(result.getString("lastname"));
				u.setEmail(result.getString("email"));
				int roleInt = result.getInt("roleid");
				u.setRole(rDao.findByRoleId(roleInt));
			}

			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> findUsers() {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM users;";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			List<User> list = new ArrayList<>();

			while (result.next()) {
				User u = new User(
						result.getInt("userid"), 
						result.getString("username"), 
						result.getString("userpassword"), 
						result.getString("firstname"), 
						result.getString("lastname"), 
						result.getString("email"),
						null
						);
				int roleInt = result.getInt("roleid");
				u.setRole(rDao.findByRoleId(roleInt));
				list.add(u);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findUserByUsername(String username) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM users WHERE username = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, username);

			ResultSet result = statement.executeQuery();

			User u = new User();

			while (result.next()) {
				u.setUserId(result.getInt("userid"));
				u.setUsername(result.getString("username"));
				u.setPassword(result.getString("userpassword"));
				u.setFirstName(result.getString("firstname"));
				u.setLastName(result.getString("lastname"));
				u.setEmail(result.getString("email"));
				int roleInt = result.getInt("roleid");
				u.setRole(rDao.findByRoleId(roleInt));
			}

			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
}
