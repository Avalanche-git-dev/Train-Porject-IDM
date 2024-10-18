package com.treno.application.dto;

public class ValutazioneDTO {

    private long idValutazione;
    private Long userId; // ID dell'utente che ha fatto la valutazione
    private Long trenoId; // ID del treno valutato
    private String userNome; // Nome dell'utente, utile per la visualizzazione nella vista
    private String trenoNome; // Nome del treno
    private int votazione; // Voto assegnato (da 1 a 5)

    // Costruttore di default
    public ValutazioneDTO() {
    }

	public long getIdValutazione() {
		return idValutazione;
	}

	public void setIdValutazione(long idValutazione) {
		this.idValutazione = idValutazione;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTrenoId() {
		return trenoId;
	}

	public void setTrenoId(Long trenoId) {
		this.trenoId = trenoId;
	}

	public String getUserNome() {
		return userNome;
	}

	public void setUserNome(String userNome) {
		this.userNome = userNome;
	}

	public String getTrenoNome() {
		return trenoNome;
	}

	public void setTrenoNome(String trenoNome) {
		this.trenoNome = trenoNome;
	}

	public int getVotazione() {
		return votazione;
	}

	public void setVotazione(int votazione) {
		this.votazione = votazione;
	}
    
    
    
}
