package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.entity.User;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.service.UserService;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class SignupController {

	@Autowired
	private UserService userService;
	
	@PostMapping(path= "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?>  checkUser (@RequestBody User user) {
		ObjectNode userreturn = userService.checkUser(user);
			if(userreturn.get("code").asText().equals("409")) {
				 return new ResponseEntity(userreturn,HttpStatus.UNAUTHORIZED);
			}
			else {
				 return new ResponseEntity(userreturn,HttpStatus.OK);
			}
	}
	

	@PostMapping(path= "/signup/checkEmail", consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?>  checkEmail (@RequestBody User user) {
		ObjectNode userreturn = userService.checkEmail(user);
			if(userreturn.get("code").asText().equals("409")) {
				 return new ResponseEntity(userreturn,HttpStatus.UNAUTHORIZED);
			}
			else {
				 return new ResponseEntity(userreturn,HttpStatus.OK);
			}
	}
	   

	@PostMapping(path= "/signup/checkUser", consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?>  checkUsername (@RequestBody User user) {
		ObjectNode userreturn = userService.checkUsername(user);
			if(userreturn.get("code").asText().equals("409")) {
				 return new ResponseEntity(userreturn,HttpStatus.UNAUTHORIZED);
			}
			else {
				 return new ResponseEntity(userreturn,HttpStatus.OK);
			}
	}
	
	@PostMapping(path= "/signup/withDetails", consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?>  signUpWithDetail (@RequestBody User user) {
		ObjectNode userreturn = userService.signUpWithDetail(user);
			if(userreturn.get("code").asText().equals("409")) {
				 return new ResponseEntity(userreturn,HttpStatus.UNAUTHORIZED);
			}
			else {
				 return new ResponseEntity(userreturn,HttpStatus.OK);
			}
	}

}
