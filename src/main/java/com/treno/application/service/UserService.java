package com.treno.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.dao.Dao;
import com.treno.application.dao.UserUtility;
import com.treno.application.dto.UserDto;
import com.treno.application.filter.UserFilter;
import com.treno.application.model.User;
import com.treno.application.model.builder.TrenoBuilder;
import com.treno.eccezzioni.InvalidPasswordException;
import com.treno.eccezzioni.UserNotFoundException;


public class UserService {
	
	@Autowired
	@Qualifier("UserDao")
	private UserUtility userDao;
	
	@Autowired
	private TrenoBuilder trenoBuilder;
	
	public Dao<User> getUserDao() {
		return userDao;
	}

	public void setUserDao(Dao<User> userDao) {
		this.userDao = (UserUtility) userDao;
	}
	
	public TrenoBuilder getTrenoBuilder() {
		return trenoBuilder;
	}

	public void setTrenoBuilder(TrenoBuilder trenoBuilder) {
		this.trenoBuilder = trenoBuilder;
	}

	public void registrazione(User user) {
	    String password = user.getPassword();
	    String email = user.getEmail();
	    String telefono = user.getTelefono();
	    String regPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}$";
	    String regEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	    String regTelefono = "^\\+?39?[-.\\s]?\\d{2,4}[-.\\s]?\\d{3,4}[-.\\s]?\\d{3,4}$";
	    if (password.matches(regPassword) && email.matches(regEmail) && telefono.matches(regTelefono)) {
	        userDao.save(user);
	    } else {
	        if (!password.matches(regPassword)) {
	            System.out.println("La password non è corretta");
	        }
	        if (!email.matches(regEmail)) {
	            System.out.println("L'email non è corretta");
	        }
	        if (!telefono.matches(regTelefono)) {
	            System.out.println("Il numero di telefono non è corretto");
	        }
	    }
		this.userDao = (UserUtility) userDao;
	}
	
	
    //Metodo registrazione
	@Transactional
    public String registra(UserDto userDto) {
        // Controlla se l'utente esiste già
        User utenteEsistente = userDao.findByUsername(userDto.getUsername());
        if (utenteEsistente != null) {
            // Se l'utente esiste, restituisci un messaggio di errore
            return "Utente già registrato con questo username!";
        }

        // Crea un nuovo oggetto User a partire dai dati del DTO
        User nuovoUtente = new User();
        nuovoUtente.setUsername(userDto.getUsername());
        nuovoUtente.setPassword(userDto.getPassword());  // Assicurati di usare un sistema di hashing per le password!
        nuovoUtente.setEmail(userDto.getEmail());

        // Aggiungere il controllo della presenza dell'utente
        userDao.save(nuovoUtente);

        return "Registrazione avvenuta con successo!";
    }
	

     
	public void logout() {}
	
	public void cancellaAccount(User user) {
		userDao.delete(user);
	}

	public User findById(long l) {
		return userDao.findById(l);
	}
	
    @Transactional
	public void update(User user) {
		userDao.update(user);
	}
    
    //Metodo login con eccezzioni
    public void login(UserDto userDto) {
        // Trova l'utente tramite il suo username
        User user = userDao.findByUsername(userDto.getUsername());

        // Controlla se l'utente esiste
        if (user == null) {
            throw new UserNotFoundException("Utente non trovato");
        }

        // Verifica la password (si presume che la password nel DB sia già hashata)
        if (!user.getPassword().equals(userDto.getPassword())) {
            throw new InvalidPasswordException("Password non corretta");
        }

        // Nessuna azione necessaria, l'autenticazione è avvenuta con successo
    }
    
    // Metodo per cercare l'utente tramite username (esistente)
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
    
    
    
    public List<User> filtraUtenti(UserFilter filtro, long userId) {
	    return ((UserService) userDao).filtraUtenti(filtro, userId);
	}

	

}
