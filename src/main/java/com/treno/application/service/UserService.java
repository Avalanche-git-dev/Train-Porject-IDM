package com.treno.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.dao.Dao;
import com.treno.application.dao.UserUtility;
import com.treno.application.dto.UserDto;
import com.treno.application.filter.UtenteFilter;
import com.treno.application.model.User;
import com.treno.eccezzioni.InvalidPasswordException;
import com.treno.eccezzioni.UserNotFoundException;



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
    public String registra (UserDto userDto) {
        // Controlla se l'utente esiste già
        User utenteEsistente = userDao.findById(userDto.getUserId());
        if (utenteEsistente != null) {
            // Se l'utente esiste, restituisci un messaggio di errore
            return "Utente già registrato con questo username!";
        }

        // Crea un nuovo oggetto User a partire dai dati del DTO
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword()); // Aggiungi qui la logica di hashing se necessario
        user.setNome(userDto.getNome());
        user.setCognome(userDto.getCognome());
        user.setTelefono(userDto.getTelefono());
        user.setEmail(userDto.getEmail());
        user.setPortafoglio(0);
        userDao.save(user);
        // Aggiungere il controllo della presenza dell'utente
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
    
    
    
    public List<User> filtraUtenti(UtenteFilter filtro, long userId) {
	    return userDao.filtraUtenti(filtro, userId);
	}

	

}
