package com.treno.application.dao;

import java.util.List;

import com.treno.application.filter.TrenoFilter;
import com.treno.application.model.Treno;

public class TrenoDao extends ProxyDao<Treno> {

	public TrenoDao() {
		super(Treno.class);
	}
	
	public List<Treno> filterAllTreni(TrenoFilter filtro) {
		return null;
		}
	
	
}
