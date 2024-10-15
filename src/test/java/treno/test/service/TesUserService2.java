package treno.test.service;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.dto.UserDto;
import com.treno.application.service.UserService;

public class TesUserService2 {

	public static void main(String[] args) {

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfigurationTest.xml");

		UserService userService = (UserService) ctx.getBean("UserService");

		UserDto user2 = new UserDto();
		user2.setUsername("Calogero");
		user2.setPassword("password456");
		user2.setEmail("Calogerissimo@example.com");
		
		//System.out(userService.login(user2));

	}

}
