package com.treno.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserDto {

	 @NotBlank(message = "Inserire username")
	private String username;
	 
	 @NotBlank(message = "Inserire password")
	private String password;
	 
	 @Email(message = "Inserire email")
	private String email;

	public UserDto() {
		super();
	}

	public UserDto(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}

	// Getters e Setters
	public String getUsername() {
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
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserDto [username=" + username +  ", email=" + email + "]"; 
	} /* ho tolto la password da qui perche chatgpt dice che rappresenta 
	     un rischio per la sicurezza in quanto potrebbe finire 
	     nei log o nelle risposte http */
	
	
	
}
