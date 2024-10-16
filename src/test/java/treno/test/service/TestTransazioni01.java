package treno.test.service;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.configuration.Factory;
import com.treno.application.dao.Dao;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
import com.treno.application.model.builder.TBuilder;

import treno.FactoryConfiguration;

public class TestTransazioni01 {

	@SuppressWarnings({ "resource", "unchecked", "unused" })
	public static void main(String[] args) {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfiguration.xml");
		TBuilder builder = ctx.getBean(TBuilder.class);
		// System.out.println(builder);
		Dao <User> daoU= (Dao<User>) ctx.getBean("userDao");
		Dao <Treno> dao=(Dao<Treno>) ctx.getBean("trenoDao");
		
		Factory f = ctx.getBean(FactoryConfiguration.class);
		f.setMarca("de");

		//Treno treno = builder.crealoRapido();
		

}
}
