package com.cg.springjwt.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "ebs_users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class EbsUser {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Column(name = "name")
	@Size(max = 30)
	private String name;

	@NotBlank
	@Size(max = 50)
	@Email
	@Column(name = "email")
	private String email;
	
	@Column(name = "mobile")
	private String mobile;
	
	@NotBlank
	@Column(name = "username")
	@Size(min = 3, max = 20)
	private String username;

	@NotBlank
	@Column(name = "pass")
	@Size(min = 6, max = 61)
	private String password;

	@Column(name = "gender")
	private String gender;
	
	@Column(name = "bDay")
	private String bDay;
	
	@Column(name = "address")
	private String address;

	@Column(name = "mvt")
	private boolean mailVerfication;

	@ManyToMany(fetch = FetchType.LAZY, 
				cascade = {CascadeType.ALL})
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public EbsUser() {
	}

	public EbsUser( String name, String email, String mobile, String username,  String password, String gender, boolean mailVerification, String bDay, String address) {
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.username = username;
		this.gender = gender;
		this.password = password;
		this.mailVerfication = mailVerification;
		this.bDay = bDay;
		this.address = address;
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

	public boolean isMailVerfication() {
		return mailVerfication;
	}

	public void setMailVerfication(boolean mailVerfication) {
		this.mailVerfication = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}