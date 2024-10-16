package treno.test.junit;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.treno.application.configuration.AppConfiguration;
import com.treno.application.dao.Dao;
import com.treno.application.dao.UserUtility;
import com.treno.application.model.Treno;
import com.treno.application.model.builder.TBuilder;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AppConfiguration.class })
public class DatabaseTest {

	@Autowired
	@Qualifier("UserDao")
	private UserUtility userDao; // UserDao

	@Autowired
	@Qualifier("TrenoDao")
	private Dao<Treno> trenoDao; // TrenoDao

	@Autowired
	@Qualifier("Builder")
	private TBuilder builder;

//
//	@Test
//	
//	public void testCreaUtenti() {
//		for (int i = 0; i < 20; i++) {
//			User user = new User();
//			user.setUsername("user" + i);
//			user.setPassword("Password + " + i + "!");
//			user.setEmail("user" + i + "@example.com");
//			user.setNome("Nome" + i);
//			user.setCognome("Cognome" + i);
//			user.setTelefono("+39 345 123456" + i);
//			user.setPortafoglio(100000.0 + i);
//
//			userDao.save(user); // Salva l'utente
//		}
//
//		System.out.println("20 utenti creati e salvati nel database.");
//	}
//	
//
//	@Test
//	public void testCreaTreni() {
//		builder.getFactory().setMarca("italiano");
//		Treno T = builder.crealoRapido();
//		T.setOwner(userDao.findById(65));
//		trenoDao.save(T);
//	}

}
