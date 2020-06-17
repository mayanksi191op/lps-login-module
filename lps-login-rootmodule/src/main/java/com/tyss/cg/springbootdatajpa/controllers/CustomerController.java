package com.tyss.cg.springbootdatajpa.controllers;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tyss.cg.springbootdatajpa.entity.Applyloan;
import com.tyss.cg.springbootdatajpa.entity.User;
import com.tyss.cg.springbootdatajpa.response.Response;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CustomerController {
	
	@Autowired
	private RestTemplate template;
	
		
	//applications
	//recheck the response
		@PostMapping("/makeloan/{email}")
		public Response<Applyloan> makeLoan(@Valid @PathVariable String email, @RequestBody Applyloan applyloan){
			 HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		      HttpEntity<Applyloan> entity = new HttpEntity<Applyloan>(applyloan,headers);
		      
		      return template.exchange("http://localhost:8083/api/makeloan/" + email, HttpMethod.POST, entity, Response.class).getBody();
		}
	
	
	//User
		@GetMapping("/user/{email}")
		public Response<User> getById(@PathVariable String email) {
			HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		      HttpEntity <String> entity = new HttpEntity<String>(headers);
		      
		      return template.exchange("http://localhost:8083/api/user/" + email, HttpMethod.GET, entity, Response.class).getBody();
		}

			@GetMapping("/customers/{userid}")
			public Response<User> getByIdSustomer(@PathVariable int userid) {
				HttpHeaders headers = new HttpHeaders();
			      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			      HttpEntity <String> entity = new HttpEntity<String>(headers);
			      
			      return template.exchange("http://localhost:8083/api/customers/" + userid, HttpMethod.GET, entity, Response.class).getBody();
			}

		@PostMapping("/customers")
		public Response<User> saveCustomer(@Valid @RequestBody User user) {
			 HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		      HttpEntity<User> entity = new HttpEntity<User>(user,headers);
		      
		      return template.exchange("http://localhost:8083/api/customers", HttpMethod.POST, entity, Response.class).getBody();
		}
		
		
		@PutMapping("/customers/password/put")
		public Response<User> putCustomerPassword(@RequestBody User user){
			HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		      HttpEntity<User> entity = new HttpEntity<User>(user, headers);
		      
		      return template.exchange("http://localhost:8083/api/customers/password/put", HttpMethod.PUT, entity, Response.class).getBody();
		}
		
		@PutMapping("/customers/{email}/{password}")
		public Response<User> putCustomerPassword(@PathVariable String email, @PathVariable String password){
			HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		      HttpEntity<String> entity = new HttpEntity<String>(headers);
		      
		      return template.exchange("http://localhost:8083/api/customers/" + email + "/" + password, HttpMethod.PUT, entity, Response.class).getBody();
		}
		
		@PutMapping("/customers/put")
		public Response<User> putCustomerDetails(@RequestBody User user){
			HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		      HttpEntity<User> entity = new HttpEntity<User>(user, headers);
		      
		      return template.exchange("http://localhost:8083/api/customers/put", HttpMethod.PUT, entity, Response.class).getBody();
		}
		
		////recheck response
		@GetMapping("/customers/application/{email}")
		public Response<User> getCustomerApplications(@PathVariable String email){
			HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		      HttpEntity <String> entity = new HttpEntity<String>(headers);
		      
		      return template.exchange("http://localhost:8083/api/customers/application/" + email, HttpMethod.GET, entity, Response.class).getBody();
		}
		
}
