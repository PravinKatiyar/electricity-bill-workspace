package com.cg.electricitybilling.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import java.sql.Date;
@Entity(name="tb_transaction")
@Table(name="tb_transaction")
public class Transaction {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="payment_ID", length=45)
     private int paymentId;
	@Column(name ="payment_Mode", length=45)
    private String paymentMode;
	@Column(name ="payment_Date", length=45)
     private String paymentDate;
	@Column(name ="payment_Amount", length=45)
     private int paymentAmount;
	@Column(name ="payment_Status", length=45)
     private String paymentStatus;
	@Column(name="connectionid" ,length=45)
	private int connectionid;
	@Column(name="billid" ,length=45)
	private int billid;
	@Column(name="customerid" ,length=45)
	private String customerid;
	public Transaction(int paymentId, String paymentMode, String paymentDate, int paymentAmount, String paymentStatus,
			int connectionid, int billid, String customerid) {
		super();
		this.paymentId = paymentId;
		this.paymentMode = paymentMode;
		this.paymentDate = paymentDate;
		this.paymentAmount = paymentAmount;
		this.paymentStatus = paymentStatus;
		this.connectionid = connectionid;
		this.billid = billid;
		this.customerid = customerid;
	};

	public Transaction(){
		
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public int getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(int paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public int getConnectionid() {
		return connectionid;
	}

	public void setConnectionid(int connectionid) {
		this.connectionid = connectionid;
	}

	public int getBillid() {
		return billid;
	}

	public void setBillid(int billid) {
		this.billid = billid;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	@Override
	public String toString() {
		return "Transaction [paymentId=" + paymentId + ", paymentMode=" + paymentMode + ", paymentDate=" + paymentDate
				+ ", paymentAmount=" + paymentAmount + ", paymentStatus=" + paymentStatus + ", connectionid="
				+ connectionid + ", billid=" + billid + ", customerid=" + customerid + "]";
	}

	
	

}
