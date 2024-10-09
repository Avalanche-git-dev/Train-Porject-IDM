package treno.test.dao;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.dao.Dao;
import com.treno.application.model.Motrice;

public class TestDAO {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfiguration.xml");
		
		//MotriceDao  mDao = (MotriceDao) ctx.getBean("motriceDao");
		//Allora o la finisci o la finisci, o ti metti a cambiare il sistema di generazione di proxy di spring, oppure quando io istanzio un oggetto che implementa dao, che allo stesso momento gestisce un em, non viene restituita una istanza 
		//bensi vieen restuituito un proxy, giustamente. Come faccio ad accedere all'istanza però se voglio ? uso l'interfaccia o uso autowired.
		@SuppressWarnings("unchecked")
		Dao<Motrice> mDao = (Dao<Motrice>) ctx.getBean("motriceDao");
		
		Motrice m =  (Motrice) ctx.getBean("deMotrice");
		
		
		
		
		
		
		
		mDao.save(m);
		
	    List<Motrice> allUsers = mDao.findAll();
	        allUsers.forEach(vagone -> System.out.println(vagone.toString()));
		
		
		for (String beanName : ctx.getBeanDefinitionNames()) {
			System.out.println("Bean --->" + beanName);
		}
		
		
		//System.out.println(rDao.toString());
		System.out.println(mDao.toString());
		ctx.close();

	}

}
