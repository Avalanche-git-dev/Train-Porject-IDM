package com.treno.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.dao.Dao;
import com.treno.application.dto.UserDTO;
import com.treno.application.exception.InvalidPasswordException;
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

	public Dao<User> getUserDao() {
		return userDao;
	}

	public void setUserDao(Dao<User> userDao) {
		this.userDao = (UserUtility) userDao;
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
		 //meglio boolean piu facile con i controller.
	}
	
	
	
	
	
    //Login
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

	
	
	
	
	
	
//	@Transactional
//	public void updateModify(Long userId, UserDTO userDto) {
//	    User user = userDao.findById(userId);
//	    if (user != null) {
//	        user.setNome(userDto.getNome());
//	        user.setEmail(userDto.getEmail());
//	        user.setPortafoglio(userDto.getPortafoglio());
//	        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
//	            user.setPassword(userDto.getPassword());
//	        }
//	        userDao.save(user);
//	        System.out.println("UserService.updateUser: user updated = " + user);
//	        //return convertToUserDTO(user);
//	    }
//	    
//	    System.out.println("UserService.updateUser: user not found for id = " + userId);
//	   // return null;
//	}
	
//	   public UserDTO updateUserWithFiltro(Long userId, UtenteFilter userFiltro) throws UserNotFoundException {
//	        // Recupera l'utente dal database
//	        Optional<User> optionalUser = Optional.of(userDao.findById(userId));
//	        if (!optionalUser.isPresent()) {
//	            throw new UserNotFoundException("Utente non trovato con ID: " + userId);
//	        }
//
//	        User user = optionalUser.get();
//	        System.out.println("UserService.updateUserWithFiltro: user prima aggiornamento = " + user);
//
//	        // Applica i campi non vuoti del filtro all'utente
//	        if (userFiltro.getNome() != null && !userFiltro.getNome().isEmpty()) {
//	            user.setNome(userFiltro.getNome());
//	        }
//
//	        if (userFiltro.getEmail() != null && !userFiltro.getEmail().isEmpty()) {
//	            user.setEmail(userFiltro.getEmail());
//	        }
//
//	        if (userFiltro.getPassword() != null && !userFiltro.getPassword().isEmpty()) {
//	            user.setPassword(userFiltro.getPassword()); // Assicurati di gestire la cifratura della password
//	        }
//
//	        // Aggiorna altri campi se presenti
//
//	        // Salva l'utente aggiornato nel database
//	        userDao.save(user);
//	        System.out.println("UserService.updateUserWithFiltro: user dopo aggiornamento = " + user);
//
//	        // Converti l'entità aggiornata in DTO
//	        return convertToUserDTO(user);
//	    }
//	   @Transactional
//	   public UserDTO updateUserWithParams(Long userId, String passwordVecchia, String passwordNuova, String email, String telefono) throws Exception {
//		    User user = userDao.findById(userId);
//		    if (passwordNuova != null && !passwordNuova.isEmpty()) {
//		        if (!passwordVecchia.equals((user.getPassword()))) {
//		            throw new Exception("Password vecchia errata.");
//		        }
//		        user.setPassword(passwordNuova);
//		    }
//		    if (email != null && !email.isEmpty() && !email.equals((user.getEmail()))) {
////		        User existingUser = userDao.findByEmail(email);
////		        if (existingUser != null) {
////		            throw new Exception("Email già in uso.");
////		        }
//		        user.setEmail(email);
//		    }
//		    if (telefono != null && !telefono.isEmpty()) {
//		        user.setTelefono(telefono);
//		    }
//		    userDao.update(user);
//		    return convertToUserDTO(user);
//		}
	
	@Transactional
	public UserDTO updateUserWithParams(Long userId, String passwordVecchia, String passwordNuova, String email, String telefono) throws Exception {
	    User user = userDao.findById(userId);
	    
	    // Verifica della password
	    if (passwordNuova != null && !passwordNuova.isEmpty()) {
	        // Controllo se la vecchia password è corretta
	        if (passwordVecchia == null || !passwordVecchia.equals(user.getPassword())) {
	            throw new Exception("La vecchia password non è corretta.");
	        }
	        // Imposta la nuova password solo se la vecchia è corretta
	        user.setPassword(passwordNuova);
	    }
	    
	    // Aggiornamento email se cambiata
	    if (email != null && !email.isEmpty() && !email.equals(user.getEmail())) {
	        // Esegui qui il controllo sull'esistenza dell'email se necessario
	        user.setEmail(email);
	    }
	    
	    // Aggiornamento telefono se fornito
	    if (telefono != null && !telefono.isEmpty()) {
	        user.setTelefono(telefono);
	    }
	    
	    userDao.update(user);
	    
	    return convertToUserDTO(user);
	}


	   

	
	

    @Transactional
    public void update(UserDTO userDto) {
        User user = userDao.findById(userDto.getUserId());
        if (user == null) {
            throw new UserNotFoundException("Utente non trovato per aggiornamento");
        }
        //user.setUsername(userDto.getUsername());
        user.setPortafoglio(userDto.getPortafoglio());
       // user.setStato(User.Stato.valueOf(userDto.getStato()));
        userDao.update(user);
    }

