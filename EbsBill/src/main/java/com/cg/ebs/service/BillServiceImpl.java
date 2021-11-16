package com.cg.ebs.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ebs.entity.BillEntity;
import com.cg.ebs.dao.BillDao;
import com.cg.ebs.dto.Connection;
import com.cg.ebs.exception.CustomException;


@Service
public class BillServiceImpl implements BillService {

	@Autowired
	BillDao billdao;
	
	final static Logger logger = LoggerFactory.getLogger(BillServiceImpl.class);

	@Override
	public BillEntity generateBill(Connection connection,Integer currentReading) {
		BillEntity billEntity=new BillEntity();	
		
		if(isOneBillPerMonth(connection.getMeterNumber()))
		{
			billEntity.setBillingDate(LocalDate.now());
		}
		else
		{
			throw new CustomException("Only one bill can be generated per month");
		}
		
		System.out.println(connection.getMeterNumber()+"  "+currentReading);
		String connectionType = connection.getConnectionType();
		
		if(connectionType!=null) {
		
			System.out.println(connectionType);
			billEntity.setConnectionId(connection.getMeterNumber());
			billEntity.setCurrentReading(currentReading);
			billEntity.setBillStatus("UNPAID");
			
	
			Integer previousReading=getPreviousReading(connection.getMeterNumber());
			if(currentReading<previousReading)
			{
				logger.error("Current Reading cannot be less than Previous Reading");
				throw new CustomException("Current Reading cannot be less than Previous Reading");
			}
			
			billEntity.setPreviousReading(previousReading);
			
			billEntity.setUnitsConsumed(currentReading - previousReading);
			billEntity.setChargesPerUnit(getchargesPerUnitBasedOnConnection(connectionType, billEntity.getUnitsConsumed()));       //need to call connection service
			
			billEntity.setDueDate(LocalDate.now().plusDays(15));
			
			billEntity.setAmount((double) ((currentReading - previousReading)*billEntity.getChargesPerUnit()));
			return billdao.save(billEntity);
		
		}
		
		else {
			throw new CustomException("Connection Id Doesn't exist");
		}
	}

	@Override
	public BillEntity viewBillByBillId(Integer billId) {
		Optional<BillEntity> optional = billdao.findById(billId);

		if (optional.isPresent()) {
			BillEntity bill = optional.get();
			return bill;
		} else {
			logger.error("BILL NOT FOUND");
			throw new CustomException("No Bill found ");
		}

	}

	@Override
	public BillEntity updateBill(BillEntity updatedDTO) throws CustomException {
		if (updatedDTO == null) {
			throw new CustomException("Bill can't be null");
		}
		updatedDTO.setUnitsConsumed(updatedDTO.getCurrentReading() - updatedDTO.getPreviousReading());
		updatedDTO.setAmount((double) ((updatedDTO.getUnitsConsumed())*updatedDTO.getChargesPerUnit()));
		return billdao.save(updatedDTO);
	}

	@Override
	public boolean deleteBill(Integer billId) {
		Optional<BillEntity> optional = billdao.findById(billId);
		if (optional.isPresent()) {
			billdao.deleteById(billId);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<BillEntity> viewAllBills() {
		try {
			return billdao.findAll();	
		}catch(Exception e) {
			logger.error("BILLS NOT FOUND");
			throw new CustomException("No Bill/s found ");
		}
	}

	@Override
	public List<BillEntity> viewBillsByConnectionId(Integer connectionId) {
		try {
			return billdao.viewBillsByConnectionId(connectionId);
		}catch(Exception e) {
			logger.error("BILL NOT FOUND");
			throw new CustomException("No Bill/s found ");
		}
	}

	@Override
	public BillEntity getBillFromDate(LocalDate date) {
		return billdao.getBillFromDate(date);
	}

	@Override
	public Integer getPreviousReading(Integer connectionId) {

		Integer previousReading=billdao.getPreviousReading(connectionId);
		if(previousReading!=null) {
			return previousReading;
		}else {
			logger.error("PREVIOUS READING NOT FOUND");
			return 0;
		}
		
		
	}
	
	public Integer getchargesPerUnitBasedOnConnection(String conn, int unitsConsumed) {
		if (conn.equals(new String("RESIDENTIAL")) && unitsConsumed <= 200) {
			return 7;
		}
		else if (conn.equals(new String("RESIDENTIAL")) && unitsConsumed > 200) {
			return 10;
		}
		else if (conn.equals(new String("COMMERCIAL"))) {
			return 15;
		} else {
			return -1;
		}
	}

	@Override
	public Double amountByBillId(int billId) {
		return billdao.amountByBillId(billId);
	}

	@Override
	public LocalDate dueDateById(int billId) {
		try {
		return billdao.dueDateByBillId(billId);
		}
		catch(Exception e)
		{
			throw new CustomException("dueDate cannot be fetched");
		}
	}

	@Override
	public boolean changeStatusToPaid(int billId) {
		Optional<BillEntity> optional = billdao.findById(billId);
		if (optional.isPresent()) {
			BillEntity bill = optional.get();
			bill.setBillStatus("PAID");
			billdao.save(bill);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isOneBillPerMonth(Integer connectionId)
	{
		LocalDate date=LocalDate.now();
		LocalDate previousBillDate=billdao.getPreviousBillDate(connectionId);
		if(previousBillDate!=null){
			return (date.compareTo(previousBillDate.plusDays(28))>0);
		}
		else
		{
			return true;
		}
		
	}

}