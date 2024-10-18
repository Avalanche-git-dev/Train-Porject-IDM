package treno.test.dao;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.Factory;
import com.treno.application.model.Cargo;
import com.treno.application.model.builder.TBuilder;

import treno.FactoryConfiguration;

public class TestDAOTreno {

	public static void main(String[] args) {

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfiguration.xml");
		@SuppressWarnings("unused")
		TBuilder builder = ctx.getBean(TBuilder.class);
		//System.out.println(builder);
		
		Factory f = ctx.getBean(FactoryConfiguration.class);
		f.setMarca("de");
		
		for (String beanName : ctx.getBeanDefinitionNames()) {
			System.out.println("Bean --->" + beanName);
		}
		
////		Treno treno = ((Object) builder).crealoRapido();
//		@SuppressWarnings("unchecked")
//		Dao<Treno> trenoDao = (Dao<Treno>) ctx.getBean("trenoDao");
//		trenoDao.save(treno);
//		System.out.println(treno.toString());
//		
		//Cargo c = (Cargo) ctx.getBean("cargo");
		Cargo cDe = (Cargo) ctx.getBean("deCargo");
		
		System.out.println(cDe);
		

				
				
				
				
				
				
				
				
				ctx.close();
	}

}
