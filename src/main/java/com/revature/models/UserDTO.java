package com.revature.models;

public class UserDTO {
	
	public String username; // not null, unique
    public String password; // not null

	public UserDTO() {
		super();
	}
	
	public UserDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	/*public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}*/
	
	
}
