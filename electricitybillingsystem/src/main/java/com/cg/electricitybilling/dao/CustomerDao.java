package com.cg.electricitybilling.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.electricitybilling.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer,String>{

}
