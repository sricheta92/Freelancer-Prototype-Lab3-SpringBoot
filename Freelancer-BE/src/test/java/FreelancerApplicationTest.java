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
	public void login() {
		
	}

}
