package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.entity.Category;
import com.entity.Skill;

public interface CatgegoryRepository extends CrudRepository<Category, String>{
	public List<Category> findAll();
}
