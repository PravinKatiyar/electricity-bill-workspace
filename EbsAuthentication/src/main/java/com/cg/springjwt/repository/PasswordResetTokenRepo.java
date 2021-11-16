package com.cg.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.springjwt.models.PasswordResetToken;

public interface PasswordResetTokenRepo extends JpaRepository<PasswordResetToken, Long> {

	PasswordResetToken findByToken(String token);

}
