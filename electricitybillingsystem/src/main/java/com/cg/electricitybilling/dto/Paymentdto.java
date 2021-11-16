package com.cg.electricitybilling.dto;

public class Paymentdto {
private String customerID;
private int  amount;
public Paymentdto(String customerID, int amount) {
	super();
	this.customerID = customerID;
	this.amount = amount;
}
public String getCustomerID() {
	return customerID;
}
public void setCustomerID(String customerID) {
	this.customerID = customerID;
}
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}

}
