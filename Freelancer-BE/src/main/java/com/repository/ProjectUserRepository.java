package com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.entity.ProjectUser;

@Repository
public interface ProjectUserRepository  extends CrudRepository<ProjectUser, Integer>{

}
