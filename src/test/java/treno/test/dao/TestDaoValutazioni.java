package treno.test.dao;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.Factory;
import com.treno.application.model.Valutazione;
import com.treno.application.model.builder.TBuilder;

import treno.FactoryConfiguration;

public class TestDaoValutazioni {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfiguration.xml");
		TBuilder builder = ctx.getBean(TBuilder.class);
		
		Factory f = ctx.getBean(FactoryConfiguration.class);
		f.setMarca("de");
		
		for (String beanName : ctx.getBeanDefinitionNames()) {
			System.out.println("Bean --->" + beanName);
		}
		
		Valutazione valutazione = new Valutazione();
		valutazione.setVotazione(5);
		
		
		ctx.close();

	}

}
