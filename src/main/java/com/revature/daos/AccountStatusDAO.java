package com.revature.daos;

import com.revature.models.AccountStatus;

public interface AccountStatusDAO {
	
	public AccountStatus findByStatusId(int id);
	public AccountStatus findByStatusName(String statusName);

}
