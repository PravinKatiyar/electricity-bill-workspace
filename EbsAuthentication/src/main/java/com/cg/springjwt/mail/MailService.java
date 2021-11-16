package com.cg.springjwt.mail;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.springjwt.models.*;
import com.cg.springjwt.repository.*;


@Service
public class MailService {
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordResetTokenRepo passwordTokenRepository;
	
	@Autowired
	MailVerificationTokenRepo mvtRepo;
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
	private Environment env;
	
	public void sendMVTMail(EbsUser yugunUser) {
		try {
		    String token = UUID.randomUUID().toString();
		    creatMailVerificationToken(yugunUser, token);
		    mailSender.send(constructMailVerificationTokenEmail(env.getProperty("spring.defaultURL"), token, yugunUser));
	    }
        catch(Exception ex) {
        	ex.printStackTrace();
        }
	}

	
	// Save Password to Database:
	public void changeUserPassword(EbsUser yugunUser, String newPassword) {
		yugunUser.setPassword(encoder.encode(newPassword));
		userRepository.save(yugunUser);
	}
	
	// Search Password reset token in database:
	public EbsUser getUserByPasswordResetToken(String token) {
		return passwordTokenRepository.findByToken(token).getUser();
	}
	
	// Generate and Save Password Reset Token:
	public void createPasswordResetTokenForUser(EbsUser yugunUser, String token) {
	    PasswordResetToken myToken = new PasswordResetToken(token, yugunUser);
		passwordTokenRepository.save(myToken);
	}
	
	//Token Mail Construction:
	public SimpleMailMessage constructResetTokenEmail(String contextPath, String token, EbsUser yugunUser) {
			    String url = contextPath + "/user/changePassword?token=" + token;
			    String message = "Link to reset Password.\r\n"
			    				+ "Follow the mentioneed steps to reset password successfully:\n"
			    				+ "1. Copy the token: " + token +"\n"
			    				+ "2. Click on the link.\n"
			    				+ "3. Paste Exact token in the Token field.\n"
			    				+ "4. Enter New Password.\n"
			    				+ "5. Press CHANGE PASSWORD Button";
			    return constructEmail("Reset Password", message + " \r\n" + url, yugunUser);
	}
	
	// Setting Mail Credentials:
	private SimpleMailMessage constructEmail(String subject, String body,EbsUser yugunUser) {
		SimpleMailMessage email = new SimpleMailMessage();
	    email.setSubject(subject);
	    email.setText(body);
//	    user.getMail();
		email.setTo(yugunUser.getEmail());
//	    email.setTo("yagyesh03@gmail.com");
	    email.setFrom("yagyeshfms@gmail.com");
	    return email;
	}
	
	// Validate Password Reset Token:
	public String validatePasswordResetToken(String token) {
	    final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);	 
	    return !isTokenFound(passToken) ? "invalidToken" : null;
	}
	
	// Check if Password in DB or not:
	private boolean isTokenFound(PasswordResetToken passToken) {
	    return passToken != null;
	}
	
	public void creatMailVerificationToken(EbsUser yugunUser, String token) {
		MailVerificationToken mvToken = new MailVerificationToken(token, yugunUser);
		mvtRepo.save(mvToken);
	}
	
	// Construct an e-Mail to send mail-verification-token:
	public SimpleMailMessage constructMailVerificationTokenEmail(
			  String contextPath, String token, EbsUser yugunUser) {
			    String url = "http://localhost:7100/auth/user/verifyMail?token=" + token;
			    String message = "Hello "+yugunUser.getName()+". Click on the given URL to VERIFY your EMAIL Account.";
			    return constructEmail("e-Mail Address Verification", message + " \r\n" + url, yugunUser);
	}
	
	// Get VMToken From Database:
	public MailVerificationToken validateVMToken(String token) {
	    final MailVerificationToken passToken = mvtRepo.findByToken(token);	 
	    return !isTokenFound(passToken) ? null : passToken;
	}
		
	// Check if VMToken in DB or not:
	private boolean isTokenFound(MailVerificationToken passToken) {
	    return passToken != null;
	}

}
