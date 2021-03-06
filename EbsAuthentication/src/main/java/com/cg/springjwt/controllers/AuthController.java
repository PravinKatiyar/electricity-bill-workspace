package com.cg.springjwt.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.cg.springjwt.exception.UserNotFoundException;
import com.cg.springjwt.mail.MailService;
import com.cg.springjwt.models.*;
import com.cg.springjwt.payload.request.*;
import com.cg.springjwt.payload.response.*;
import com.cg.springjwt.repository.*;
import com.cg.springjwt.security.jwt.JwtUtils;
import com.cg.springjwt.security.services.UserDetailsImpl;


@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordResetTokenRepo passwordTokenRepository;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	MailService mailService;
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
	private Environment env;

	
	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllEbsUsers(){
		System.out.println(userRepository.findAllUsers());
		return ResponseEntity.ok(userRepository.findAllUsers());
	}
	
//SIGN IN FUNCTION
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		EbsUser ebsUser =  userRepository.findByUsername(userDetails.getUsername()).orElse(null);
		
		try
		{
			if(ebsUser.isMailVerfication()) {
				
				return ResponseEntity.ok(
						new JwtResponse(jwt, ebsUser.getName(), ebsUser.getEmail(), ebsUser.getMobile(), ebsUser.getUsername(), ebsUser.getGender(), ebsUser.getbDay(), ebsUser.getAddress(), 
								roles.get(0)));
			}
			else {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Activate Account by clicking on link sent to your e-Mail Id"));
			}
		}
		catch(NullPointerException np) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid User Name"));
		}
		catch(Exception ex) {
		}
		return null;
	}

//SIGN UP FUNCTION
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		EbsUser yugunUser = new EbsUser(signUpRequest.getName(), signUpRequest.getEmail(),
				signUpRequest.getMobile(), signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()), signUpRequest.getGender(), false, signUpRequest.getbDay(), signUpRequest.getAddress());

		
		Set<Role> roles = new HashSet<>();

		Role userRole = new Role(ERole.CUSTOMER);
		roles.add(userRole);

		yugunUser.setRoles(roles);
	//	yugunUser.setMailVerfication(true);////////////////////edit
		try {
			mailService.sendMVTMail(yugunUser);
			userRepository.save(yugunUser);
		}
		catch(DataIntegrityViolationException ex) 
		{
			System.out.println("DataIntegrityViolationException");
		}
		
		mailService.sendMVTMail(yugunUser);
		
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	
// EDIT & SAVE User Details
	//-->
	
	@PostMapping("/editUser")
	public ResponseEntity<?> editUser(@Valid @RequestBody SignupRequest signUpRequest) {
		
		if (!userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: User Does not exist!"));
		}
		
		EbsUser oldUser = userRepository.findByUsername(signUpRequest.getUsername()).orElse(null);
		
		userRepository.delete(oldUser);
		
		EbsUser yugunUser = new EbsUser(signUpRequest.getName(), signUpRequest.getEmail(),
				signUpRequest.getMobile(), signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()), signUpRequest.getGender(), true, signUpRequest.getbDay(), signUpRequest.getAddress());

		
		Set<Role> roles = new HashSet<>();

		Role userRole = new Role(ERole.ADMIN);
		roles.add(userRole);

		yugunUser.setRoles(roles);

		try {
			userRepository.save(yugunUser);
		}
		catch(DataIntegrityViolationException ex) 
		{
			System.out.println("DataIntegrityViolationException");
		}

		return ResponseEntity.ok(new MessageResponse("User Updation successfully!"));
	}
	
//	<-- Edit & Save User Details
	
//RESET PASSWORD:
	//-->
	//Reset Password that return Generic Response:
	@GetMapping("/user/resetPassword/{email}")
	public GenericResponse resetPassword(HttpServletRequest request, 
	  @PathVariable("email") String userEmail) throws UserNotFoundException {
		EbsUser yugunUser = userRepository.findByEmail(userEmail);
	    if (yugunUser == null) {
	        throw new UserNotFoundException("USER NOT FOUND");
	    }
	    try {
		    String token = UUID.randomUUID().toString();
		    mailService.createPasswordResetTokenForUser(yugunUser, token);
		    mailSender.send(mailService.constructResetTokenEmail(env.getProperty("spring.defaultURL"), token, yugunUser));
	    }
        catch(Exception ex) {
        	ex.printStackTrace();
        }
	    return new GenericResponse("message.resetPasswordEmail");
	}
	
	
	
	
	
	//Function to Show change in password:	
	@GetMapping("/user/changePassword")
	public ModelAndView showChangePasswordPage(Locale locale, Model model, 
	  @RequestParam("token") String token) {
//	    String result = validatePasswordResetToken(token);
	    	ModelAndView mav = new ModelAndView();
	        mav.addObject("token", token);
	        mav.setViewName("changePass");
	        return mav;
	}
	
	
	
	
	//Function to process save password request:
	@GetMapping("/user/savePassword/{pass}/{token}")
	public GenericResponse savePassword(@PathVariable("pass") String pass,
										@PathVariable("token") String token) {
	    String result = mailService.validatePasswordResetToken(token);
	 
	    if(result != null) {
	        return new GenericResponse("Invalid Token");
	    }
	 
	    EbsUser yugunUser = mailService.getUserByPasswordResetToken(token);
	    if(yugunUser != null) {
	    	mailService.changeUserPassword(yugunUser, pass);
	        return new GenericResponse("Password Changed Successfully");
	    } else {
	        return new GenericResponse("Task Failed");
	    }

	}
	
	
	
// <-- RESET PASSWORD

//-------------------------------------------//
	
	// DELETE USER -->
	
		@DeleteMapping("/deleteYugunUser/")
		public void deleteAccount(@RequestBody EbsUser user ) {		
			userRepository.delete(user);
		}
		
		// <-- DELETE USER
		
		// GET ALL USER -->
		
		@GetMapping("/getAllYugunUser/")
		public ResponseEntity<?> deleteAccount() {		
			return new ResponseEntity<Object>(userRepository.findAll(), HttpStatus.OK);
		}
		
		// <-- Get All USERS
	
//-------------------------------------------//
	
// ACCOUNT MAIL ID VERIFICATION -->
	
	// Verify Mail & Acivate Account:
	@GetMapping("/user/verifyMail")
	public ModelAndView verifyingMail(Locale locale, Model model,
			@RequestParam("token") String token) {
		MailVerificationToken mvt = mailService.validateVMToken(token);
		if(mvt.equals(null))
		{
			return null;
		}
		mvt.getUser().setMailVerfication(true);
		userRepository.save(mvt.getUser());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("MailVerificationCard");
		return mav;
	}	
	

	
}