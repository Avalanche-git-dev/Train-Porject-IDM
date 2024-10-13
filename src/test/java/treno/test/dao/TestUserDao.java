package treno.test.dao;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.dao.Dao;
import com.treno.application.model.User;

public class TestUserDao {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfiguration.xml");
		for (String beanName : ctx.getBeanDefinitionNames()) {
			System.out.println("Bean --->" + beanName);
		}
		
		@SuppressWarnings("unchecked")
		Dao<User> UserDao = (Dao<User>) ctx.getBean("UserDao");
		
	    User u1 = UserDao.findById(5);
		
		
		
		System.out.println(u1);
		System.out.println(u1.getNome());
		List<User> users = UserDao.findAll();
		
		for(User u:users) {
			
		System.out.println(u.getNome());
		}
		
		ctx.close();
		
		
		
		
		

	}

}
