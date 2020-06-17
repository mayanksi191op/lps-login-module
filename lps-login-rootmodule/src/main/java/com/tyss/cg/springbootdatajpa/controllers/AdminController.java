package com.tyss.cg.springbootdatajpa.controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tyss.cg.springbootdatajpa.entity.LoanPrograms;
import com.tyss.cg.springbootdatajpa.entity.User;
import com.tyss.cg.springbootdatajpa.response.Response;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AdminController {
	
	@Autowired
	private RestTemplate template;
	
	
	//VIEW LOAN PROGRAMS
	@GetMapping("/loanprograms")
	public Response<List<LoanPrograms>> findAll() {
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      return template.exchange("http://localhost:8082/api/loanprograms", HttpMethod.GET, entity, Response.class).getBody();
	}
	
	@GetMapping("/loanprograms/{pageNo}/{itemsPerPage}")
	public Object getLoans(@PathVariable int pageNo, @PathVariable int itemsPerPage){
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return template.exchange("http://localhost:8082/api/loanprograms/" + pageNo + "/" + itemsPerPage, HttpMethod.GET, entity, Object.class).getBody();
	}
		
	@GetMapping("/loanprograms/{pageNo}/{itemsPerPage}/{fieldname}")
	public Object getLoans(@PathVariable int pageNo, @PathVariable int itemsPerPage, @PathVariable String fieldname){
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return template.exchange("http://localhost:8082/api/loanprograms/" + pageNo + "/" + itemsPerPage + "/" + fieldname, HttpMethod.GET, entity, Object.class).getBody();
	}
		
	@GetMapping("/loanprograms/{loan_no}")
	public Response<LoanPrograms> getById(@PathVariable int loan_no) {
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return template.exchange("http://localhost:8082/api/loanprograms/" + loan_no, HttpMethod.GET, entity, Response.class).getBody();
	}
	
	@DeleteMapping("/loanprograms/delete/{loan_no}")
	public Response<LoanPrograms> delete(@PathVariable int loan_no) {
		  HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return template.exchange("http://localhost:8082/api/loanprograms/delete/" + loan_no, HttpMethod.DELETE, entity, Response.class).getBody();
	}
	
	@PutMapping("/loanprograms/update")
	public Response<LoanPrograms> update(@Valid @RequestBody LoanPrograms loanPrograms) {
		 HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<LoanPrograms> entity = new HttpEntity<LoanPrograms>(loanPrograms, headers);
	      
	      return template.exchange("http://localhost:8082/api/loanprograms/update", HttpMethod.PUT, entity, Response.class).getBody();
	}
	
	@PostMapping("/loanprograms/add")
	public Response<LoanPrograms> save(@Valid @RequestBody LoanPrograms loanPrograms) {
		 HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<LoanPrograms> entity = new HttpEntity<LoanPrograms>(loanPrograms,headers);
	      
	      return template.exchange("http://localhost:8082/api/loanprograms/add", HttpMethod.POST, entity, Response.class).getBody();
	}
	
	
	//users
	@GetMapping("/clients")
	public Response<List<User>> viewClients() {
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return template.exchange("http://localhost:8082/api/clients", HttpMethod.GET, entity, Response.class).getBody();
	}

	@GetMapping("/clients/{pageNo}/{itemsPerPage}")
	public Object getClients(@PathVariable int pageNo, @PathVariable int itemsPerPage){
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return template.exchange("http://localhost:8082/api/clients/" + pageNo + "/" + itemsPerPage, HttpMethod.GET, entity, Object.class).getBody();
	}
		
	@GetMapping("/clients/{pageNo}/{itemsPerPage}/{fieldname}")
	public Object getClients(@PathVariable int pageNo, @PathVariable int itemsPerPage, @PathVariable String fieldname){
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return template.exchange("http://localhost:8082/api/clients/" + pageNo + "/" + itemsPerPage + "/" + fieldname, HttpMethod.GET, entity, Object.class).getBody();
	}
		
	@PostMapping("/clients")
	public Response<User> save(@Valid @RequestBody User user) {
		 HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<User> entity = new HttpEntity<User>(user,headers);
	      
	      return template.exchange("http://localhost:8082/api/clients", HttpMethod.POST, entity, Response.class).getBody();
	}

	@DeleteMapping("/clients/{userid}")
	public Response<User> deleteClient(@PathVariable int userid) {
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return template.exchange("http://localhost:8082/api/clients/" + userid, HttpMethod.DELETE, entity, Response.class).getBody();
	}

}
