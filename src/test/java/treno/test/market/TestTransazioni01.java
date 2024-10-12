package treno.test.market;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.configuration.Factory;
import com.treno.application.configuration.FactoryConfiguration;
import com.treno.application.dao.Dao;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
import com.treno.application.model.builder.TBuilder;
import com.treno.application.model.builder.TrenoBuilder;
import com.treno.application.service.TransazioneService;

public class TestTransazioni01 {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfiguration.xml");
		TrenoBuilder builder = ctx.getBean(TBuilder.class);
		// System.out.println(builder);
		Dao <User> daoU= (Dao<User>) ctx.getBean("userDao");
		Dao <Treno> dao=(Dao<Treno>) ctx.getBean("trenoDao");
		
		Factory f = ctx.getBean(FactoryConfiguration.class);
		f.setMarca("de");

		Treno treno = builder.crealoRapido();
		
		User user1 = daoU.findById(1);
		user1.setNome("marco");
		daoU.update(user1);
		treno.setOwner(user1);
		
		
		
		
		
		dao.save(treno);
		
		
		
	
		
		
		
		User user2 =daoU.findById(2);
		user1.setNome("marco");
		user2.setNome("pippo");
		User user3 = daoU.findById(3);
		User user4 =daoU.findById(4);
		user1.setNome("fede");
		user2.setNome("gianni");
		
	
		
		
		
		
		
		daoU.update(user1);
		daoU.update(user2);
		
		
		treno.setOwner(user1);
		dao.update(treno);
		System.out.println("id del treno"+treno.getIdTreno()); 
		System.out.println("propietario del treno:"+treno.getOwner().getNome()); 
		
//		System.out.println(user1.getNome());
//		System.out.println(user2.getNome());
		TransazioneService transazione = ctx.getBean(TransazioneService.class);
		
		try {
			transazione.mettereInVendita(treno, user1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("sono qui prima del compra "+treno.getOwner().getNome());
		
		
		try {
			transazione.compraTreno(user2, treno);
			System.out.println(treno.getOwner().getNome());
		} catch (Exception e) {}
		System.out.println("sono dopo la vendita "+treno.getOwner().getNome());
		
		
		

	}

}
