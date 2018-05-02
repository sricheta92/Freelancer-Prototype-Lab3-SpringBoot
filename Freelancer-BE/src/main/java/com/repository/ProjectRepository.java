package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entity.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer>{
	
	List<Project> findByProjectId(int projectId);
	List<Project> findByBiddedProjectsUserUserid(int userid);
	List<Project> findByPostedProjectsUserUserid(int userid);

}
