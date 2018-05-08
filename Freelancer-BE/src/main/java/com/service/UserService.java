package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Project;
import com.entity.ProjectBid;
import com.entity.Skill;
import com.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.payload.LoginPayload;
import com.payload.SkillUser;
import com.repository.ProjectBidRepository;
import com.repository.ProjectRepository;
import com.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private ObjectMapper objectMapper;
	
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private ProjectBidRepository projectBidRepository;

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public List<User> login(LoginPayload user) {
		List<User> user1 =  userRepository.findByEmailAndPassword(user.getUseroremail(), user.getPassword());
		List<User> user2 = userRepository.findByUsernameAndPassword(user.getUseroremail(), user.getPassword());
		if((user1!= null && !user1.isEmpty())) {
			return user1;
		}
		if((user2!= null && !user2.isEmpty())) {
			return user2;
		}
		
		else {
			return null;
		}
	}

	public ObjectNode checkUser(User user) {

		    ObjectNode obj = objectMapper.createObjectNode();
			List<User> user1 =  userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
			List<User> user2 = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
			if(user1!= null && !user1.isEmpty()) {
				obj.put("code", "409");
				obj.put("success", "false");
				obj.put("inuse", "email");
				obj.put("message", "This email address is already in use.");
			}
			if(user2!= null && !user2.isEmpty()) {
				obj.put("code", "409");
				obj.put("success", "false");
				obj.put("inuse", "user");
				obj.put("message", "This username already exists, please choose another");
			}else {
				User usersaved = userRepository.save(user);
				obj.put("code", "200");
				obj.put("success", "true");
				obj.put("message", "New user created");
				obj.put("id", usersaved.getUserid());
				obj.put("username", usersaved.getUsername());
			}
		
		
		
		return obj;
		
	}

	public ObjectNode checkEmail(User user) {

		ObjectNode obj = objectMapper.createObjectNode();
		List<User> user1 =  userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		if(user1!= null && !user1.isEmpty()) {
			obj.put("code", "409");
			obj.put("success", "false");
			obj.put("inuse", "email");
			obj.put("message", "This email address is already in use.");
		}else {
			obj.put("code", "200");
			obj.put("success", "true");
		}
		return obj;
	}

	public ObjectNode checkUsername(User user) {

		ObjectNode obj = objectMapper.createObjectNode();
		List<User> user1 =  userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if(user1!= null && !user1.isEmpty()) {
			obj.put("code", "409");
			obj.put("success", "false");
			obj.put("inuse", "user");
			obj.put("message", "This username already exists, please choose another");
		}else {
			obj.put("code", "200");
			obj.put("success", "true");
		}
		return obj;
	}

	public User mapSkillsToUser(SkillUser skillUser) {
		Set<Skill> skillSet = new HashSet<Skill>();
		for(String skill : skillUser.getSkills()) {
			Skill s = new Skill();
			s.setSkill_id(skill);
			skillSet.add(s);
		}
		User u = new User(skillUser.getUserID());
		u.setSkills(skillSet);
        return userRepository.save(u);
	}

	public ObjectNode signUpWithDetail(User user) {

		ObjectNode obj = objectMapper.createObjectNode();
		List<User> p1 = userRepository.findByUserid(user.getUserid());
		User u = p1.get(0);
		u.setFirstname(user.getFirstname());
		u.setLastname(user.getLastname());
		u.setCity(user.getCity());
		u.setPhone(user.getPhone());
		u.setProf_headline(user.getProf_headline());
		userRepository.save(u);
		obj.put("code","200");
		obj.put("success", true);
		obj.put("message", "Added");
		obj.put("id", user.getUserid());
		return obj;
	}

	public Map<String,Object> getBiddedProjects(int userid) {
		
		List<Project> projects = projectRepository.findByBiddedProjectsUserUserid(userid);
		Map<String,Object> returnObj = new HashMap<String,Object>();
		returnObj.put("code", "200");
	    returnObj.put("projectsBiddedByMe", projects);
		return returnObj;
	
	}
	
	public Map<String,Object> getPostedProjects(int userid) {
		
		List<Project> projects = projectRepository.findByPostedProjectsUserUserid(userid);
		Map<String,Object> returnObj = new HashMap<String,Object>();
		returnObj.put("code", "200");
	    returnObj.put("projectsPostedByMe", projects);
		return returnObj;
	
	}

	public User getUserDetails(int userid) {
		
		List<User> user = userRepository.findByUserid(userid);
		return user.get(0);
	}


}