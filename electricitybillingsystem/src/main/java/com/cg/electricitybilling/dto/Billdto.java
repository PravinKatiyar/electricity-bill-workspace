package com.cg.electricitybilling.dto;

public class Billdto {

	private String customerid;
	private int reading;
	public Billdto(String customerid, int amount) {
		super();
		this.customerid = customerid;
		this.reading = amount;
	}
	public Billdto() {
		
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public int getReading() {
		return reading;
	}
	public void setReading(int amount) {
		this.reading = amount;
	}
	@Override
	public String toString() {
		return "Billdto [customerid=" + customerid + ", amount=" + reading + "]";
	}
	
}
