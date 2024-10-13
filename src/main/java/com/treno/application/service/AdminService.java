package com.treno.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.treno.application.dao.Dao;
import com.treno.application.model.Transazione;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
import com.treno.application.model.User.Stato;

@Component
@Scope("prototype")
public class AdminService {

	@Autowired
	@Qualifier("userDao")
	private Dao<User> userDao;
	
	public Dao<User> getUserDao() {
		return userDao;
	}

	public void setUserDao(Dao<User> userDao) {
		this.userDao = userDao;
	}
	
	public void controllaAttivita(User user) {
		if(user.getUserId() != null) {
			List<Treno> listaTreni = user.getL();
			List<Transazione> listaAcquisti = user.getTransazioneAcquisto();
			List<Transazione> listaVendite = user.getTransazioneVendita();
			System.out.println(listaTreni);
			System.out.println(listaAcquisti);
			System.out.println(listaVendite);
			// Mostra le varie liste. Si può fare direttamente nel controller?
		}
	}
	
	public void banna(User user) {
		user.setStato(Stato.locked);
		userDao.update(user);
	}
	
	public void riattiva(User user) {
		user.setStato(Stato.unlocked);
		userDao.update(user);
	}
	
}
