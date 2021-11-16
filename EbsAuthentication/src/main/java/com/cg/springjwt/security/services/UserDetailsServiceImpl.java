package com.cg.springjwt.security.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.springjwt.models.EbsUser;
import com.cg.springjwt.payload.response.UserModel;
import com.cg.springjwt.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EbsUser yugunUser = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(yugunUser);
	}
	
	@Transactional
	public UserModel getUserByUserName(String username) throws UsernameNotFoundException {
		EbsUser yugunUser = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(yugunUser, userModel);
		return userModel;
	}

}
