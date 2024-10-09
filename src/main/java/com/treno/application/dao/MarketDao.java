package com.treno.application.dao;

import java.util.*;

import com.treno.application.model.Treno;

public interface MarketDao {

	List<Treno> showTreniInVendita();
	List<Treno> showByTrenoId(int id);
	List<Treno> showByUserId(int id);
	List<Treno> showByPrice(double price);
	
}
