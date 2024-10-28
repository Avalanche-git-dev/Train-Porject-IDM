package com.treno.application.utility;

import java.util.List;

import com.treno.application.dao.Dao;
import com.treno.application.dto.TransazioneDTO;
import com.treno.application.model.Transazione;

public interface TransazioneUtility extends Dao <Transazione> {

	public List<Transazione> findTransazioniByTreno(long id);

	public List<Transazione> findTransazioniByUser(long id);

	public List<TransazioneDTO> findTreniByTotalTransactionValueDesc();

	public List<Transazione> findAllTransazioniOrdinatePerImporto();

	public List<Transazione> findAllTransazioni();

	public List<Transazione> findTransazioniOrdinatePerDataRecente();

	public List<Transazione> findTransazioniByVenditore(long venditoreId);

	public List<Transazione> findTransazioniByAcquirente(long acquirenteId);
	
	

}
