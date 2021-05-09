package com.revature.daos;

import com.revature.models.AccountType;

public interface AccountTypesDAO {
	
	public AccountType findByTypeId(int id);

}
