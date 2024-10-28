package com.treno.application.utility;

import java.util.List;

import com.treno.application.dao.Dao;
import com.treno.application.dto.UserDTO;
import com.treno.application.filter.UtenteFilter;
import com.treno.application.model.Admin;
import com.treno.application.model.User;

public interface UserUtility extends Dao <User>{
	
	public User findByUsername (String username);


	public User findByEmail(String email);
	
	public User findByPassword(String password);
	
	public List<UserDTO> findAllUsers();

	List<User> filtraUtenti(UtenteFilter filtro);


	public List<User> findAllLockedUsers();


	public List<User> findAllActiveUsers();


	Admin findAdminByUserId(Long userId);


	public List<Admin> findAllAdminWithPrivileges();




}
