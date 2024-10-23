package com.treno.application.dto;

import java.time.LocalDateTime;



public class TransazioneDTO {

    private long idTransazione;
    private Double importo;
    private LocalDateTime data;
    private long trenoId;
    private String trenoNome;  
    private long acquirenteId;
    private String acquirenteUsername;  
    private long venditoreId;
    private String venditoreUsername;  

    // Costruttori
    public TransazioneDTO() {
    }

    public TransazioneDTO(long idTransazione, Double importo, LocalDateTime data, long trenoId, String trenoNome,
                          long acquirenteId, String acquirenteUsername, long venditoreId, String venditoreUsername) {
        this.idTransazione = idTransazione;
        this.importo = importo;
        this.data = data;
        this.trenoId = trenoId;
        this.trenoNome = trenoNome;
        this.acquirenteId = acquirenteId;
        this.acquirenteUsername = acquirenteUsername;
        this.venditoreId = venditoreId;
        this.venditoreUsername = venditoreUsername;
    }

    // Getters e Setters
    public long getIdTransazione() {
        return idTransazione;
    }

    public void setIdTransazione(long idTransazione) {
        this.idTransazione = idTransazione;
    }

    public Double getImporto() {
        return importo;
    }

    public void setImporto(Double importo) {
        this.importo = importo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public long getTrenoId() {
        return trenoId;
    }

    public void setTrenoId(long trenoId) {
        this.trenoId = trenoId;
    }

    public String getTrenoNome() {
        return trenoNome;
    }

    public void setTrenoNome(String trenoNome) {
        this.trenoNome = trenoNome;
    }

    public long getAcquirenteId() {
        return acquirenteId;
    }

    public void setAcquirenteId(long acquirenteId) {
        this.acquirenteId = acquirenteId;
    }

    public String getAcquirenteUsername() {
        return acquirenteUsername;
    }

    public void setAcquirenteUsername(String acquirenteUsername) {
        this.acquirenteUsername = acquirenteUsername;
    }

    public long getVenditoreId() {
        return venditoreId;
    }

    public void setVenditoreId(long venditoreId) {
        this.venditoreId = venditoreId;
    }

    public String getVenditoreUsername() {
        return venditoreUsername;
    }

    public void setVenditoreUsername(String venditoreUsername) {
        this.venditoreUsername = venditoreUsername;
    }

	@Override
	public String toString() {
		return "TransazioneDTO [idTransazione=" + idTransazione + ", importo=" + importo + ", data=" + data
				+ ", trenoId=" + trenoId + ", trenoNome=" + trenoNome + ", acquirenteId=" + acquirenteId
				+ ", acquirenteUsername=" + acquirenteUsername + ", venditoreId=" + venditoreId + ", venditoreUsername="
				+ venditoreUsername + "]";
	}
    
    
    
}
