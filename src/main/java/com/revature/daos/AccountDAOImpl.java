package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.AccountStatus;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO{
	
	UserDAO uDao = new UserDAOImpl();
	AccountTypesDAO tDao = new AccountTypesDAOImpl();
	AccountStatusDAO sDao = new AccountStatusDAOImpl();
	
	

	@Override
	public List<Account> findAccounts() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM accounts;";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<Account> list = new ArrayList<>();

			while (result.next()) {
				Account a = new Account(
						result.getInt("accountid"), 
						result.getDouble("balance"),
						null,
						null, 
						null
						);
				int userInt = result.getInt("userid");
				a.setUser(uDao.findUserById(userInt));
				int statusInt = result.getInt("statusid");
				a.setStatus(sDao.findByStatusId(statusInt));
				int typeInt = result.getInt("typeid");
				a.setType(tDao.findByTypeId(typeInt));
				
				list.add(a);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean submitAccount(Account a) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			//There is no chance for sql injection with just an integer so this is safe. 
			String sql = "INSERT INTO accounts (balance, userid, statusid, typeid)"
					+ "	VALUES (?, ?, ?, ?);";

			
			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;
			statement.setDouble(++index, a.getBalance());
			statement.setInt(++index, a.getUser().getUserId());
			statement.setInt(++index, a.getStatus().getStatusId());
			statement.setInt(++index, a.getType().getTypeId());
			//statement.setString(++index, u.getEmail());
			//statement.setInt(++index, u.getRole().getRoleId());//need to convert role to intID
			
			statement.execute();
			return true;


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateAccount(Account a) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE accounts " + "SET userid = ?, " + "statusid = ?, " + "typeid = ? " +
					"WHERE accountid = ?;";
					
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			
			statement.setInt(++index, a.getUser().getUserId());
			statement.setInt(++index, a.getStatus().getStatusId());
			statement.setInt(++index, a.getType().getTypeId());
			
			
			statement.setInt(++index, a.getAccountId());
			
			statement.execute();
			return true;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Account findAccountsById(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM accounts WHERE accountid = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			//User u = new User();
			Account a = new Account();

			while (result.next()) {
				a.setAccountId(result.getInt("accountid"));
				a.setBalance(result.getDouble("balance"));
				int uId = result.getInt("userid");
				a.setUser(uDao.findUserById(uId));;
				int sId = result.getInt("statusid");
				a.setStatus(sDao.findByStatusId(sId));
				int tId = result.getInt("typeid");
				a.setType(tDao.findByTypeId(tId));
			}

			return a;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Account> findAccountsByStatus(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM accounts WHERE statusid = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			//int statusid = status.getStatusId();
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			List<Account> list = new ArrayList<>();

			//User u = new User();
			//Account a = null;

			while (result.next()) {
				int uId = result.getInt("userid");
				int sId = result.getInt("statusid");
				int tId = result.getInt("typeid");
				Account a = new Account(
				result.getInt("accountid"),
				result.getDouble("balance"),
				uDao.findUserById(uId),
				sDao.findByStatusId(sId),		
				tDao.findByTypeId(tId));
				list.add(a);
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Account findAccountsByUserId(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM accounts WHERE userid = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			//int userid = u.getUserId();
			int userid = id;
			statement.setInt(1, userid);
			ResultSet result = statement.executeQuery();

			//User u = new User();
			Account a = new Account();

			while (result.next()) {
				a.setAccountId(result.getInt("accountid"));
				a.setBalance(result.getDouble("balance"));
				int uId = result.getInt("userid");
				a.setUser(uDao.findUserById(uId));;
				int sId = result.getInt("statusid");
				a.setStatus(sDao.findByStatusId(sId));
				int tId = result.getInt("typeid");
				a.setType(tDao.findByTypeId(tId));
			}

			return a;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateAccountBalance(Account a) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE accounts " + "SET balance = ? " +
					"WHERE accountid = ?;";
					
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			
			statement.setDouble(++index, a.getBalance());
			//statement.setInt(++index, a.getUser().getUserId());
			//statement.setInt(++index, a.getStatus().getStatusId());
			//statement.setInt(++index, a.getType().getTypeId());
			
			
			statement.setInt(++index, a.getAccountId());
			
			statement.execute();
			return true;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Account> findAccountsByType(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM accounts WHERE typeid = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			//int statusid = status.getStatusId();
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			List<Account> list = new ArrayList<>();

			//User u = new User();
			//Account a = null;

			while (result.next()) {
				int uId = result.getInt("userid");
				int sId = result.getInt("statusid");
				int tId = result.getInt("typeid");
				Account a = new Account(
				result.getInt("accountid"),
				result.getDouble("balance"),
				uDao.findUserById(uId),
				sDao.findByStatusId(sId),		
				tDao.findByTypeId(tId));
				list.add(a);
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	


}
