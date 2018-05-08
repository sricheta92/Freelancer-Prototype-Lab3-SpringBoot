package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Project;
import com.entity.ProjectBid;
import com.entity.ProjectUser;
import com.entity.Skill;
import com.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.payload.ProjectBidPayload;
import com.payload.ProjectSkill;
import com.payload.ProjectUserPayload;
import com.repository.ProjectBidRepository;
import com.repository.ProjectRepository;
import com.repository.ProjectUserRepository;
import com.repository.UserRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private ProjectUserRepository projectUserRepository;
	
	@Autowired
	private ProjectBidRepository projectBidRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	UserRepository userRepository;

	public ObjectNode addProject(Project project) {
		
		ObjectNode obj = objectMapper.createObjectNode();
		Project project1 = projectRepository.save(project);
		obj.put("code", "200");
		obj.put("success", "true");
		obj.put("message", "New project posted ");
		obj.put("projectid", project1.getProjectId());
		return obj;
		
	}

	public ObjectNode mapProjectToUser(ProjectUserPayload projectPayload) {
		

		User user = userRepository.findByUserid(projectPayload.getUserid()).get(0);
		Project project = projectRepository.findByProjectId(projectPayload.getProjectid()).get(0);
		ProjectUser projectUser = new ProjectUser();
		projectUser.setUser(user);
		projectUser.setProject(project);
		projectUser.setRole(projectPayload.getRole());
		ProjectUser pu = projectUserRepository.save(projectUser);
		
		user.getPostedProjects().add(pu);
	    userRepository.save(user);
	    projectRepository.save(project);
	    ObjectNode obj = objectMapper.createObjectNode();
		obj.put("code", "200");
		obj.put("success", "true");
		obj.put("message", "Users added to the project ");
		return obj;
	
	}
	
	public Project mapSkillsToProject(ProjectSkill projectSkill) {
		Set<Skill> skillSet = new HashSet<Skill>();
		for(String skill : projectSkill.getSkills()) {
			Skill s = new Skill();
			s.setSkill_id(skill);
			skillSet.add(s);
		}
		Project u = new Project(projectSkill.getProjectid());
		u.setSkills(skillSet);
        return projectRepository.save(u);
	}

	@Transactional
	public void mapFilesToProject(Project projectpayload) {
		List<Project> p1 = projectRepository.findByProjectId(projectpayload.getProjectId());
		Project p = p1.get(0);
		p.setDocument(projectpayload.getDocument());;
		projectRepository.save(p);
	}


	public ObjectNode bidproject(ProjectBidPayload projectBidPayload) {

		User user = userRepository.findByUserid(projectBidPayload.getUser_id()).get(0);
		Project project = projectRepository.findByProjectId(projectBidPayload.getProject_id()).get(0);
		ProjectBid projectBid = new ProjectBid();
		projectBid.setUser(user);
		projectBid.setProject(project);
		projectBid.setBid_days(projectBidPayload.getBid_days());
		projectBid.setBid_price(projectBidPayload.getBid_price());
		ProjectBid pb = projectBidRepository.save(projectBid);
		
		user.getBiddedProjects().add(pb);
	    userRepository.save(user);
	    projectRepository.save(project);
	    ObjectNode obj = objectMapper.createObjectNode();
		obj.put("code", "200");
		obj.put("success", "true");
		obj.put("message", "user bidded for project successfully ");
		return obj;
	}

	public Map<String,Object> getRecommendedProjects(int userid) {
		List<Project> recommendedProject = new ArrayList<Project>();
		User user = userRepository.findByUserid(userid).get(0);
		Iterable<Project> allProjects = projectRepository.findAll();
		
		Iterator iterator = allProjects.iterator();
	     while (iterator.hasNext () ) {
	         Project p = (Project) iterator.next ();
	         Set<Skill> projectSkills = p.getSkills();
	         Set<Skill> matchSKills = new HashSet<Skill>(projectSkills);
	         matchSKills.retainAll(user.getSkills());
	         
	         if(matchSKills.size()>0) {
	        	 recommendedProject.add(p);
	         }
	     }
	     Project[] r = new Project[recommendedProject.size()];
	     Map<String,Object> returnObj = new HashMap<String,Object>();
	    // ObjectNode obj = objectMapper.createObjectNode();
	     returnObj.put("code", "200");
	     returnObj.put("projectsWithSkills", recommendedProject.toArray(r));
		 return returnObj;
		
	}

}
