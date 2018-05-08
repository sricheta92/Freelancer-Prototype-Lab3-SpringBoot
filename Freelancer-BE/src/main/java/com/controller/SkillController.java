package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.entity.Category;
import com.entity.Skill;
import com.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.payload.SkillUser;
import com.service.SkillService;
import com.service.UserService;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class SkillController {
	
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private SkillService skillService;
	
	@Autowired 
	private UserService userService;
	

	@GetMapping(path= "/skill/allSkills", consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?>  getAllSkills () {
		ObjectNode obj = objectMapper.createObjectNode();
	    List<Skill> skills = skillService.getAllSkills();
	    if(skills!=null) {
	    	obj.putPOJO("skills", skills);
	      return new ResponseEntity(obj,HttpStatus.OK);
	    }else {
	    	obj.putPOJO("skills", null);
	      return new ResponseEntity(obj,HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	}
	
	@GetMapping(path= "/skill/allCategories", consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?>  getAllCategories () {
		ObjectNode obj = objectMapper.createObjectNode();
	    List<Category> categories = skillService.getAllCategories();
	    if(categories!=null) {
	    	obj.putPOJO("allCategories", categories);
	    	obj.putPOJO("success", true);
	      return new ResponseEntity(obj,HttpStatus.OK);
	    }else {
	    	obj.putPOJO("success", false);
	      return new ResponseEntity(obj,HttpStatus.NOT_FOUND);
	    }
	    
	}
	
	@GetMapping(path= "/skill/skillsByCategory", consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?>  getSkillsByCategory () {
		ObjectNode obj = objectMapper.createObjectNode();
	    List skillsByCategory = skillService.getSkillsByCategory();
	    if(skillsByCategory!=null) {
	    	obj.putPOJO("skillbyCategory", skillsByCategory);
	      return new ResponseEntity(obj,HttpStatus.OK);
	    }else {
	    	obj.putPOJO("success", false);
	      return new ResponseEntity(obj,HttpStatus.NOT_FOUND);
	    }
	    
	}
	
	@PostMapping(path= "/skill/withDetails", consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?>  mapSkillsToUser (@RequestBody SkillUser skillUser) {
		ObjectNode obj = objectMapper.createObjectNode();
	    User user = userService.mapSkillsToUser(skillUser);
	    return  new ResponseEntity(null,HttpStatus.OK);

	    
	}


}
