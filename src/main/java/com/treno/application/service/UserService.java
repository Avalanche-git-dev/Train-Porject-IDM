package com.treno.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.dao.Dao;
import com.treno.application.dao.UserUtility;
import com.treno.application.dto.UserDTO;
import com.treno.application.filter.UtenteFilter;
import com.treno.application.model.User;
import com.treno.eccezzioni.InvalidPasswordException;
import com.treno.eccezzioni.UserNotFoundException;

import jakarta.servlet.http.HttpSession;

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

	// Metodo registrazione
	@Transactional
	public String registra(UserDTO userDto) {
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

	// Cancella
	@Transactional
	public void cancellaAccount(UserDTO userDto) {
		User user = userDao.findById(userDto.getUserId());
		if (user == null) {
			throw new UserNotFoundException("Utente non trovato per cancellazione");
		}
		userDao.delete(user);
	}

	// find
	public UserDTO findById(long id) {
		User user = userDao.findById(id);
		if (user == null) {
			throw new UserNotFoundException("Utente non trovato con ID: " + id);
		}
		return convertToDTO(user);
	}

	// Modifica
	@Transactional
	public void update(UserDTO userDto) {
		User user = userDao.findById(userDto.getUserId());
		if (user == null) {
			throw new UserNotFoundException("Utente non trovato per aggiornamento");
		}
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setPortafoglio(userDto.getPortafoglio());
		user.setStato(User.Stato.valueOf(userDto.getStato()));
		userDao.update(user);
	}

	// Metodo login con eccezzioni
//    public void login(UserDTO userDto) {
//        // Trova l'utente tramite il suo username
//        User user = userDao.findByUsername(userDto.getUsername());
//
//        // Controlla se l'utente esiste
//        if (user == null) {
//            throw new UserNotFoundException("Utente non trovato");
//        }
//
//        // Verifica la password (si presume che la password nel DB sia già hashata)
//        if (!user.getPassword().equals(userDto.getPassword())) {
//            throw new InvalidPasswordException("Password non corretta");
//        }
//
//        // Nessuna azione necessaria, l'autenticazione è avvenuta con successo
//    }

	// Login
//	public void login(UserDTO userDto) {
//		User user = userDao.findByUsername(userDto.getUsername());
//		if (user == null) {
//			throw new UserNotFoundException("Utente non trovato");
//		}
//
//		if (!user.getPassword().equals(userDto.getPassword())) {
//			throw new InvalidPasswordException("Password non corretta");
//		}
//	}

	
	public UserDTO login(UserDTO userDto) {
	    // Trova l'utente dal database usando lo username
	    User user = userDao.findByUsername(userDto.getUsername());
	    
	    // Verifica se l'utente esiste
	    if (user == null) {
	        throw new UserNotFoundException("Utente non trovato");
	    }

	    // Verifica se la password fornita è corretta
	    if (!user.getPassword().equals(userDto.getPassword())) {
	        throw new InvalidPasswordException("Password non corretta");
	    }

	    // Se le credenziali sono corrette, converti l'utente in UserDTO
	    return convertToDTO(user);
	}


	// logut
	@Transactional
	public void logout(HttpSession session) {
		session.invalidate();
	}

	// Metodo per cercare l'utente tramite username (esistente)
	public UserDTO findByUsername(String username) {
		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new UserNotFoundException("Utente non trovato con username: " + username);
		}
		return convertToDTO(user);
	}

	// Ormai abbiamo capito il giro :)
	public List<UserDTO> filtraUtenti(UtenteFilter filtro, long userId) {
		List<User> utenti = userDao.filtraUtenti(filtro, userId);
		if (utenti.isEmpty()) {
			throw new UserNotFoundException("Nessun utente trovato con il filtro specificato");
		}
		return utenti.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	// Senza questa roba non si puo chiamare logica di business
	private UserDTO convertToDTO(User user) {
		UserDTO userDto = new UserDTO();
		userDto.setUserId(user.getUserId());
		userDto.setUsername(user.getUsername());
		userDto.setEmail(user.getEmail());
		userDto.setPortafoglio(user.getPortafoglio());
		userDto.setStato(user.getStato().name());
		return userDto;
	}
}
