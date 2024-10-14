package com.treno.application;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public  class TrenoApplication {

	

	AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfiguration.xml");
//		
//		for (String beanName : ctx.getBeanDefinitionNames()) {
//			System.out.println("Bean --->" + beanName);
//		}
//		


}