//
//	// Modifica
//	@Transactional
//	public void update(UserDTO userDto) {
//		User user = userDao.findById(userDto.getUserId());
//		
//
//		if(userDto.getPassword()!=null) {
//			user.setPassword(userDto.getPassword());
//		}
//		
//		if(userDto.getPassword().equals((user.getPassword()))) {
//			throw new InvalidPasswordException("La password da te inserita non corrisponde alla tua password corrente.");
//		}
//		
//		if(userDto.getEmail()!=null) {
//			user.setEmail(userDto.getEmail());
//		}
//		if(userDto.getTelefono()!=null) {
//			user.setTelefono(userDto.getTelefono());
//		}
//		
//		if(user!=null) {
//		userDao.update(user);
//		}
//	}
	
//	@Transactional
//	public void update(UserDTO userDto) {
//	    User user = userDao.findById(userDto.getUserId());
//	    if (user == null) {
//	        throw new DataAccessResourceFailureException("è fallita la ricerca nel db mi sa.");
//	    }
//	    
//        //User userUp = convertToUserEntity(userDto);
//	    // Aggiorna l'utente nel database
//	    userDao.update(user);
//	}
	
//	@Transactional
//	public void updateModify(UserDTO user) {
//		User UserCheck = userDao.findById(user.getUserId());
//		
//		
//		if(user.getPassword()!=null&&(UserCheck.getPassword().equals((user.getPassword())))) {
//		UserCheck.setPassword(user.getPassword());
//		}
//		
//		if(user.getEmail()!=null) {
//		UserCheck.setEmail(user.getEmail());
//		}
//		
//	if(user.getTelefono()!=null) {
//			UserCheck.setTelefono(user.getTelefono());
//		}
//		
//		userDao.update(UserCheck);
//	}


	
	



	
	

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
	
	
	//ByPassword

	
	
	
	
	// Ormai abbiamo capito il giro :)
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
		userDto.setUsername(user.getUsername());
		userDto.setNome(user.getNome());
		userDto.setCognome(user.getCognome());
		userDto.setEmail(user.getEmail());
		userDto.setPortafoglio(user.getPortafoglio());
		userDto.setTelefono(user.getTelefono());
		return userDto;
	}
	
	
	
	public User convertToUserEntity(UserDTO userDto) {
	    User user = new User();
	    user.setUserId(userDto.getUserId());
	    user.setUsername(userDto.getUsername());
	    user.setEmail(userDto.getEmail());
	    user.setPortafoglio(userDto.getPortafoglio());

	    if (userDto.getStato() != null) {
	        user.setStato(Stato.valueOf(userDto.getStato()));
	    }

	    return user;
	}
}
