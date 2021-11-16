package com.cg.electricitybilling.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.electricitybilling.entity.Bill;

@Repository
public interface IBillDao  extends JpaRepository<Bill,Integer>{
	 
	    @Query("SELECT ud from tb_bill ud where ud.bill_customerid=?1 and ud.billingMonth=?2")
		public Bill getBill(String customerid,String billingMonth);
	    
	    @Query("SELECT ud from tb_bill ud where ud.bill_customerid=?1 and ud.billid=?2")
		public Bill updateBill(String customerid,int billid);
	    
	    
}
