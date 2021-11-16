package com.electricitybillingsystem;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.electricitybilling.dao.TransactionDao;
import com.cg.electricitybilling.entity.Transaction;
import com.cg.electricitybilling.service.ITransactionService;
import com.cg.electricitybilling.service.ITransactionServiceImpl;

@RunWith(SpringRunner.class)
class ElectricitybillingsystemApplicationTests {

	
	@SpringBootTest
	class ExamTest {

		@Autowired
		ITransactionService examService;
		
		@Autowired
		 TransactionDao dao;
		
		@Autowired
		ITransactionServiceImpl examServiceImpl;
		

		
	

		@Test
	    void viewAllExamTest() {

			List<Transaction> examList = examService.viewallTransactions();
			assertTrue(1 <= examList.size());
		}

		
		


	}
	
	
	
	
	
	
	@Test
	void contextLoads() {
	}

}
