package treno.test.user;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.configuration.Factory;
import com.treno.application.configuration.FactoryConfiguration;
import com.treno.application.dao.Dao;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
import com.treno.application.model.builder.TBuilder;
import com.treno.application.model.builder.TrenoBuilder;
import com.treno.application.service.UserService;

public class TestUserCreaTreno {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfiguration.xml");
		
        UserService uservice = ctx.getBean(UserService.class);
        Factory f = ctx.getBean(FactoryConfiguration.class);
		f.setMarca("de");
        
        User user = uservice.getUserDao().findById(15);
        
        Treno t = uservice.getTrenoBuilder().crealoRapido(user);
        
        @SuppressWarnings("unchecked")
		Dao<Treno> trenoDao = (Dao<Treno>) ctx.getBean("trenoDao");
		trenoDao.update(t);
        
		ctx.close();
	}

}
