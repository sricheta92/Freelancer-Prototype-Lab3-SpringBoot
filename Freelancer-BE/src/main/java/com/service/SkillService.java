package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Category;
import com.entity.Skill;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.payload.SkillUser;
import com.repository.CatgegoryRepository;
import com.repository.SkillRepository;

@Service
public class SkillService {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private SkillRepository skillRepository;
	

	@Autowired
	private CatgegoryRepository catgegoryRepository;


	public List<Skill> getAllSkills() {
		return skillRepository.findAll();
	}

	public List<Category> getAllCategories() {
		return catgegoryRepository.findAll();
	}

	public List getSkillsByCategory() {
		ObjectNode obj =null;
		List a =null;
		List returnList = new ArrayList();
		List<Category> category = catgegoryRepository.findAll();
		for(Category cat : category) {
			obj = objectMapper.createObjectNode();
			a = skillRepository.findByCategory(cat.getCategory_id());
			obj.put("category_id", cat.getCategory_id());
			obj.putPOJO("skills", a);
			returnList.add(obj);
		}
		
		return returnList;
	}

	

}
