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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.entity.Project;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.payload.ProjectBidPayload;
import com.payload.ProjectSkill;
import com.payload.ProjectUserPayload;
import com.service.ProjectService;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	ProjectService userService;
	

	@PostMapping(path= "/project/postprojects", consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?>  postProject (@RequestBody Project project) {
		ObjectNode projectreturn = projectService.addProject(project);
			if(projectreturn.get("code").asText().equals("409")) {
				 return new ResponseEntity(projectreturn,HttpStatus.UNAUTHORIZED);
			}
			else {
				 return new ResponseEntity(projectreturn,HttpStatus.OK);
			}
	}
	

	@PostMapping(path= "/project/mapProjectToUser", consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?>  mapProjectToUser (@RequestBody ProjectUserPayload project) {
		ObjectNode projectreturn = projectService.mapProjectToUser(project);
			if(projectreturn.get("code").asText().equals("409")) {
				 return new ResponseEntity(projectreturn,HttpStatus.UNAUTHORIZED);
			}
			else {
				 return new ResponseEntity(projectreturn,HttpStatus.OK);
			}
	}
	
	@PostMapping(path= "/project/bidproject", consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?>  bidProject (@RequestBody ProjectBidPayload projectBidPayload) {
		ObjectNode projectreturn = projectService.bidproject(projectBidPayload);
			if(projectreturn.get("code").asText().equals("409")) {
				 return new ResponseEntity(projectreturn,HttpStatus.UNAUTHORIZED);
			}
			else {
				 return new ResponseEntity(projectreturn,HttpStatus.OK);
			}
	}
	

	@PostMapping(path= "/project/mapSkillToProject", consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?>  mapSkillsToProject (@RequestBody ProjectSkill projectSkill) {
		ObjectNode obj = objectMapper.createObjectNode();
	    Project project = projectService.mapSkillsToProject(projectSkill);
	    obj.put("success", true);
	    obj.put("message", "Skills added to the project ");
	    return  new ResponseEntity(obj,HttpStatus.OK);

	    
	}
	
	
	@PostMapping(path= "/project/mapFilesToProject", consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?>  mapFilesToProject (@RequestBody Project projectpayload) {
		ObjectNode obj = objectMapper.createObjectNode();
	    projectService.mapFilesToProject(projectpayload);
	    obj.put("success", true);
	    obj.put("message", "Files added to the project ");
	    return  new ResponseEntity(obj,HttpStatus.OK);
	}
	

	@GetMapping(path= "/project/mapRecommendedProjects/{userid}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?>  mapRecommendedProjects ( @PathVariable("userid") int userid) {
		Map<String,Object> obj =userService.getRecommendedProjects(userid);
		return  new ResponseEntity(obj,HttpStatus.OK);
		
	}


}
