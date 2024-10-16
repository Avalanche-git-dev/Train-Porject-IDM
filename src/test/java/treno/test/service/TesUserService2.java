package treno.test.service;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.dto.UserDTO;
import com.treno.application.service.UserService;

public class TesUserService2 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfigurationTest.xml");

		UserService userService = (UserService) ctx.getBean("UserService");

		UserDTO user2 = new UserDTO();
		user2.setUsername("Calogero");
		user2.setPassword("password456");
		user2.setEmail("Calogerissimo@example.com");

		// System.out(userService.login(user2));
		ctx.close();
	}

}
