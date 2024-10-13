package com.treno.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.dao.Dao;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
import com.treno.application.model.builder.TrenoBuilder;

@Service
public class TrenoService {
	
	@Autowired
	@Qualifier ("Builder")
	private TrenoBuilder builder;
	
	@Autowired
	@Qualifier ("TrenoDao")
	private Dao<Treno> trenoDao;

	
	
	 //Owner gestito trasmite sessione quindi arriva a livello service convalidato.
	 @Transactional
	    public Treno creaTreno(String input, User owner) {
	        Treno treno = builder.creaTrenoDaStringa(input);
	        treno.setOwner(owner);
	        //Da Aggiungere la logica di controllo per il treno se esiste gia oppure no per usare il metodo corretto.
	        trenoDao.save(treno);
	        return treno;
	    }

}
