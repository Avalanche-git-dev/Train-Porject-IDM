package com.treno.application.utility;

import java.util.List;

import com.treno.application.dao.Dao;
import com.treno.application.filter.UtenteFilter;
import com.treno.application.model.User;

public interface UserUtility extends Dao <User>{
	
	public User findByUsername (String username);

	public List<User> filtraUtenti(UtenteFilter filtro, long userId);

	public User findByEmail(String email);
	public User findByPassword(String password);


}
