package com.cg.springjwt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.springjwt.models.EbsUser;

@Repository
public interface UserRepository extends JpaRepository<EbsUser, Long> {
	Optional<EbsUser> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	EbsUser findByEmail(String userEmail);
	
	@Query(value = "select u from EbsUser u")
	List<EbsUser> findAllUsers();
		
}