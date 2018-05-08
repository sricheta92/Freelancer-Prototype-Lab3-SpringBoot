package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.entity.ProjectBid;

@Repository
public interface ProjectBidRepository extends CrudRepository<ProjectBid, Integer> {
	
	public List<ProjectBid> findByUserUserid(int userid);

}
