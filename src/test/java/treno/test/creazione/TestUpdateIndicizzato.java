package treno.test.creazione;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.dao.Dao;
import com.treno.application.model.User;

public class TestUpdateIndicizzato {
	
	
	public static void main(String[] args) {

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfiguration.xml");


		for (String beanName : ctx.getBeanDefinitionNames()) {
			System.out.println("Bean --->" + beanName);
		}
		
		
		// Modifica nomi
		Dao<User> UserDao = (Dao<User>) ctx.getBean("UserDao");
		
	
	
	
	

	String[] nomi = {"pippo", "gianni", "marco", "lorenzo", "francesco", "andrea", "paolo", "fabio", "mario", "luca"};
	String[] cognomi = {"rossi", "bianchi", "verdi", "neri", "esposito", "romano", "mariani", "gallo", "fontana", "conti"};

	for (long i = 1; i <= 10; i++) {
	    User user = UserDao.findById((long) i);  // cast da long a int per findById
	    user.setNome(nomi[(int) (i - 1)]);      // i-1 per accedere correttamente agli indici dell'array
	    user.setCognome(cognomi[(int) (i - 1)]);
	    UserDao.update(user);
	}

ctx.close();

}
	
}