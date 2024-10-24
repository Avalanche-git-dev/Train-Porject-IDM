 package com.treno.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.treno.application.dto.AdminDTO;
import com.treno.application.exception.InvalidCredentialsException;
import com.treno.application.exception.InvalidPasswordException;
import com.treno.application.exception.UserNotFoundException;
import com.treno.application.model.Admin;
import com.treno.application.model.User;
import com.treno.application.model.User.Stato;
import com.treno.application.utility.TrenoUtility;

public class AdminService extends UserService {

	@Autowired
	@Qualifier("TrenoDao")
	private TrenoUtility trenoDao;
	
	
	
	
	
    //Login as Admin
//	
	public AdminDTO login (AdminDTO adminDto) {
	   User user =  super.getUserDao().findByUsername(adminDto.getUsername());
	    
	    if (user == null) {
	        throw new UserNotFoundException("Utente non trovato");
	    }
	    
	    if(!user.getUsername().equals(adminDto.getUsername())) {
	    	throw new InvalidCredentialsException("Credenziali sbagliate, ricontrolla password o username.");
	    }

	    if (!user.getPassword().equals(adminDto.getPassword())) {
	        throw new InvalidPasswordException("Password non corretta");
	    }
	    
	    if ((user instanceof Admin)) {
	        Admin admin = (Admin) user;
	        return convertToAdminDTO(admin);
	    } else {
	        throw new InvalidCredentialsException("Accesso negato. Questa sezione è solo gli amministratori possono accedere.");
	    }
	    
	}
	


	
	
	
	
	
	
    // Funzionalità admin
	public void bloccaUser(long userId) {
		User user = super.getUserDao().findById(userId);
		user.setStato(Stato.locked);
		super.getUserDao().update(user);
	}
	
	public void sbloccaUser(long userId) {
		User user = super.getUserDao().findById(userId);
		user.setStato(Stato.unlocked);
		super.getUserDao().update(user);
	}
	
	
	
	
	
	
	
	
	// Conversioni in caso di problemi. i dto sono molto comodi per decidere con quali dati lavorare.
	public AdminDTO convertToAdminDTO(Admin admin) {
	    AdminDTO adminDto = new AdminDTO();
	    adminDto.setUserId(admin.getUserId());
	    adminDto.setUsername(admin.getUsername());
	    adminDto.setNome(admin.getNome());
	    adminDto.setCognome(admin.getCognome());
	    adminDto.setEmail(admin.getEmail());
	    adminDto.setTelefono(admin.getTelefono());
	    adminDto.setPortafoglio(admin.getPortafoglio());
	    adminDto.setStato(admin.getStato());
	    adminDto.setPrivilegio(admin.isPrivilegio()); // Campo booleano per privilegio admin
	    return adminDto;
	}
	
	
	
	public Admin convertToAdminEntity(AdminDTO adminDto) {
	    Admin admin = new Admin();
	    admin.setUserId(adminDto.getUserId());
	    admin.setUsername(adminDto.getUsername());
	    admin.setNome(adminDto.getNome());
	    admin.setCognome(adminDto.getCognome());
	    admin.setEmail(adminDto.getEmail());
	    admin.setTelefono(adminDto.getTelefono());
	    admin.setPortafoglio(adminDto.getPortafoglio());
	    admin.setStato(adminDto.getStato());
	    admin.setPrivilegio(adminDto.isPrivilegio()); // Campo booleano per privilegio admin
	    return admin;
	}


	
	
	
	
} 