package com.blogApp.modelDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {

	
	private int id;
	
	@NotBlank(message = "Name is mandatory")
	private String name;
	@Email
	@NotBlank(message = "Name is mandatory")
	private String email;
	@NotBlank(message = "password is mandatory")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", 
	 
	message = " a digit must occur at least once, "+
			  " a lower case letter must occur at least once,"+ 
			  " a upper case letter must occur at least once,"+ 
			  " a special character must occur at least once,"+ 
			  " no whitespace allowed in the entire password")
    private String password;
	@Size(min = 20, max =200 ,message = "minimum of 20 charecters and max 200." )
	private String about;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
}
