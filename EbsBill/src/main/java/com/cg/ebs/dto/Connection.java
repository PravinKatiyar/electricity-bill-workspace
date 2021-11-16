package com.cg.ebs.dto;

public class Connection {

	private int meterNumber;
	private String connectionType;
	private String customerID;
	private boolean approvalStatus;


	//Getter and Setters//

	public String getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}

	public int getMeterNumber() {
		return meterNumber;
	}

	public void setMeterNumber(int meterNumber) {
		this.meterNumber = meterNumber;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public boolean getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(boolean approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
}
