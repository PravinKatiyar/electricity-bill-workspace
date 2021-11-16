package com.cg.ebs.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ebs.entity.BillEntity;
import com.cg.ebs.dto.BillDTO;
import com.cg.ebs.dto.Connection;
import com.cg.ebs.service.BillService;
import com.cg.ebs.util.Mapper;

@RestController
@CrossOrigin("*")
@RequestMapping("/bill")
public class BillController {

	@Autowired
	private BillService billService;

//	@Autowired
//	RestTemplate rest;

	Mapper mapper=new Mapper();

	@PostMapping("generateBill/{connectionId}/{currentReading}")
	public ResponseEntity<BillDTO> generateBill(@RequestBody Connection connection, @PathVariable String currentReading) {	
		BillEntity billE=billService.generateBill(connection,Integer.parseInt(currentReading));
		BillDTO bill = mapper.entityToDto(billE);		
		return new ResponseEntity<>(bill,HttpStatus.OK);
			
	}

	@GetMapping("/allbills")
	public ResponseEntity<List<BillDTO>> viewAllBills() {
		List<BillDTO> bills = new ArrayList<BillDTO>();
		List<BillEntity> billEntities = billService.viewAllBills();

		for (BillEntity bill : billEntities) {
			bills.add(mapper.entityToDto(bill));
		}
		return new ResponseEntity<>(bills, HttpStatus.OK);
	}

	@GetMapping("/billbyid/{billId}")
	public ResponseEntity<BillDTO> viewBillByBillId(@PathVariable Integer billId) {
		BillDTO bill = mapper.entityToDto(billService.viewBillByBillId(billId));
		return new ResponseEntity<>(bill, HttpStatus.OK);

	}
	
	
	@GetMapping("/billbyConnectionId/{connectionId}")
	public ResponseEntity<List<BillDTO>> viewBillsByConnectionID(@PathVariable Integer connectionId) {
		List<BillDTO> bills = new ArrayList<BillDTO>();
		List<BillEntity> billEntities = billService.viewBillsByConnectionId(connectionId);

		for (BillEntity bill : billEntities)
			bills.add(mapper.entityToDto(bill));
		
		return new ResponseEntity<>(bills, HttpStatus.OK);

	}
	
	
	@DeleteMapping("/delete/{billId}")
	public ResponseEntity<String> deleteBill(@PathVariable Integer billId) {
		boolean flag = billService.deleteBill(billId);
		System.out.println(flag);
		if(flag)
			return new ResponseEntity<String>("Bill Deleted Successfully", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Can not delete Bill", HttpStatus.NOT_FOUND);
	}

	
	@PutMapping("/update")
	public ResponseEntity<BillDTO> updateBill(@RequestBody BillDTO bill) {
		BillEntity billEntity = mapper.dtoToEntity(bill);
		BillEntity billE = billService.updateBill(billEntity);
		BillDTO billDto = mapper.entityToDto(billE);
		return new ResponseEntity<>(billDto, HttpStatus.OK);
	}

	
	@GetMapping("billFromDate/{date}") 
	public BillEntity getBillFromDate( @PathVariable String date)
	{
		return billService.getBillFromDate(LocalDate.parse(date));
	}

	@GetMapping("/amountByBillId/{billId}") 
	public Double amountByBillId( @PathVariable int billId)
	{
		return billService.amountByBillId(billId);
	}
	
	@GetMapping("/dueDateByBillId/{billId}") 
	public LocalDate dueDateByBillId( @PathVariable int billId)
	{
		return billService.dueDateById(billId);
	}
	
	@GetMapping("/changeStatusToPaid/{billId}") 
	public boolean changeStatusToPaid( @PathVariable int billId)
	{
		return billService.changeStatusToPaid(billId);
	}
		
}