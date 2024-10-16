package treno.test.service;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.dto.UserDTO;
import com.treno.application.service.UserService;

public class TestUserService {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfigurationTest.xml");
		
		UserService userService = (UserService) ctx.getBean("UserService");
		
		 // Prima istanza di UserDto
        UserDTO user1 = new UserDTO();
        user1.setUsername("Calogero");
        user1.setPassword("password123");
        user1.setEmail("Calogerou1@example.com");

        // Seconda istanza di UserDto
        UserDTO user2 = new UserDTO();
        user2.setUsername("Calogero");
        user2.setPassword("password456");
        user2.setEmail("Calogerissimo@example.com");
        
        userService.registra(user2);
        
        //Dao<User> userDao = (Dao<User>) ctx.getBean("UserDao");
		ctx.close();
		
		

	}

}
