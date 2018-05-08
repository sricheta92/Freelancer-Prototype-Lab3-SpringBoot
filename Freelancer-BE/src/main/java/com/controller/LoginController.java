package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.entity.User;
import com.payload.LoginPayload;
import com.service.UserService;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(path= "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?>  checkUser (@RequestBody LoginPayload user) {
	    List<User> userAuthentic = userService.login(user);
	    System.out.println(userAuthentic);
	    if(userAuthentic!=null) {
	      return new ResponseEntity(userAuthentic.get(0),HttpStatus.OK);
	    }else {
	      return new ResponseEntity(null,HttpStatus.UNAUTHORIZED);
	    }
	    
	}


}
