package treno.test.service;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.dao.Dao;
import com.treno.application.model.User;
import com.treno.application.service.TrenoService;

public class TestTrenoService00 {

	@SuppressWarnings({ "unchecked", "unused" })
	public static void main(String[] args) {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfigurationTest.xml");
		TrenoService service = (TrenoService) ctx.getBean("TrenoService");
		Dao<User> userDao = (Dao<User>) ctx.getBean("UserDao");
		User user1 = userDao.findById(5);

		//service.creaTreno("HPPPPPRPPPPPPPP", user1,"italiano");
		

		ctx.close();
	}

}
