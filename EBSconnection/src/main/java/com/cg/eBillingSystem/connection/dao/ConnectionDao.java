package com.cg.eBillingSystem.connection.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.eBillingSystem.connection.entity.Connection;
@Repository
public interface ConnectionDao extends JpaRepository<Connection, Integer> {
	
	List<Connection> findAllByCustomerID(String customerId);

}