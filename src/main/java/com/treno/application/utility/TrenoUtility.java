package com.treno.application.utility;

import java.util.List;

import com.treno.application.dao.Dao;
import com.treno.application.filter.TrenoFilter;
import com.treno.application.model.Treno;

public interface TrenoUtility extends Dao <Treno> {

	//Treno findById(Treno treno);
	
	public List<Treno> filtraTreni(TrenoFilter filtro);

	public List<Treno> findAllTreniByUser(long userId);


}
