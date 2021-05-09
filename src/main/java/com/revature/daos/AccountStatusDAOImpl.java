package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.AccountStatus;
import com.revature.utils.ConnectionUtil;

public class AccountStatusDAOImpl implements AccountStatusDAO{

	@Override
	public AccountStatus findByStatusId(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM accountstatus WHERE statusid = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();

			AccountStatus status = new AccountStatus();

			while (result.next()) {
				status.setStatusId(id);
				status.setStatus(result.getString("statusname"));
			}

			return status;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AccountStatus findByStatusName(String statusName) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM accountstatus WHERE statusname = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, statusName);

			ResultSet result = statement.executeQuery();

			AccountStatus status = new AccountStatus();

			while (result.next()) {
				status.setStatusId(result.getInt("statusid"));
				status.setStatus(statusName);
			}

			return status;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
