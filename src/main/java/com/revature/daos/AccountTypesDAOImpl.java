package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.AccountType;
import com.revature.utils.ConnectionUtil;

public class AccountTypesDAOImpl implements AccountTypesDAO{

	@Override
	public AccountType findByTypeId(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM accounttypes WHERE typeid = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();

			AccountType type = new AccountType();

			while (result.next()) {
				type.setTypeId(id);
				type.setType("typename");
			}

			return type;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
