/* package com.treno.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.treno.application.dao.UserDao;
import com.treno.application.model.User;
import com.treno.application.model.User.Stato;

public class AdminService {

	@Autowired
	@Qualifier("UserDao")
	private UserDao userDao;
	
	public List<User> findAllUsers() {
        return userDao.findAll(); 
    }
	
	public void bloccaUser(long userId) {
		User user = userDao.findById(userId);
		user.setStato(Stato.locked);
	}
	
	public void sbloccaUser(long userId) {
		User user = userDao.findById(userId);
		user.setStato(Stato.unlocked);
	}
	
} */