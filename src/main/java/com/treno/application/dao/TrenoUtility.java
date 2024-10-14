package com.treno.application.dao;

import java.util.List;

import com.treno.application.filter.TrenoFilter;
import com.treno.application.model.Treno;

public interface TrenoUtility extends Dao <Treno> {

	Treno findById(Treno treno);
	List<Treno> filtraTrenoByParametro(TrenoFilter filtro);

}
