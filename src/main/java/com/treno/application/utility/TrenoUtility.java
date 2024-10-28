package com.treno.application.utility;

import java.util.List;

import com.treno.application.dao.Dao;
import com.treno.application.filter.TrenoFilter;
import com.treno.application.model.Treno;
import com.treno.application.model.Vagone;

public interface TrenoUtility extends Dao <Treno> {

	//Treno findById(Treno treno);
	
	public List<Treno> filtraTreni(TrenoFilter filtro);

	public List<Treno> findAllTreniByUser(long userId);
	
	public List<Treno> findAllInVendita();

	public List<Treno> findByOwnerIdAndInVenditaFalse(Long ownerId);
	
	public Treno findByTrenoId(long id);

	List<Vagone> findVagonibyTreno(Long trenoId);


	public List<Treno> filtraTreniInVendita(TrenoFilter filtro);

}
