package com.treno.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.dao.TrenoUtility;
import com.treno.application.filter.TrenoFilter;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
import com.treno.application.model.builder.TBuilder;
import com.treno.application.model.builder.TrenoBuilder;

@Service
public class TrenoService {

	@Autowired
	@Qualifier("Builder")
	private TrenoBuilder builder;

	@Autowired
	@Qualifier("TrenoDao")
	private TrenoUtility trenoDao;

	// Owner gestito trasmite sessione quindi arriva a livello service convalidato.
	@Transactional
	public void creaTreno(String input, User owner, String marca) {
		TBuilder builder2 = (TBuilder) builder;
		builder2.getFactory().setMarca(marca);
		Treno treno = builder.creaTrenoDaStringa(input);
		treno.setOwner(owner);
		trenoDao.save(treno);
	}

	public Treno findByT(Treno treno) {
		return trenoDao.findById(treno.getIdTreno());
	}
	
	public List<Treno> findAllTreniByUser(long userId) {
        return trenoDao.findAllTreniByUser(userId);
    }
	
	@Transactional
	public void update(Treno treno) {
		trenoDao.update(treno);

	}
	
	
	//Metodo pronto per i filtri 

	public List<Treno> filtraTreni(TrenoFilter filtro, long userId) {
	    return ((TrenoUtility) trenoDao).filtraTreni(filtro, userId);
	}

	public Treno findById(Long id) {
		return trenoDao.findById(id);
	}

//	 public List<Treno> findTreniByUser(long userId) {
//	        return trenoDao.findAllTreniByUser(userId);
//	    }
}
