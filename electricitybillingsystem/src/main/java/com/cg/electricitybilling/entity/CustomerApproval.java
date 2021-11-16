package com.cg.electricitybilling.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_approval")
public class CustomerApproval {
    @Id
    @Column(length=15)
	private String customerid;
    @Column(length=15)
	private String name;
    @Column(length=15)
	private String password;
    @Column(length=15)
	private String contactnumber;
    @Column(length=15)
	private String dateofbirth;
    @Column(length=15)
	private String gender;
    @Column(length=15)
	private String aadharnumber;
    @Column(length=15)
	private String address;
    @Column(length=15)
	private String email;
	public CustomerApproval(String customerid, String name, String password, String contactnumber, String dateofbirth,
			String gender, String aadharnumber, String address, String email) {
		super();
		this.customerid = customerid;
		this.name = name;
		this.password = password;
		this.contactnumber = contactnumber;
		this.dateofbirth = dateofbirth;
		this.gender = gender;
		this.aadharnumber = aadharnumber;
		this.address = address;
		this.email = email;
	}
	public CustomerApproval(){
		
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContactnumber() {
		return contactnumber;
	}
	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}
	public String getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAadharnumber() {
		return aadharnumber;
	}
	public void setAadharnumber(String aadharnumber) {
		this.aadharnumber = aadharnumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "CustomerApproval [customerid=" + customerid + ", name=" + name + ", password=" + password
				+ ", contactnumber=" + contactnumber + ", dateofbirth=" + dateofbirth + ", gender=" + gender
				+ ", aadharnumber=" + aadharnumber + ", address=" + address + ", email=" + email + "]";
	}
    
    
}
