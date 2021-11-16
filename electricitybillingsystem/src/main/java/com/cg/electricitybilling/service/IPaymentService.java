package com.cg.electricitybilling.service;

import java.util.List;

import com.cg.electricitybilling.entity.Payment;

public interface IPaymentService {
	
public String addPayment(String customerId,int amount);
public List<Payment> viewPayments();
}
