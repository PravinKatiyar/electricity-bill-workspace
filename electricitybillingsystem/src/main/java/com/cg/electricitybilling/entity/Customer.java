package com.cg.electricitybilling.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_cus")
public class Customer {
	@Id
	@Column(length = 12) // length of column
	private String customerId;
	@Column(length = 15)
	private String customerName;
	@Column(length = 10)
	private String contactNumber;
	@Column(length = 15)
	private String dateOfBirth;
	@Column(length = 6)
	private String gender;
	@Column(length = 12)
	private String aadharNumber;
	@Column(length = 12)
	private String address;
	@Column(length = 15)
	private String password;
	@Column(length = 15)
	private String email;
	public Customer(String customerId, String customerName, String contactNumber, String dateOfBirth, String gender,
			String aadharNumber, String address, String password, String email) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.contactNumber = contactNumber;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.aadharNumber = aadharNumber;
		this.address = address;
		this.password = password;
		this.email = email;
	}

	public Customer(){
		
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", contactNumber="
				+ contactNumber + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", aadharNumber="
				+ aadharNumber + ", address=" + address + ", password=" + password + ", email=" + email + "]";
	}
	
}
