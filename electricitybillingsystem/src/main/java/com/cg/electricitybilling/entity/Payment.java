package com.cg.electricitybilling.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="tb_payment")
@Table(name="tb_payment")
public class Payment {

	@Id
	@Column(name ="customer_ID", length=45)
	private String customerID;
	
	@Column(name ="payment", length=45)
	private int payment;

	public Payment(String customerID, int payment) {
		super();
		this.customerID = customerID;
		this.payment = payment;
		
	}

	public Payment() {
		// TODO Auto-genrated constructor stub
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

}
