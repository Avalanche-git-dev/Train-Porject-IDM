package treno.test.filtri;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.dao.UserUtility;
import com.treno.application.dto.UserDto;
import com.treno.application.filter.UserFilter;
import com.treno.application.model.User;
import com.treno.application.service.UserService;

public class TestFiltroUser {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfiguration.xml");
		UserUtility userDao = (UserUtility) ctx.getBean("UserDao");
		
		UserFilter filtro = new UserFilter();
		
		filtro.setNome("UserN111");
		
		List<User> utentiFiltrati = userDao.filtraUserByParametro(filtro);
        
        if(utentiFiltrati.size() == 0) {
        	System.out.println("Nessun utente corrisponde a questa ricerca");
        } else {
        	for (User utenteFiltrato : utentiFiltrati) {
        		System.out.println(utenteFiltrato.toString());
        	}
        }
        
        ctx.close();
		
	}
	
}
