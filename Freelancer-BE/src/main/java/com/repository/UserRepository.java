package com.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entity.User;
import com.payload.SignupPayload;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
    List<User> findByEmailAndPassword(String email,String password);
    List<User> findByUsernameAndPassword(String username,String password);
	List<User> findByUserid(int userid);
	List<User> findByEmail(String email);
	List<User> findByUsername(String username);

    
}