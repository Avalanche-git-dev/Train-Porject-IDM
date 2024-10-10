package com.treno.application.service;

import com.treno.application.dao.UserDao;
import com.treno.application.model.User;

public class UserService {
	
	UserDao userDao;
	
	public void registrazione(User user) {
		userDao.save(user);
	}
	
	public User login(int id, String password) {
		User user = userDao.findById(id);
		if(user != null && user.getPassword().equals(password))
			return user;
		return null;
	}
	
	public void logout() {}
	
	public void cancellaAccount(User user) {
		userDao.delete(user);
	}

}
