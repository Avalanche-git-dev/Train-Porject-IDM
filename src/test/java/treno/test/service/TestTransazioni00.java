package treno.test.service;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.configuration.Factory;
import com.treno.application.configuration.FactoryConfiguration;
import com.treno.application.model.builder.TBuilder;
import com.treno.application.model.builder.TrenoBuilder;

public class TestTransazioni00 {

	public static void main(String[] args) throws Exception {

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfiguration.xml");
		

	
		

		
		ctx.close();
		
		
}
}