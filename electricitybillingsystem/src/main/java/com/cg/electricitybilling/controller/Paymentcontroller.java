package com.cg.electricitybilling.controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.electricitybilling.dao.CustomerDao;
import com.cg.electricitybilling.dao.TransactionDao;
import com.cg.electricitybilling.dto.Paymentdto;
import com.cg.electricitybilling.entity.Customer;
import com.cg.electricitybilling.entity.Transaction;
import com.cg.electricitybilling.exception.CustomerNotFoundException;
import com.cg.electricitybilling.service.IPaymentServiceImpl;
import com.cg.electricitybilling.service.ITransactionService;


@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
public class Paymentcontroller {

	@Autowired
	private IPaymentServiceImpl ser;
	
	@Autowired
	private ITransactionService iser;
	
	@Autowired
	private CustomerDao cdao;
	
	@Autowired
	private TransactionDao tdao;
	

	
	@PostMapping(value = "/addpayment")
	public String addPayment(@Validated @RequestBody Paymentdto pdto, BindingResult br) throws CustomerNotFoundException{
		String customerId=pdto.getCustomerID();
		int amount=pdto.getAmount();
		//Customer c=new Customer();
	    Optional<Customer> opt=cdao.findById(pdto.getCustomerID());
	    try {
	    if(opt.isPresent()) {
	    	String str=ser.addPayment(customerId, amount);
			return str + customerId + " of ₹ " + amount;
	    }
	    else {
	    	throw new CustomerNotFoundException("Customer Not Found");
	    }
	    }
	    catch(CustomerNotFoundException e) {
	    	return e.getMessage();
	    }
		
		
	}
	
     @GetMapping(value = "/paymenthistory/{customerid}")
	public ResponseEntity<List<Transaction>> customerTransactions(@PathVariable String customerid){
    	 List<Transaction> list=iser.viewbycustomerid(customerid);
		return new ResponseEntity<>(list, HttpStatus.OK);
	
	}
     @GetMapping(value = "/paymenthistory/{customerid}/{connectionid}")
 	public List<Transaction> viewpaymentsbyconnection(@PathVariable String customerid,@PathVariable int connectionid){
 		List<Transaction> list=iser.viewbyconnection(customerid,connectionid);
 		return list;
 	
 	}
     
     @GetMapping(value="/viewallpayments")
     public List<Transaction> viewAllPayments(){
    	 List<Transaction> list=iser.viewallTransactions();
    	 return list;
     }
   
     
     @PostMapping(value = "/pay/{connectionid}/{amount}/{mode}/{billid}/{customerid}")
 	public String payAmount(@PathVariable int connectionid, 
 			@PathVariable int amount,@PathVariable String mode,@PathVariable int billid,@PathVariable String customerid) {
    		Date date = new Date();
    	    String strDateFormat = "yyyy/MM/dd hh:mm:ss a";
    	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
    	    String formattedDate= dateFormat.format(date);
    	    String str=iser.addTransaction(connectionid,amount,mode,formattedDate,billid,customerid);
    	    return str;
 	}

 	

 
     
    
           
}
