package treno.test.service;

import java.util.List;
import java.util.Random;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.dao.Dao;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
import com.treno.application.service.ValutazioneService;

public class TestValutazioniService00 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		
		
	
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfiguration.xml");

		ValutazioneService valutazioneService = (ValutazioneService) ctx.getBean("ValutazioneService");
		Dao<User> userDao = (Dao<User>) ctx.getBean("UserDao");
		Dao<Treno> trenoDao = (Dao<Treno>) ctx.getBean("TrenoDao");

		// tiriamo fuori gli utenti speriamo in bene.
		List<User> utenti = userDao.findAll();
		List<Treno> treni = trenoDao.findAll();

		Random random = new Random();

		// Test per ogni utente e treno
		for (int i = 0; i < 5; i++) {
			User user = utenti.get(i); // Prendi il prossimo utente
			Treno treno = treni.get(i); // Prendi il prossimo treno
			// Genera un numero casuale tra 1 e 5
			int voto = random.nextInt(5) + 1;
			// I voti che gli utenti daranno ai treni

			try {
				valutazioneService.UserValutaTreno(user.getUserId(), treno.getIdTreno(), voto);
			} catch (Exception e) {
				e.printStackTrace();
				e.getMessage();
			}

		}

		ctx.close();

	}
}
