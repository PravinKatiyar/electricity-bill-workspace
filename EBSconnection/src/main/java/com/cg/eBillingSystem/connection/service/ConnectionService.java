package com.cg.eBillingSystem.connection.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.eBillingSystem.connection.dao.ConnectionDao;
import com.cg.eBillingSystem.connection.entity.Connection;
import com.cg.eBillingSystem.connection.exception.EntityNotFoundException;


@Service
public class ConnectionService{

	@Autowired
	ConnectionDao dao;
	
	
/*
 * Delete Connection	
 */
	public String deleteConnection(int connectionId) {

		Optional<Connection> findById = dao.findById(connectionId);
		if (findById.isPresent()) {
			dao.deleteById(connectionId);
			System.out.println(connectionId);
			return "Connection deleted";
		} else {
			throw new EntityNotFoundException("No connection found with test id " + connectionId);
		}	
		
	}
	

	public List<Connection> viewAll() {
		return dao.findAll();
	}
	
	
	public List<Connection> findByCustomerId(String customerId){
		for(Connection conn: dao.findAllByCustomerID(customerId))
			System.out.println(conn.getCustomerID()+conn.getMeterNumber());
			return dao.findAllByCustomerID(customerId);
	}
	
	public List<Connection> approveConnection(Connection connection){
		connection.setApprovalStatus(true);
		dao.delete(connection);
		dao.save(connection);
		System.out.println("connection approval done : " + connection.getMeterNumber()+ " "+ connection.getApprovalStatus());

		return findByCustomerId(connection.getCustomerID());
	}

	
	public Connection addConnection(Connection connection) {
		return dao.save(connection);
	}

}