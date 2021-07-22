package com.lms.model.dtos;

public class CredentialsDto {
	private String email;
	private String password;
	private String role;
	
	public CredentialsDto() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public CredentialsDto(String email, String password, String role) {
		this.email = email;
		this.password = password;
		this.role = role;
	}

	@Override
	public String toString() {
		return "CredentialsDto [email=" + email + ", password=" + password + ", role=" + role + "]";
	}
}
