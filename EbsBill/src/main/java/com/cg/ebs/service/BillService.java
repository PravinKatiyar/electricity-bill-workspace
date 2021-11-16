package com.cg.ebs.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.ebs.dto.Connection;
import com.cg.ebs.entity.BillEntity;
import com.cg.ebs.exception.CustomException;

public interface BillService {

	BillEntity generateBill(Connection connection, Integer currentReading);

	BillEntity viewBillByBillId(Integer billId);

	List<BillEntity> viewAllBills();

	List<BillEntity> viewBillsByConnectionId(Integer connectionId);

	BillEntity updateBill(BillEntity updatedDTO) throws CustomException;

	boolean deleteBill(Integer billId);

	BillEntity getBillFromDate(LocalDate date);

	Integer getPreviousReading(Integer connectionId);

	Double amountByBillId(int billId);

	LocalDate dueDateById(int billId);

	boolean changeStatusToPaid(int billId);

	public boolean isOneBillPerMonth(Integer connectionId);

}
