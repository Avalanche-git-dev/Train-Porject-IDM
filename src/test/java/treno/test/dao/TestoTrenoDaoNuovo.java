package treno.test.dao;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.Factory;
import com.treno.application.dao.Dao;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
import com.treno.application.model.builder.TBuilder;

import treno.FactoryConfiguration;

public class TestoTrenoDaoNuovo {

	@SuppressWarnings({ "unchecked", "unused" })
	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfiguration.xml");
		TBuilder builder = ctx.getBean(TBuilder.class);
		
		Factory f = ctx.getBean(FactoryConfiguration.class);
		f.setMarca("de");
		
		for (String beanName : ctx.getBeanDefinitionNames()) {
			System.out.println("Bean --->" + beanName);
		}
		
		//Treno t= builder.crealoRapido();
		User u1= new User();
		
		u1.setNome("pippo");
		Dao<User> UserDao= (Dao<User>) ctx.getBean("UserDao");
		UserDao.save(u1);
		
		
		
		
		//Tiro fuori il dao.
		
		Dao<Treno> TrenoDao = (Dao<Treno>) ctx.getBean("TrenoDao");
		Treno t  = TrenoDao.findById((long) 2);
		//System.out.println(t);
		//TrenoDao.save(t);
		
		t.setSigla("porco dio");
		TrenoDao.update(t);
		
		List <Treno> treni = TrenoDao.findAll();
		
		for (Treno treno: treni) {
			System.out.println(treno);
		}
		
		ctx.close();
		

	}

}
