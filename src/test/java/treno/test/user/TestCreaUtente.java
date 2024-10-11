package treno.test.user;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.treno.application.configuration.AppConfiguration;
import com.treno.application.model.User;
import com.treno.application.service.UserService;

public class TestCreaUtente {
	
	public static void main(String[] args) {
        
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        
        UserService uservice = context.getBean(UserService.class);
        User u = uservice.getUserDao().findById(11);
        int id = 10;
        User user = uservice.getUserDao().findById(id);
        if(user != null) {
        	uservice.login(id, user.getPassword());
        }
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
        	us.setUsername("user"+i);
        	us.setPassword("Abcde"+i+"!@");
        	us.setEmail("user"+i+"@gmail.com");
        	us.setTelefono("391234567890");
        	us.setNome("userN"+i);
        	us.setCognome("userC"+i);
        	us.setPortafoglio(100.0);
        	uservice.registrazione(us);
        }
        
    }

}
