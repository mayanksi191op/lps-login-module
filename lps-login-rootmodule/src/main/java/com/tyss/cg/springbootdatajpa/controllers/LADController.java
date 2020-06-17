package com.tyss.cg.springbootdatajpa.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tyss.cg.springbootdatajpa.entity.Applyloan;
import com.tyss.cg.springbootdatajpa.response.Response;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LADController {
	
	@Autowired
	private RestTemplate template;
	
	//Applications
	@GetMapping("/application")
	public Response<List<Applyloan>> getAllApplications(){
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return template.exchange("http://localhost:8081/api/application", HttpMethod.GET, entity, Response.class).getBody();
	}
	
	@GetMapping("/application/{appId}")
	public Response<Applyloan> getApplicationById(@PathVariable int appId){
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return template.exchange("http://localhost:8081/api/application/" + appId, HttpMethod.GET, entity, Response.class).getBody();
	}
	
	@DeleteMapping("/application/{appId}")
	public Response<Applyloan> deleteProgram(@PathVariable int appId) {
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return template.exchange("http://localhost:8081/api/application/" + appId, HttpMethod.DELETE, entity, Response.class).getBody();
	}
	
	@GetMapping("/application/requested/")
	public Response<List<Applyloan>> requestedApplications(){
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return template.exchange("http://localhost:8081/api/application/requested/", HttpMethod.GET, entity, Response.class).getBody();
	}
		
	@GetMapping("/application/requested/{pageNo}/{itemsPerPage}")
	public Object requestedApplication(@PathVariable int pageNo, @PathVariable int itemsPerPage){
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return template.exchange("http://localhost:8081/api/application/requested/" + pageNo + "/" + itemsPerPage, HttpMethod.GET, entity, Object.class).getBody();
	}

	@GetMapping("/application/requested/{pageNo}/{itemsPerPage}/{fieldname}")
	public Object sortRequestedApplication(@PathVariable int pageNo, @PathVariable int itemsPerPage, @PathVariable String fieldname){
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return template.exchange("http://localhost:8081/api/application/requested/" + pageNo + "/" + itemsPerPage + "/" +fieldname, HttpMethod.GET, entity, Object.class).getBody();
	}
	

	@GetMapping("/application/rejected/")
	public Response<List<Applyloan>> rejectedApplications(){
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return template.exchange("http://localhost:8081/api/application/rejected/", HttpMethod.GET, entity, Response.class).getBody();
	}
		
	@GetMapping("/application/rejected/{pageNo}/{itemsPerPage}")
	public Object rejectedApplications(@PathVariable int pageNo, @PathVariable int itemsPerPage){
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return template.exchange("http://localhost:8081/api/application/rejected/" + pageNo + "/" + itemsPerPage, HttpMethod.GET, entity, Object.class).getBody();
	}

	@GetMapping("/application/rejected/{pageNo}/{itemsPerPage}/{fieldname}")
	public Object sortRejectedApplications(@PathVariable int pageNo, @PathVariable int itemsPerPage, @PathVariable String fieldname){
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return template.exchange("http://localhost:8081/api/application/rejected/" + pageNo + "/" + itemsPerPage + "/" +fieldname, HttpMethod.GET, entity, Object.class).getBody();
	}

	@GetMapping("/application/approved/")
	public Response<List<Applyloan>> approvedApplications(){
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return template.exchange("http://localhost:8081/api/application/approved/", HttpMethod.GET, entity, Response.class).getBody();
	}
	
	@GetMapping("/application/approved/{pageNo}/{itemsPerPage}")
	public Object approvedApplication(@PathVariable int pageNo, @PathVariable int itemsPerPage){
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return template.exchange("http://localhost:8081/api/application/approved/" + pageNo + "/" + itemsPerPage, HttpMethod.GET, entity, Object.class).getBody();
	}

	@GetMapping("/application/approved/{pageNo}/{itemsPerPage}/{fieldname}")
	public Object sortApprovedApplication(@PathVariable int pageNo, @PathVariable int itemsPerPage, @PathVariable String fieldname){
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return template.exchange("http://localhost:8081/api/application/approved/" + pageNo + "/" + itemsPerPage + "/" + fieldname, HttpMethod.GET, entity, Object.class).getBody();
	}
	
	@PutMapping("/application/setapprove/{loanid}")
	public Response<Applyloan> setApproved(@PathVariable int loanid){
		 HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      
	      return template.exchange("http://localhost:8081/api/application/setapprove/" + loanid, HttpMethod.PUT, entity, Response.class).getBody();
	}
	
	@PutMapping("/application/setreject/{loanid}")
	public Response<Applyloan> setRejected(@PathVariable int loanid){
		 HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      
	      return template.exchange("http://localhost:8081/api/application/setreject/" + loanid, HttpMethod.PUT, entity, Response.class).getBody();
	}
	
}	
