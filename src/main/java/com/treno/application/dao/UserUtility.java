package com.treno.application.dao;

import java.util.List;

import com.treno.application.filter.UserFilter;
import com.treno.application.model.User;

public interface UserUtility extends Dao <User>{
	
	User findByUsername(String username);
	List<User> filtraUserByParametro(UserFilter filtro, long userId);
	List<User> filtraUserByParametro(UserFilter filtro);

}
