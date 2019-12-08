package com.ihsinformatics.spring.appgpa.dto;

import com.sun.istack.NotNull;

public class UserDto {
	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	private String password;
	private String matchingPassword;

	@NotNull
	private String email;

	public UserDto() {
	}

	public UserDto(String firstName, String lastName, String password, String matchingPassword, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.matchingPassword = matchingPassword;
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
