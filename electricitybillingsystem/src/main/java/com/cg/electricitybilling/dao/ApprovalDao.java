 package com.cg.electricitybilling.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.electricitybilling.entity.CustomerApproval;

public interface ApprovalDao extends JpaRepository<CustomerApproval,String> {

}
