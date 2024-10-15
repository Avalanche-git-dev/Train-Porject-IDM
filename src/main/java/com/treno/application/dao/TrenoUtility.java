package com.treno.application.dao;

import java.util.List;

import com.treno.application.filter.TrenoFilter;
import com.treno.application.model.Treno;

public interface TrenoUtility extends Dao <Treno> {

	//Treno findById(Treno treno);
	
	public List<Treno> filtraTreni(TrenoFilter filtro, long userId);
}
