package com.revature.models;

import java.io.Serializable;

public class Role implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int roleId; // primary key
    private String role; // not null, unique

    public Role(){
        super();
    }
    
    public Role(int roleId) {
    	super();
    	this.roleId = roleId;
    }
    
    
    public Role(int roleId, String role){
        super();
        this.roleId = roleId;
        this.role = role;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role1 = (Role) o;

        if (roleId != role1.roleId) return false;
        return role != null ? role.equals(role1.role) : role1.role == null;
    }

    @Override
    public int hashCode() {
        int result = roleId;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
