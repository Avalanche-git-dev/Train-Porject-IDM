package treno.test.creazione;

import java.util.List;
import java.util.Random;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.dao.Dao;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
import com.treno.application.service.ValutazioneService;

public class TestCreazioneValutazioni {
	
	
	public static void main (String[]arg) {
	// Crea il contesto Spring e recupera i bean necessari
	AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfiguration.xml");

	ValutazioneService valutazioneService = (ValutazioneService) ctx.getBean("ValutazioneService");
	Dao<User> userDao = (Dao<User>) ctx.getBean("UserDao");
	Dao<Treno> trenoDao = (Dao<Treno>) ctx.getBean("TrenoDao");

	// Recupera tutti gli utenti e i treni dal database
	List<User> utenti = userDao.findAll();
	List<Treno> treni = trenoDao.findAll();

	Random random = new Random();

	// Cicla su ogni utente
	for (int i = 0; i < 5; i++) {
	    User user = utenti.get(i); // Prendi il prossimo utente
	    
	    // Cicla su ogni treno per ciascun utente
	    for (int j = 0; j < 5; j++) {
	        Treno treno = treni.get(j); // Prendi il prossimo treno
	        
	        // Genera un numero casuale tra 1 e 5 per il voto
	        int voto = random.nextInt(5) + 1;
	        
	        // Tenta di far valutare il treno all'utente
	        try {
	            valutazioneService.UserValutaTreno(user.getUserId(), treno.getIdTreno(), voto);
	        } catch (Exception e) {
	            e.printStackTrace();
	            e.getMessage(); // Stampa l'errore in caso di problemi (ad esempio se l'utente ha già valutato il treno)
	        }
	    }
	}

	// Chiudi il contesto Spring
	ctx.close();


	}

}

