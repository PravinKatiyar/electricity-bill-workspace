package com.cg.ebs.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.ebs.exception.CustomException;

@SpringBootTest
class BillServiceImplTest {

	@Autowired
	BillService billservice;

//	@Test
//	void generateBillTest1() {
//		int currentReading = 500;
//		int connectionId = 10001;
//		assertThat(billservice.generateBill(connectionId, currentReading)).isNotNull();
//	}

//	@Test
//	@ExceptionHandler(CustomException.class)
//	void generateBillFail() {
//		int currentReading = 100;
//		int connectionId = 10002;
//		assertThrows(CustomException.class, () -> {
//			billservice.generateBill(connectionId, currentReading);
//		});
//
//	}

	@Test
	void showBillDetailsTest() {
		int billId = 10001;
		assertThat(billservice.viewBillByBillId(billId)).isNotNull();

	}

	@Test

	@ExceptionHandler(CustomException.class)
	void showBillDetailsFail() {
		int billId = 0;
		assertThrows(CustomException.class, () -> {
			billservice.viewBillByBillId(billId);
		});

	}
}
