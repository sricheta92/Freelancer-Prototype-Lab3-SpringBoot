package com.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.repository.UserRepository;
import com.service.UserService;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private UserService userService;
	
	

	@GetMapping(path= "/user/detail/{userid}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?>  getUserDetails (@PathVariable("userid") int userid) {
		ObjectNode obj = objectMapper.createObjectNode();
	    User users = userService.getUserDetails(userid);
	    if(users!=null) {
	    	obj.putPOJO("user", users);
	    	obj.putPOJO("skill", users.getSkills());
	    	obj.putPOJO("success", true);
	      return new ResponseEntity(obj,HttpStatus.OK);
	    }else {
	    	obj.putPOJO("success", false);
	      return new ResponseEntity(obj,HttpStatus.NOT_FOUND);  
	    }
	    
	}
	
	@GetMapping(path= "/user/biddedprojects/{userid}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?>  getBiddedProject ( @PathVariable("userid") int userid) {
		//ObjectNode obj = objectMapper.createObjectNode();
		Map<String,Object> obj  = userService.getBiddedProjects(userid);
	    return new ResponseEntity(obj,HttpStatus.OK);
	}
	

	@GetMapping(path= "/user/postedprojects/{userid}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?>  getPostedprojects ( @PathVariable("userid") int userid) {
		//ObjectNode obj = objectMapper.createObjectNode();
		Map<String,Object> obj  = userService.getPostedProjects(userid);
	    return new ResponseEntity(obj,HttpStatus.OK);
	}
}
