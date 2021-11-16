package com.cg.electricitybilling.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.electricitybilling.dao.PaymentDao;
import com.cg.electricitybilling.dao.TransactionDao;
import com.cg.electricitybilling.entity.Payment;

@Service
public class IPaymentServiceImpl implements IPaymentService {

	
	@Autowired
	PaymentDao pdao;
	@Autowired
	TransactionDao tdao;
	
	@Override
	public String addPayment(String customerId, int amount) {
		// TODO Auto-generated method stub
		Payment p=new Payment();
		Optional<Payment> optpayment=pdao.findById(customerId);
		if(optpayment.isPresent()) {
		p=optpayment.get();
	   int previousAmount=p.getPayment();
		
		p.setCustomerID(customerId);
		p.setPayment(amount +previousAmount);
		pdao.save(p);
		}
		else {
			p.setCustomerID(customerId);
			p.setPayment(amount);
			pdao.save(p);
		}
		return "Payment added for customerId ";
	
	}

	@Override
	public List<Payment> viewPayments() {
		// TODO Auto-generated method stub
		List<Payment> list=pdao.findAll();
		return list;
	}

}
