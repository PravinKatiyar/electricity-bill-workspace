package com.cg.ebs.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.ebs.entity.BillEntity;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class BillEntityTest {

	private BillEntity dto;
	
	@Test
	 void testConstructor() {
		dto = new BillEntity(1, 1, LocalDate.parse("2020-12-12"), 200, 400, 200, LocalDate.parse("2021-01-28"), 10, 2000.00, "UNPAID");
		assertEquals(BillEntity.class, dto.getClass());
		
	}
	@Test
	 void testSuperConstructor() {
		dto = new BillEntity();
		assertEquals(BillEntity.class, dto.getClass());
		
	}
	
	@Test
	 void testGetters() {
		dto = new BillEntity(1, 1, LocalDate.parse("2020-12-12"), 200, 400, 200, LocalDate.parse("2021-01-28"), 10, 2000.00, "UNPAID");
		assertEquals(1, dto.getBillID());
		assertEquals(1, dto.getConnectionId());
		assertEquals(LocalDate.parse("2020-12-12"), dto.getBillingDate());
		assertEquals(200, dto.getPreviousReading());
		assertEquals(400, dto.getCurrentReading());
		assertEquals(200, dto.getUnitsConsumed());
		assertEquals(LocalDate.parse("2021-01-28"), dto.getDueDate());
		assertEquals(10, dto.getChargesPerUnit());
		assertEquals(2000.00, dto.getAmount());
		assertEquals("UNPAID", dto.getBillStatus());
	}
	
	@Test
	 void testSetters() {
		dto= new BillEntity();
		dto.setBillID(1);
		dto.setConnectionId(1);
		dto.setBillingDate(LocalDate.parse("2020-12-12"));
		dto.setPreviousReading(200);
		dto.setCurrentReading(400);
		dto.setUnitsConsumed(200);
		dto.setDueDate(LocalDate.parse("2021-01-28"));
		dto.setChargesPerUnit(10);
		dto.setAmount(2000.00);
		dto.setBillStatus("UNPAID");
		assertEquals(1, dto.getBillID());
		assertEquals(1, dto.getConnectionId());
		assertEquals(LocalDate.parse("2020-12-12"), dto.getBillingDate());
		assertEquals(200, dto.getPreviousReading());
		assertEquals(400, dto.getCurrentReading());
		assertEquals(200, dto.getUnitsConsumed());
		assertEquals(LocalDate.parse("2021-01-28"), dto.getDueDate());
		assertEquals(10, dto.getChargesPerUnit());
		assertEquals(2000.00, dto.getAmount());
		assertEquals("UNPAID", dto.getBillStatus());
	}

}
