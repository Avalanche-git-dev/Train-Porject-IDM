package com.treno.application.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transazioni")
public class Transazione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_transazione")
	private long idTransazione;

	@Column(name = "importo", nullable = false)
	private Double importo;

	@Column(name = "transazione_data", nullable = false)
	private LocalDateTime data;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_treno", nullable = false)
	private Treno treno;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "buyer_id", nullable = false)
	private User acquirente;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "seller_id", nullable = false)
	private User venditore;

	// Costruttore vuoto

	public Transazione() {
		super();
	}

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

	public Treno getTreno() {
		return treno;
	}

	public void setTreno(Treno treno) {
		this.treno = treno;
	}

	public User getAcquirente() {
		return acquirente;
	}

	public void setAcquirente(User acquirente) {
		this.acquirente = acquirente;
	}

	public User getVenditore() {
		return venditore;
	}

	public void setVenditore(User venditore) {
		this.venditore = venditore;
	}

	@Override
	public String toString() {
		return "Transazione [idTransazione=" + idTransazione + ", importo=" + importo + ", data=" + data + ", treno="
				+ treno.getIdTreno() + ", acquirente=" + acquirente.getUserId() + ", venditore=" + venditore.getUserId()
				+ "]";
	}

}