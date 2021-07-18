package com.lms.entity;


public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String userPassword;
	private int roleid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public User() {
		
	}
	public User(String firstName, String lastName, String email, String userPassword, int roleid) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userPassword = userPassword;
		this.roleid = roleid;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", userPassword=" + userPassword + ", roleid=" + roleid + "]";
	}
	
	
	
}
