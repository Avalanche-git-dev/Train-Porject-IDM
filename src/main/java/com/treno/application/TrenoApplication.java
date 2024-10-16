package com.treno.application;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.treno.application.configuration.FactoryConfiguration;
import com.treno.application.model.Motrice;

//@Configuration
//@ComponentScan
@Component
public class TrenoApplication {

	public static void main(String[] args) {

		// AbstractApplicationContext TrenoApp = new
		// AnnotationConfigApplicationContext(TrenoApplication.class);

//		Motrice m = (Motrice) TrenoApp.getBean("motrice");
//		System.out.println(m.toString());

		// TrenoBuilder b = new TBuilder ();
//		Treno t = b.crealoRapido();
//		System.out.println(t.toString());

//		TrenoBuilder tb = (TrenoBuilder) TrenoApp.getBean("TBuilder");
//		Treno t = tb.crealoRapido();
		// System.out.println(t.toString());
//		
//		Treno t = (Treno) TrenoApp.getBean("treno");
//		System.out.println(t);
//		System.out.println(t.getVagoni());

		// AbstractApplicationContext xmlCtx = new
		// ClassPathXmlApplicationContext("BeansConfiguration.xml");

//		for (String beanName : TrenoApp.getBeanDefinitionNames()) {
//	        System.out.println("Bean --->" + beanName);
//	    }
//	TrenoApp.close();

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfiguration.xml");
		
		for (String beanName : ctx.getBeanDefinitionNames()) {
			System.out.println("Bean --->" + beanName);
		}
		
		FactoryConfiguration factory = ctx.getBean(FactoryConfiguration.class);
		factory.setMarca("de");
		Motrice m = factory.creaMotrice();
		System.out.println(m.toString());
		ctx.close();

	}

}
