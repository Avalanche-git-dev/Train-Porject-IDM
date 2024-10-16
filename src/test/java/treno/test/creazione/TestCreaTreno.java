package treno.test.creazione;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.configuration.Factory;
import com.treno.application.dao.Dao;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
import com.treno.application.model.builder.TBuilder;

import treno.FactoryConfiguration;

public class TestCreaTreno {

	@SuppressWarnings({ "unchecked", "unused" })
	public static void main(String[] args) {
		

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfiguration.xml");
		TBuilder builder = ctx.getBean(TBuilder.class);
		// System.out.println(builder);

		Factory f = ctx.getBean(FactoryConfiguration.class);
		f.setMarca("de");
		
		
		Dao<User> UserDao = (Dao<User>) ctx.getBean("UserDao");
		Dao<Treno> TrenoDao = (Dao<Treno>) ctx.getBean("TrenoDao");
		
		List <User> utenti = UserDao.findAll();
		
		List<Treno> treniRapidi = new ArrayList<Treno>();
		
//		for(int i=0 ; i<10 ; i++) {
//			treniRapidi.add(builder.crealoRapido());
//		}
		
		int numeroUtenti = utenti.size();
		int nRapidi= treniRapidi.size();
		
		for (int i = 0; i < treniRapidi.size(); i++) {
		    // Usa l'indice modulo dimensione della lista utenti per ciclare sugli utenti
		    User owner = utenti.get(i % numeroUtenti);
		    treniRapidi.get(i).setOwner(owner);
		}
		
		//Salvo i treni
		for (int i=0; i<nRapidi ; i++) {
			TrenoDao.save(treniRapidi.get(i));
		}
		
		
		//Aggiorno gli utenti
		for (int i=0; i<numeroUtenti ; i++) {
			UserDao.update(utenti.get(i));
		}
		
		
		
		
		
		
		
		
		//Chiudo il context
		ctx.close();
		
		
		
		
		
		
		}
		
		
	
	
	
	

	}


