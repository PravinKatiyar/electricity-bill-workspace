package com.cg.electricitybillingsystem.approval;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.electricitybilling.dao.ApprovalDao;
import com.cg.electricitybilling.dao.CustomerDao;
import com.cg.electricitybilling.entity.Customer;
import com.cg.electricitybilling.entity.CustomerApproval;
import com.cg.electricitybilling.exception.CustomerNotFoundException;


@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
public class ApprovalController {
	
	
	@Autowired
	private CustomerDao cdao;
	
	@Autowired
	private ApprovalDao appdao;
	
	 @PostMapping(value = "/apply")
	 	public String addPayment(@Validated @RequestBody CustomerApproval capp, BindingResult br) throws CustomerNotFoundException{	
	             Optional<CustomerApproval> opt=appdao.findById(capp.getCustomerid());
	           if(opt.isPresent()) {
	        	   return "you have already applied";
	           }
	           else {
	        	   appdao.save(capp);
	        	   return "Applied Successfully";
	           }
	     }
	 @GetMapping(value="/getallapprovals")
     public List<CustomerApproval> approveCustomer(){
    	 return appdao.findAll();
     }
	 
	 @PostMapping(value="/approve/{customerid}")
     public void approveCustomer(@PathVariable("customerid") String customerid){
    	Customer c=new Customer();
    	CustomerApproval capp=new CustomerApproval();
    	c.setCustomerId(capp.getCustomerid());
    	c.setCustomerName(capp.getName());
    	c.setGender(capp.getGender());
    	c.setPassword(capp.getPassword());
    	c.setAadharNumber(capp.getAadharnumber());
    	c.setAddress(capp.getAddress());
    	c.setDateOfBirth(capp.getDateofbirth());
    	c.setEmail(capp.getEmail());
    	c.setContactNumber(capp.getContactnumber());
    	cdao.save(c);
    	appdao.deleteById(customerid);
    
     }
}
