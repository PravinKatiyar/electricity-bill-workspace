package com.cg.eBillingSystem.connection.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cg.eBillingSystem.connection.entity.Connection;
import com.cg.eBillingSystem.connection.service.ConnectionService;

@CrossOrigin("*")
@RestController
public class ConnectionController {

	@Autowired
	ConnectionService conService;
	Logger logger = LoggerFactory.getLogger(ConnectionController.class);
	
	@PostMapping("/addConnection")
	public ResponseEntity<?> addConnection(@Validated @RequestBody Connection connection) {
		try {
			conService.addConnection(connection);
			return new ResponseEntity<List<Connection>>(conService.findByCustomerId(connection.getCustomerID()), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}

	}

	@GetMapping("/getConnectionByCustomer/{customerId}")
	public ResponseEntity<?> getConnectionByCustomerId(@PathVariable String customerId){
		System.out.println(customerId);
		return new ResponseEntity<List<Connection>>(conService.findByCustomerId(customerId), HttpStatus.OK);
	}
	
	@PutMapping("approveConnection")
	public ResponseEntity<?> approveConnection(@Validated @RequestBody Connection connection){
		return new ResponseEntity<List<Connection>>(conService.approveConnection(connection), HttpStatus.OK);
	}
	
	@GetMapping("/getAllConnections")
	public List<Connection> viewAll() {
		return conService.viewAll();
	}

	
	@DeleteMapping("/deleteConnection/{id}")
	public ResponseEntity<?> deleteConnection(@PathVariable(value = "id") int connectionId) {
		try {
			conService.deleteConnection(connectionId);
			return new ResponseEntity<String>("Connection deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	
	
	
}
