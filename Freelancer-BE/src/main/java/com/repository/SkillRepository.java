package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.Skill;

@Repository
public interface SkillRepository extends CrudRepository<Skill, String>{
	public List<Skill> findAll();
//	@Query("UPDATE customer c SET c.firstName = :firstName WHERE c.id = :id")
	@Query("Select s.skill_id, s.skill_name from Skill s where s.category.category_id= :catgegoryID")
	public List<Skill> findByCategory(@Param("catgegoryID") String catgegoryID);
}
