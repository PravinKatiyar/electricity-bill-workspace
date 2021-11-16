package com.cg.ebs.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.ebs.entity.BillEntity;


@Repository
public interface BillDao  extends JpaRepository<BillEntity,Integer>{
	
	@Query(value = "Select b from BillEntity b where b.connectionId = :connectionId")
	List<BillEntity> viewBillsByConnectionId(@Param("connectionId")Integer connectionId);
	
	@Query(value = "Select b from BillEntity b where b.billingDate = :billingDate")
	BillEntity getBillFromDate(@Param("billingDate") LocalDate date);
	
	@Query(value = "Select max(b.currentReading)  from BillEntity b where b.connectionId = :connectionId")
	Integer getPreviousReading(@Param("connectionId") Integer connectionId);
	
	@Query(value="select max(b.billingDate) from BillEntity b  where b.connectionId = :connectionId")
	LocalDate getPreviousBillDate(@Param("connectionId") Integer connectionId);
	
	@Query(value ="select b.amount from BillEntity b where b.billID= :billId")
	Double amountByBillId(@Param("billId") int billId);
	
	@Query(value ="select b.dueDate from BillEntity b where b.billID= :billId")
	LocalDate dueDateByBillId(@Param("billId") int billId);
	
}
