/* package treno.test.junit;

import java.util.List;
//
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
//
import com.treno.application.configuration.AppConfiguration;
import com.treno.application.dao.Dao;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
import com.treno.application.model.builder.TBuilder;
import com.treno.application.utility.UserUtility;
//
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AppConfiguration.class })
public class DatabaseTest {
//
	@Autowired
	@Qualifier("UserDao")
	private UserUtility userDao; // UserDao
//
	@Autowired
	@Qualifier("TrenoDao")
	private Dao<Treno> trenoDao; // TrenoDao
//
	@Autowired
	@Qualifier("Builder")
	private TBuilder builder;
//
////
////	@Test
////	
////	public void testCreaUtenti() {
////		for (int i = 0; i < 20; i++) {
////			User user = new User();
////			user.setUsername("user" + i);
////			user.setPassword("Password + " + i + "!");
////			user.setEmail("user" + i + "@example.com");
////			user.setNome("Nome" + i);
////			user.setCognome("Cognome" + i);
////			user.setTelefono("+39 345 123456" + i);
////			user.setPortafoglio(100000.0 + i);
////
////			userDao.save(user); // Salva l'utente
////		}
////
////		System.out.println("20 utenti creati e salvati nel database.");
////	}
////	
////
////	@Test
////	public void testCreaTreni() {
////		builder.getFactory().setMarca("italiano");
////		Treno T = builder.crealoRapido();
////		T.setOwner(userDao.findById(65));
////		trenoDao.save(T);
////	}
	@Test
	public void testPopolaUtenti() {
	    for (int i = 1; i <= 20; i++) {
	        User user = new User();
	        user.setUsername("user" + i);
	        user.setPassword("Password" + i + "!");
	        user.setEmail("user" + i + "@example.com");
	        user.setNome("Nome" + i);
	        user.setCognome("Cognome" + i);
	        user.setTelefono("+39 345 123456" + i);
	        user.setPortafoglio(100000.0 + i);
//	        
	        userDao.save(user); // Salva l'utente nel database
	    }
//
	    System.out.println("20 utenti creati e salvati nel database.");
	}
//	
////	
	@Test
	public void testPopolaTreni() {
	    // Recupera una lista di utenti dal database per assegnare un owner ai treni
	    List<User> utenti = userDao.findAll();
//	    
	    // Lista di URL di immagini casuali
	    String[] immagini = {
	        "https://example.com/train1.jpg",
	        "https://example.com/train2.jpg",
	        "https://example.com/train3.jpg",
	        "https://example.com/train4.jpg"
	    };
//
//	    // Crea 20 treni
	    for (int i = 0; i < 20; i++) {
	        builder.getFactory().setMarca("italiano"); // Imposta la marca
	        Treno treno = builder.crealoRapido();
//
//	        // Imposta lunghezza casuale (ad esempio tra 100 e 300 metri)
//	        //double lunghezza = 100 + Math.random() * 200;
	        treno.setLunghezza();
//
//	        // Imposta un URL immagine casuale
	        String immagineCasuale = immagini[(int) (Math.random() * immagini.length)];
	        treno.setImmagine(immagineCasuale);
//
//	        // Genera una sigla casuale (es. "TRN" seguito da un numero casuale)
	        String siglaCasuale = "TRN" + (1000 + i);
	        treno.setSigla(siglaCasuale);
//
//	        // Genera un nome casuale per il treno
	        String nomeCasuale = "Treno Rapido " + i;
	        treno.setNome(nomeCasuale);
//
//	        // Assegna il proprietario del treno da uno degli utenti recuperati
	        User owner = utenti.get(i % utenti.size()); // Cicla tra gli utenti
	        treno.setOwner(owner);
//
//	        // Due treni casuali messi in vendita
	        if (i == 5 || i == 12) {
	            treno.setInVendita(true);
	            treno.setPrezzoVendita(100000 + i * 5000); // Imposta un prezzo casuale
	        } else {
	            treno.setInVendita(false);
	        }
//
//	        // Salva il treno nel database
	        trenoDao.save(treno);
	    }
//
	    System.out.println("20 treni creati e salvati nel database.");
	}
//
//
//
}
*/
