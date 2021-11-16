package com.cg.electricitybilling.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.electricitybilling.dao.IBillDao;
import com.cg.electricitybilling.dao.PaymentDao;
import com.cg.electricitybilling.dao.TransactionDao;
import com.cg.electricitybilling.entity.Transaction;

@Service
public class ITransactionServiceImpl implements ITransactionService{
	@Autowired
	TransactionDao dao;
	
	@Autowired
	PaymentDao pdao;
	
	@Autowired
	IBillDao bdao;

	@Override
	public List<Transaction> viewbycustomerid(String customerId) {
		
		return dao.getTransactions(customerId);	
	}

	@Override
	public String addTransaction(int connectionid,int amount,String mode,String date,int billid,String customerid) {
		// TODO Auto-generated method stub
		Transaction t=new Transaction();
		//t.setPaymentId(1);
		t.setConnectionid(connectionid);
        t.setPaymentAmount(amount);	
        t.setPaymentDate(date);
        t.setPaymentStatus("done");
        t.setPaymentMode(mode);
        t.setBillid(billid);
        t.setCustomerid(customerid);
      // Bill b= bdao.updateBill(customerId, billid);
      // b.setStatus("PAID ON " + date );
      // bdao.save(b);
        dao.save(t);
		return "Transaction Added";
	}

	@Override
	public List<Transaction> viewallTransactions() {
		List<Transaction> list=dao.findAll();
		return list;
	}

	@Override
	public List<Transaction> viewbyconnection(String customerid, int connectionid) {
		return dao.getTransactionsbyconnectionid(customerid, connectionid);
		
	} 

}
