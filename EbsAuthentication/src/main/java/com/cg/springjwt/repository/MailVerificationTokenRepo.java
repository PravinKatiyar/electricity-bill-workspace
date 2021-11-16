package com.cg.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.springjwt.models.MailVerificationToken;

public interface MailVerificationTokenRepo extends JpaRepository<MailVerificationToken, Long> {

	MailVerificationToken findByToken(String token);
}
