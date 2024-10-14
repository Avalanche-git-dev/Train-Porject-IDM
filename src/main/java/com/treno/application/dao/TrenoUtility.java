package com.treno.application.dao;

import com.treno.application.model.Treno;

public interface TrenoUtility extends Dao <Treno> {

	Treno findById(Treno treno);
	

}
