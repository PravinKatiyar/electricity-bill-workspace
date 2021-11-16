package com.cg.electricitybilling.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.electricitybilling.entity.Transaction;

@Repository
public interface TransactionDao extends JpaRepository<Transaction,Integer>{
	
    @Query("SELECT gt from tb_transaction gt where gt.customerid=?1")
	public List<Transaction> getTransactions(String customerid);
    
    @Query("SELECT gt from tb_transaction gt where gt.customerid=?1 and gt.connectionid=?2")
	public List<Transaction> getTransactionsbyconnectionid(String customerid ,int connectionid);

}
