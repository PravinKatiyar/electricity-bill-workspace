package com.cg.ebs.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bill")
public class BillEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer billID;
	private Integer connectionId;
	private LocalDate billingDate;
	private Integer previousReading;
	private Integer currentReading;
	private Integer unitsConsumed;
	private LocalDate dueDate;
	private Integer chargesPerUnit;
	private Double amount;
	@Column(name = "bill_status")
	private String billStatus;


	public BillEntity() {
		super();
	}

	public BillEntity(Integer billID, Integer connectionId, LocalDate billingDate, Integer previousReading,
			Integer currentReading, Integer unitsConsumed, LocalDate dueDate, Integer chargesPerUnit, Double amount,
			String billStatus) {
		super();
		this.billID = billID;
		this.connectionId = connectionId;
		this.billingDate = billingDate;
		this.previousReading = previousReading;
		this.currentReading = currentReading;
		this.unitsConsumed = unitsConsumed;
		this.dueDate = dueDate;
		this.chargesPerUnit = chargesPerUnit;
		this.amount = amount;
		this.billStatus = billStatus;
	}

	public Integer getBillID() {
		return billID;
	}

	public void setBillID(Integer billID) {
		this.billID = billID;
	}

	public Integer getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(Integer connectionId) {
		this.connectionId = connectionId;
	}

	public LocalDate getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(LocalDate billingDate) {
		this.billingDate = billingDate;
	}

	public Integer getPreviousReading() {
		return previousReading;
	}

	public void setPreviousReading(Integer previousReading) {
		this.previousReading = previousReading;
	}

	public Integer getCurrentReading() {
		return currentReading;
	}

	public void setCurrentReading(Integer currentReading) {
		this.currentReading = currentReading;
	}

	public Integer getUnitsConsumed() {
		return unitsConsumed;
	}

	public void setUnitsConsumed(Integer unitsConsumed) {
		this.unitsConsumed = unitsConsumed;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getChargesPerUnit() {
		return chargesPerUnit;
	}

	public void setChargesPerUnit(Integer chargesPerUnit) {
		this.chargesPerUnit = chargesPerUnit;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}

}
