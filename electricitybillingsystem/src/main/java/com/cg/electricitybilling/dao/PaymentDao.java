package com.cg.electricitybilling.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.electricitybilling.entity.Payment;

@Repository
public interface PaymentDao extends JpaRepository<Payment,String> {
   
}
