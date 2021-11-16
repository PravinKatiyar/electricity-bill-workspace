package com.cg.electricitybilling.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="tb_bill")
@Table(name="tb_bill")	
public class Bill {
     @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length=150)
	private int billid;
	@Column(length=150)
	private String bill_customerid;
	@Column(length=15)
	private String billingMonth;
	@Column(length=15)
	private String dueDate;
	@Column(length=150)
	private int currentReading;
	@Column(length=150)
	private String status;
	@Column(length=150)
	private int chargesPerUnit;
	@Column(length=150)
	private int amountPayable;
	public Bill(int billid, String bill_customerid, String billingMonth, String dueDate, int currentReading,
			String status, int chargesPerUnit, int amountPayable) {
		super();
		this.billid = billid;
		this.bill_customerid = bill_customerid;
		this.billingMonth = billingMonth;
		this.dueDate = dueDate;
		this.currentReading = currentReading;
		this.status = status;
		this.chargesPerUnit = chargesPerUnit;
		this.amountPayable = amountPayable;
	}
	

	public Bill() {
		// TODO Auto-generated constructor stub
	}


	public int getBillid() {
		return billid;
	}

	public void setBillid(int billid) {
		this.billid = billid;
	}

	public String getBill_customerid() {
		return bill_customerid;
	}

	public void setBill_customerid(String bill_customerid) {
		this.bill_customerid = bill_customerid;
	}

	public String getBillingMonth() {
		return billingMonth;
	}

	public void setBillingMonth(String billingMonth) {
		this.billingMonth = billingMonth;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public int getCurrentReading() {
		return currentReading;
	}

	public void setCurrentReading(int currentReading) {
		this.currentReading = currentReading;
	}

	

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public int getChargesPerUnit() {
		return chargesPerUnit;
	}


	public void setChargesPerUnit(int chargesPerUnit) {
		this.chargesPerUnit = chargesPerUnit;
	}


	public int getAmountPayable() {
		return amountPayable;
	}

	public void setAmountPayable(int amountPayable) {
		this.amountPayable = amountPayable;
	}


	@Override
	public String toString() {
		return "Bill [billid=" + billid + ", bill_customerid=" + bill_customerid + ", billingMonth=" + billingMonth
				+ ", dueDate=" + dueDate + ", currentReading=" + currentReading + ", status=" + status
				+ ", chargesPerUnit=" + chargesPerUnit + ", amountPayable=" + amountPayable + "]";
	}

	
	
	
 
}
