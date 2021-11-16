package com.cg.electricitybilling.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.electricitybilling.dao.IBillDao;
import com.cg.electricitybilling.dto.Billdto;
import com.cg.electricitybilling.entity.Bill;

@Service
@Transactional
public class IBillServiceImpl implements IBillService{

	@Autowired
	private IBillDao bdao;
	
	@Override
	public Bill generateBill(Billdto bdto) {
		// TODO Auto-generated method stub
		 Bill bill=new Bill();
		 bill.setBill_customerid(bdto.getCustomerid());
		 LocalDate currentDate = LocalDate.now(); 
 	    bill.setBillingMonth(currentDate.getMonth().toString());
 	   // bill.setPreviousReading(100);         //previousReading
 	    bill.setCurrentReading(bdto.getReading());
 	    bill.setChargesPerUnit(10);
 	    bill.setAmountPayable(bdto.getReading()*10);
 	   // int d=currentDate.getDayOfMonth();
 	   // int dd=d+7;
 	    String month=currentDate.getMonth().toString();
 	    //bill.setDueDate(dd+"/"+month);
 	    bill.setDueDate(month);
 	    bdao.save(bill);
 	    return bill;
	}

	@Override
	public Bill getBill(String customerid, String month) {
		// TODO Auto-generated method stub
	    return  bdao.getBill(customerid, month);
	
	}
	

}
