package com.lms.entity;

public class Role {
	
	private int roleid;
	private String roleType;
	
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	
	public Role(String roleType) {
		this.roleType = roleType;
	}
	
	public Role() {
		
	}
	@Override
	public String toString() {
		return "Role [roleid=" + roleid + ", roleType=" + roleType + "]";
	}
}
