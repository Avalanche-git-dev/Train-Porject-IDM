package com.treno.application.dao;

import com.treno.application.model.User;

public interface UserUtility extends Dao <User>{
	
	public User findByUsername (String username);


}
