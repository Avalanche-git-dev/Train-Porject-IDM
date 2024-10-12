package com.treno.application.model;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Component
@Scope("prototype")
@Entity
@Table(name = "transazioni")
public class Transazione {
	
	
	@Column(name = "importo")
	private Double importo;
	
	
	@Column(name = "transazione_data")
	private LocalDateTime data;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_transazione")
	private Integer idTransazione;

	// @OneToMany(mappedBy="market", cascade=CascadeType.ALL, orphanRemoval=true,
	// fetch=FetchType.LAZY)
	@ManyToOne//(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_treno")
	private Treno treno;

	
	
	@ManyToOne//(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "buyer_id")
	private User acquirente;

	
	

	@ManyToOne//(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "seller_id")
	private User venditore;







	public Transazione() {
		super();
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








	public Integer getIdTransazione() {
		return idTransazione;
	}




	public void setIdTransazione(Integer idTransazione) {
		this.idTransazione = idTransazione;
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

	
	
	
}