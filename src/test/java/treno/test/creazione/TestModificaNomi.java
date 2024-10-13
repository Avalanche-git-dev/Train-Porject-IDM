package treno.test.creazione;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.dao.Dao;
import com.treno.application.model.Treno;
import com.treno.application.model.User;

public class TestModificaNomi {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfiguration.xml");


		for (String beanName : ctx.getBeanDefinitionNames()) {
			System.out.println("Bean --->" + beanName);
		}
		
		
		// Modifica nomi
		Dao<User> UserDao = (Dao<User>) ctx.getBean("UserDao");
		
		
		
		//Configurazione nomi Leggibilità

		// U1
		User u1 = UserDao.findById(1);
		u1.setNome("pippo");
		u1.setCognome("rossi");
		UserDao.update(u1);

		// U2
		User u2 = UserDao.findById(2);
		u2.setNome("gianni");
		u2.setCognome("bianchi");
		UserDao.update(u2);

		// U3
		User u3 = UserDao.findById(3);
		u3.setNome("marco");
		u3.setCognome("verdi");
		UserDao.update(u3);

		// U4
		User u4 = UserDao.findById(4);
		u4.setNome("lorenzo");
		u4.setCognome("neri");
		UserDao.update(u4);

		// U5
		User u5 = UserDao.findById(5);
		u5.setNome("francesco");
		u5.setCognome("esposito");
		UserDao.update(u5);

		// U6
		User u6 = UserDao.findById(6);
		u6.setNome("andrea");
		u6.setCognome("romano");
		UserDao.update(u6);

		// U7
		User u7 = UserDao.findById(7);
		u7.setNome("paolo");
		u7.setCognome("mariani");
		UserDao.update(u7);

		// U8
		User u8 = UserDao.findById(8);
		u8.setNome("fabio");
		u8.setCognome("gallo");
		UserDao.update(u8);

		// U9
		User u9 = UserDao.findById(9);
		u9.setNome("mario");
		u9.setCognome("fontana");
		UserDao.update(u9);

		// U10
		User u10 = UserDao.findById(10);
		u10.setNome("luca");
		u10.setCognome("conti");
		UserDao.update(u10);
		
		
		
		
		
		
		
		

		// Treni
		Dao<Treno> TrenoDao = (Dao<Treno>) ctx.getBean("TrenoDao");
		
		
		List<Treno> treni = TrenoDao.findAll();

		for (Treno treno : treni) {
			System.out.println(treno);
		}

		ctx.close();

	}

}
