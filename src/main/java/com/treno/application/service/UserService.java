package com.treno.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.dto.UserDTO;
import com.treno.application.exception.AlreadyExistEmail;
import com.treno.application.exception.InvalidCredentialsException;
import com.treno.application.exception.InvalidPasswordException;
import com.treno.application.exception.InvalidPhoneNumberException;
import com.treno.application.exception.UserAlreadyExistsException;
import com.treno.application.exception.UserNotFoundException;
import com.treno.application.filter.UtenteFilter;
import com.treno.application.model.User;
import com.treno.application.model.User.Stato;
import com.treno.application.utility.UserUtility;

public class UserService {

	@Autowired
	@Qualifier("UserDao")
	private UserUtility userDao;

	

	
	
	
	public UserUtility getUserDao() {
		return userDao;
	}

	public void setUserDao(UserUtility userDao) {
		this.userDao = userDao;
	}

	// Metodo registrazione
	@Transactional
	public void registra(UserDTO userDto) {
		  User utenteEsistente = userDao.findByUsername(userDto.getUsername());
	        if (utenteEsistente != null) {
	            throw new UserAlreadyExistsException("Utente già registrato con questo username!");
	        }
	        
	        // Controlla se esiste già un utente con la stessa email
	        User utenteEsistenteEmail = userDao.findByEmail(userDto.getEmail());
	        if (utenteEsistenteEmail != null) {
	            throw new UserAlreadyExistsException("Esiste già un account con questa email!");
	        }
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword()); 
		user.setNome(userDto.getNome());
		user.setCognome(userDto.getCognome());
		user.setTelefono(userDto.getTelefono());
		user.setEmail(userDto.getEmail());
		user.setPortafoglio(0);
		user.setStato(Stato.unlocked);
		userDao.save(user);
		
		// Ricontrollare l'update per aggiornamento profilo .
	}
	
	
	
	
	
    //Login
	public UserDTO login(UserDTO userDto) {
	    // Trova l'utente dal database usando lo username
	    User user = userDao.findByUsername(userDto.getUsername());
	    
	    if (user == null) {
	        throw new UserNotFoundException("Utente non trovato");
	    }
	    
	    if(!user.getUsername().equals(userDto.getUsername())) {
	    	throw new InvalidCredentialsException("Credenziali sbagliate, ricontrolla password o username.");
	    }

	    if (!user.getPassword().equals(userDto.getPassword())) {
	        throw new InvalidPasswordException("Password non corretta");
	    }
	    
	    

	    return convertToUserDTO(user);
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

	
	
	
	// UpdateUserConParametri : obbiettivo eseguire update su parametri mirati, 
	// lancia eccezzioni che poi vengono catturate nel controller e rimandate al client .

	@Transactional
	public UserDTO updateUserWithParams(Long userId, String passwordVecchia, String passwordNuova, String email, String telefono) throws InvalidCredentialsException {
	    User user = userDao.findById(userId);
	    if (user == null) {
	        throw new UserNotFoundException("L'utente con ID " + userId + " non è stato trovato.");
	    }

	    
	    if (passwordNuova != null && !passwordNuova.isEmpty()) {
	        if (passwordVecchia == null || !passwordVecchia.equals(user.getPassword())) {
	            throw new InvalidPasswordException("La vecchia password non è corretta.");
	        }
	        user.setPassword(passwordNuova); // Imposta la nuova password solo se la vecchia è corretta
	    }
	    

	    if (email != null && !email.isEmpty()) {
	        if ((user.getEmail()).equals(email)) {
	            throw new AlreadyExistEmail("L'email " + email + " è già in uso.");
	        }
	        user.setEmail(email);
	    }

	    
	    if (telefono != null && !telefono.isEmpty()) {
	        if ((!telefono.matches("\\d{10}"))||(user.getTelefono().equals(telefono))) {
	            throw new InvalidPhoneNumberException("Il numero di telefono inserito non è valido.");
	        }
	        user.setTelefono(telefono);
	    }

	    userDao.update(user);
	    
	    return convertToUserDTO(user);
	}



	   

	
	
    //Aggiorna portafoglio.
    @Transactional
    public void update(UserDTO userDto) {
        User user = userDao.findById(userDto.getUserId());
        if (user == null) {
            throw new UserNotFoundException("Utente non trovato per aggiornamento");
        }
        user.setPortafoglio(userDto.getPortafoglio());
        userDao.update(user);
    }



	// find
	public UserDTO findById(long id) {
		User user = userDao.findById(id);
		if (user == null) {
			throw new UserNotFoundException("Utente non trovato con ID: " + id);
		}
		return convertToUserDTO(user);
	}
	
	//ByUsername
	public UserDTO findByUsername(String username) {
		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new UserNotFoundException("Utente non trovato con username: " + username);
		}
		return convertToUserDTO(user);
	}
	
	
	// find ALL
	public List<UserDTO> findAllUsers() {
		return userDao.findAllUsers();
	}
	
	
	// Filtro Utenti nel Service per fare da ponte al controller.
	public List<UserDTO> filtraUtenti(UtenteFilter filtro, long userId) {
		List<User> utenti = userDao.filtraUtenti(filtro, userId);
		if (utenti.isEmpty()) {
			throw new UserNotFoundException("Nessun utente trovato con il filtro specificato");
		}
		return utenti.stream().map(this::convertToUserDTO).collect(Collectors.toList());
	}
	
	
	// Senza questa roba non si puo chiamare logica di business
	public UserDTO convertToUserDTO (User user) {
		UserDTO userDto = new UserDTO();
		userDto.setUserId(user.getUserId());
		userDto.setPassword(user.getPassword());
		userDto.setUsername(user.getUsername());
		userDto.setNome(user.getNome());
		userDto.setCognome(user.getCognome());
		userDto.setEmail(user.getEmail());
		userDto.setPortafoglio(user.getPortafoglio());
		userDto.setTelefono(user.getTelefono());
		userDto.setStato(user.getStato());
		return userDto;
	}
	
	
	
	public User convertToUserEntity(UserDTO userDto) {
	    User user = new User();
	    user.setUserId(userDto.getUserId());
	    user.setUsername(userDto.getUsername());
	    user.setPassword(userDto.getPassword());
	    user.setEmail(userDto.getEmail());
	    user.setPortafoglio(userDto.getPortafoglio());
	    user.setStato(userDto.getStato());
	    user.setTelefono(user.getTelefono());
	    user.setNome(userDto.getNome());
	    user.setCognome(userDto.getCognome());
	    return user;
	}
	
	
	//metodi admin
	
	
	
	
}
