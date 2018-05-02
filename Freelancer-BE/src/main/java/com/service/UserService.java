package com.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Project;
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

    	MessageDigest messageDigest =null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        messageDigest.update(user.getPassword().getBytes(),0,user.getPassword().length());
        String md5String = new BigInteger(1,messageDigest.digest()).toString(16);
        user.setPassword(md5String);
        
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

	public ObjectNode checkUser(User user) throws NoSuchAlgorithmException {

		    ObjectNode obj = objectMapper.createObjectNode();
			List<User> user1 =  userRepository.findByEmail(user.getEmail());
			List<User> user2 = userRepository.findByUsername(user.getUsername());
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
				MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		        messageDigest.update(user.getPassword().getBytes(),0,user.getPassword().length());
		        String md5String = new BigInteger(1,messageDigest.digest()).toString(16);
		        user.setPassword(md5String);
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
		List<User> user1 =  userRepository.findByEmail(user.getEmail());
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
		List<User> user1 =  userRepository.findByUsername(user.getUsername());
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