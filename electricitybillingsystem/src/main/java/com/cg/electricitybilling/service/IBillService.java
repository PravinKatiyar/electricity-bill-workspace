package com.cg.electricitybilling.service;

import com.cg.electricitybilling.dto.Billdto;
import com.cg.electricitybilling.entity.Bill;

public interface IBillService {
public Bill generateBill(Billdto bdto);
public Bill getBill(String customerid,String month);
}
