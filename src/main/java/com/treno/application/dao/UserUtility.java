package com.treno.application.dao;

import java.util.List;

import com.treno.application.filter.UtenteFilter;
import com.treno.application.model.User;

public interface UserUtility extends Dao <User>{
	
	public User findByUsername (String username);

	public List<User> filtraUtenti(UtenteFilter filtro, long userId);


}
