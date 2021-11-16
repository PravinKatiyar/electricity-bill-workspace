package com.cg.electricitybilling.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.electricitybilling.dao.CustomerDao;
import com.cg.electricitybilling.dto.Billdto;
import com.cg.electricitybilling.entity.Bill;
import com.cg.electricitybilling.entity.Customer;
import com.cg.electricitybilling.exception.CustomerNotFoundException;
import com.cg.electricitybilling.service.IBillServiceImpl;


@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
public class BillController {

	@Autowired
	private CustomerDao cdao;
 	
 	@Autowired
 	private IBillServiceImpl ibill;
	
	
    @PostMapping(value="/generatebill")
 	public Bill generateBill(@Validated @RequestBody Billdto bdto, BindingResult br) throws CustomerNotFoundException {
 		//Bill bill=new Bill();
 		Optional<Customer> opt=cdao.findById(bdto.getCustomerid());
 		if(opt.isPresent()) {
 		Bill b=ibill.generateBill(bdto);
 		return b;
 		}
 		else
 			throw new CustomerNotFoundException("Customer not found");

 }
     
     @PostMapping(value="/getbill")
 		public Bill getBill(@RequestParam String customerid,@RequestParam String month) {
  	   Bill b=new Bill();
 			 b=ibill.getBill(customerid, month);
 			System.out.println(b);
 			return b;

 }
}
