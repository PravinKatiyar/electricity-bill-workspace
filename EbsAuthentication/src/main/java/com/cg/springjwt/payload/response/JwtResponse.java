package com.cg.springjwt.payload.response;

import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class JwtResponse {
	
	private String token;
	private String type = "Bearer";
	private String name;
	private String email;
	private String mobile;
	private String username;
	private String gender;
	private String bDay;
	private String address;
	private String role;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getbDay() {
		return bDay;
	}
	public void setbDay(String bDay) {
		this.bDay = bDay;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public JwtResponse(String token, String name, String email, String mobile, String username,
			String gender, String bDay, String address, String role) {
		super();
		this.token = token;
		this.type = type;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.username = username;
		this.gender = gender;
		this.bDay = bDay;
		this.address = address;
		this.role = role;
	}

}