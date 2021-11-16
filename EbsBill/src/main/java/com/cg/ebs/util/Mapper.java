package com.cg.ebs.util;

import com.cg.ebs.entity.BillEntity;
import com.cg.ebs.dto.BillDTO;

public class Mapper {

	
	  public BillEntity dtoToEntity(BillDTO bill) {
	
		  BillEntity billEntity=new BillEntity();
		  
		  billEntity.setBillID( bill.getBillID());
		  billEntity.setConnectionId(bill.getConnectionId());
		  billEntity.setBillingDate(bill.getBillingDate());
		  billEntity.setPreviousReading(bill.getPreviousReading());
		  billEntity.setCurrentReading(bill.getCurrentReading());
		  billEntity.setUnitsConsumed(bill.getUnitsConsumed());
		  billEntity.setDueDate(bill.getDueDate());
		  billEntity.setChargesPerUnit(bill.getChargesPerUnit());
		  billEntity.setAmount(bill.getAmount());
     	  billEntity.setBillStatus(bill.getBillStatus());
	 
          return billEntity; 
          }
	 
	
	  
	  public BillDTO entityToDto(BillEntity billEntity)
	  {
		  BillDTO bill=new BillDTO();
		  
		  bill.setBillID(billEntity.getBillID());
		  bill.setConnectionId(billEntity.getConnectionId());
		  bill.setBillingDate(billEntity.getBillingDate());
		  bill.setPreviousReading(billEntity.getPreviousReading());
		  bill.setCurrentReading(billEntity.getCurrentReading());
		  bill.setUnitsConsumed(billEntity.getUnitsConsumed());
		  bill.setDueDate(billEntity.getDueDate());
		  bill.setChargesPerUnit(billEntity.getChargesPerUnit());
		  bill.setAmount(billEntity.getAmount());
		  bill.setBillStatus(billEntity.getBillStatus());
		  
		  return bill;
	  }
}
