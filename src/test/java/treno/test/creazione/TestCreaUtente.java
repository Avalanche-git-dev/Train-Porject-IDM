package treno.test.creazione;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.dao.UserUtility;
import com.treno.application.model.User;
import com.treno.application.service.UserService;

public class TestCreaUtente {
	
	@SuppressWarnings({ "resource", "unused" })
	public static void main(String[] args) {
        
		ApplicationContext context = new ClassPathXmlApplicationContext("BeansConfiguration.xml");
        
        UserService uservice = context.getBean(UserService.class);
        UserUtility udao = (UserUtility) context.getBean("UserDao");
        User u = uservice.getUserDao().findById((long) 11);
        long id = 10;
//        User user = uservice.getUserDao().findById(id);
//        
//        if(user != null) {
//        	uservice.login(userDto);
//        }
        if(u != null) {
        	System.out.println("Trovato e rimosso");
        	uservice.cancellaAccount(u);
        } else {
        	System.out.println("Utente non presente");
        }
        List<User> list = uservice.getUserDao().findAll();
        if(list.size() > 0) System.out.println("Ci sono degli utenti nel db");
        for(int i = list.size(); i < list.size() + 30; i++) {
        	User us = new User();
        	us.setUsername("USer"+i);
        	us.setPassword("Abcde"+i+"!@");
        	us.setEmail("user"+i+"@gmail.com");
        	us.setTelefono("391234567890");
        	us.setNome("userN"+i);
        	us.setCognome("userC"+i);
        	us.setPortafoglio(100000.0);
        	udao.save(us);
        }
        
    }
	
	
	
	

}
