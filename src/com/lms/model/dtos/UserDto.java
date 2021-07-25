package com.lms.model.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {
	@JsonProperty("id")
	private String id;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("email")
	private String email;
	@JsonProperty("password")
	private String password;
	@JsonProperty("registeras")
	private String registerAs;
	public UserDto() {
	
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRegisterAs() {
		return registerAs;
	}
	public void setRegisterAs(String registerAs) {
		this.registerAs = registerAs;
	}
	
	public UserDto(String firstName, String lastName, String email, String password, String registerAs) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.registerAs = registerAs;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", registerAs=" + registerAs + "]";
	}
	

	
}
