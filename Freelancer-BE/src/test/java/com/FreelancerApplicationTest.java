package com;
import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.entity.User;
import com.payload.LoginPayload;
import com.payload.ProjectUserPayload;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FreelancerApplicationTest {


	public static RestTemplate restTemplateObj;
	public static String email;
	public static String userId;
	
	@Bean
	public static RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@BeforeClass
	public static void prapareForeTest() {
		restTemplateObj = restTemplate();
		Random rand = new Random();
		int number = rand.nextInt(50) + 1;
		email = Integer.toString(number)+"@gmail.com";
	}

	@Test
	public void doLogin() {
		User user = new User();
		user.setEmail(email);
		user.setUsername("admin5");
		user.setPassword("admin5");
		user.setRole("Employer");
		ResponseEntity<String> res = restTemplateObj.postForEntity("http://localhost:9090/signup", user, String.class);
		assertEquals(200, res.getStatusCodeValue());
		
	}
	
	@Test
	public void fetchAllUsers() {
		User user = new User();
		user.setEmail("22@gmail.com");
		ResponseEntity<String> res = restTemplateObj.postForEntity("http://localhost:9090/signup/checkEmail", user, String.class);
		assertEquals(200, res.getStatusCodeValue());
	}
	
	@Test
	public void fetchAllProjects() {
		User user = new User();
		user.setEmail("22@gmail.com");
		ResponseEntity<String> res = restTemplateObj.postForEntity("http://localhost:9090/signup/checkEmail", user, String.class);
		assertEquals(200, res.getStatusCodeValue());
		
	}
	
	@Test
	public void doSignup() {
		User user = new User();
		user.setUsername("admin5");
		ResponseEntity<String> res = restTemplateObj.postForEntity("http://localhost:9090/signup/checkUser", user, String.class);
		assertEquals(200, res.getStatusCodeValue());
		
	}
	
	@Test
	public void fetchUserById() {
		ProjectUserPayload user = new ProjectUserPayload();
		user.setUserid(1);
		user.setProjectid(2);
		user.setRole("Employer");
		ResponseEntity<String> res = restTemplateObj.postForEntity("http://localhost:9090/project/mapProjectToUser", user, String.class);
		assertEquals(200, res.getStatusCodeValue());
		
	}

}
