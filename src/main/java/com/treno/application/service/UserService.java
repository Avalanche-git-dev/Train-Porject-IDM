package com.treno.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.dao.Dao;
import com.treno.application.dao.UserUtility;
import com.treno.application.dto.UserDto;
import com.treno.application.model.User;



public class UserService {
	
	@Autowired
	@Qualifier("UserDao")
	private UserUtility userDao;
	
	public Dao<User> getUserDao() {
		return userDao;
	}

	public void setUserDao(Dao<User> userDao) {
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
	
	//Metodo Login
    public String login(UserDto userDto) {
        // Controlla se l'utente esiste
        User utenteEsistente = userDao.findByUsername(userDto.getUsername());
        if (utenteEsistente == null) {
            // Se l'utente non esiste, restituisci un messaggio di errore
            return "Username o password errati!";
        }

        if (!userDto.getPassword().equals(utenteEsistente.getPassword())) {
            // Se la password non corrisponde, restituisci un messaggio di errore
            return "Username o password errati!";
        }

        // Se tutto è corretto, restituisci un messaggio di successo
        return "Login avvenuto con successo!";
    }
	
	public void logout() {}
	
	public void cancellaAccount(User user) {
		userDao.delete(user);
	}

	public User findById(User user) {
		return userDao.findById(user.getUserId());
	}
	
    @Transactional
	public void update(User user) {
		userDao.update(user);
	}
	
	

}
