package com.todolist.webbapp.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class Login {
	
	@NotNull(message = "email* is required")
	@Email(message = "is not valid email")
	private String email;
	
	@NotNull(message = "password* is required")
	private String password;
	
	
	public Login() {
		
		
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
