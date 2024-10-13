package com.treno.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.treno.application.dao.Dao;
import com.treno.application.model.User;



public class UserService {
	
	@Autowired
	@Qualifier("UserDao")
	private Dao<User> userDao;
	
	public Dao<User> getUserDao() {
		return userDao;
	}

	public void setUserDao(Dao<User> userDao) {
		this.userDao = userDao;
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
	}
	
	public User login(Long id, String password) {
		User user = userDao.findById(id);
		if(user != null && user.getPassword().equals(password)) {
			System.out.println("L'utente ha effettuato corretamente il login");
			return user;
		}
		System.out.println("La password non è corretta");
		return null;
	}
	
	public void logout() {}
	
	public void cancellaAccount(User user) {
		userDao.delete(user);
	}

}
