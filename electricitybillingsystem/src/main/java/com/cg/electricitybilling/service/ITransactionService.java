package com.cg.electricitybilling.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.electricitybilling.entity.Transaction;
import com.cg.electricitybilling.exception.CustomerNotFoundException;

@Service
public interface ITransactionService {
	public List<Transaction> viewbycustomerid(String customerId);
	
	public String addTransaction(int connectionid,int amount,String mode,String date,int billid,String customerid);
	public List<Transaction> viewallTransactions();
	public List<Transaction> viewbyconnection(String customerid,int connectionid);
}
