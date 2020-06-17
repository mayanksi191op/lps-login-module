package com.tyss.cg.springbootdatajpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.cg.springbootdatajpa.entity.User;
import com.tyss.cg.springbootdatajpa.exception.InvalidCredentialsException;
import com.tyss.cg.springbootdatajpa.exception.UserNotAllowedException;
import com.tyss.cg.springbootdatajpa.repository.UserRepository;
import com.tyss.cg.springbootdatajpa.response.JwtResponse;
import com.tyss.cg.springbootdatajpa.services.JwtUtil;
import com.tyss.cg.springbootdatajpa.services.UserServices;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil; 
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserDetailsService userDetailsService;
	
	//jwt
		@PostMapping("/login")
		public ResponseEntity<?> login(@RequestBody User register) throws Exception{

			try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(register.getEmail(),register.getPassword()));
			} catch(DisabledException de) {
				throw new UserNotAllowedException("User Disabled!!!");

			} catch(BadCredentialsException bce) {
				//we should  use loggers here
				throw new InvalidCredentialsException("Invalid Credentials!!! Please enter correct credentials.");

			}// End of try catch

			final UserDetails userDetails = userDetailsService.loadUserByUsername(register.getEmail());
			final String email = register.getEmail();

			User user = userRepo.getByEmail(register.getEmail());
			String role = user.getRole();

			final String jwt = jwtUtil.generateToken(userDetails);
			final int userid = user.getUserid();

			return ResponseEntity.ok(new JwtResponse(jwt, email, role, userid, false));//doubt
		}// End of login()
}
