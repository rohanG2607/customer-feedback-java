package com.feedback.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegisterationDTO {

	@NotBlank(message = "FullName is required.")
	private String fullName;

	@NotBlank(message = "Username is required")
	@Size(min = 4, max = 20, message = "Username must be between 4 and 20 charactters.")
	private String username;

	@NotBlank(message = "Email is required")
	@Email(message = "Enter a valid address.")
	private String email;

	@NotBlank(message = "Password is required")
	@Size(min = 6, message = "password must contain atleast 6 characters.")
	private String password;

	public UserRegisterationDTO() {

	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

}
