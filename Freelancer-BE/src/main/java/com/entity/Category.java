package com.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "skill_category")
@Entity
public class Category {

	@Id
	private String category_id;
	
	private String skill_category_name;

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public String getSkill_category_name() {
		return skill_category_name;
	}

	public void setSkill_category_name(String skill_category_name) {
		this.skill_category_name = skill_category_name;
	}
}
