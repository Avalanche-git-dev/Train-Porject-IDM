package com.treno.application.dao;

import java.util.List;

import com.treno.application.dto.TransazioneDTO;
import com.treno.application.model.Transazione;

public interface TransazioneUtility extends Dao <Transazione> {

	public List<Transazione> findTransazioniByTreno(long id);

	public List<Transazione> findTransazioniByUser(long id);

	public List<TransazioneDTO> findTreniByTotalTransactionValueDesc();

}
