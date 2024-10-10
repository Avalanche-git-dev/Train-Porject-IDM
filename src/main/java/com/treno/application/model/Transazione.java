package com.treno.application.model;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Component
@Scope("prototype")
@Entity
@Table(name = "markets")
public class Transazione {
	@Column(name = "amount")
	private Double amount;
	@Column(name = "transazione_data")
	private LocalDateTime transactionDate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_market")
	private Integer idMarket;

	// @OneToMany(mappedBy="market", cascade=CascadeType.ALL, orphanRemoval=true,
	// fetch=FetchType.LAZY)
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_treno")
	private Treno treniInVendita;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "buyer_id")
	private User acquirente;

	public Treno getTreniInVendita() {
		return treniInVendita;
	}

	
	public void setTreniInVendita(Treno treniInVendita) {
		this.treniInVendita = treniInVendita;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "seller_id")
	private User venditore;

	public Integer getIdMarket() {
		return idMarket;
	}

	public Transazione(Double amount, LocalDateTime transactionDate, Integer idMarket, Treno treniInVendita, User acquirente,
			User venditore) {
		super();
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.idMarket = idMarket;
		this.treniInVendita = treniInVendita;
		this.acquirente = acquirente;
		this.venditore = venditore;
	}

	public Transazione() {
		super();
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Treno getTrenoInVendita() {
		return treniInVendita;
	}

	public void setTrenoInVendita(Treno treno) {
		this.treniInVendita = treno;
		
	}

	public User getAcquirente() {
		return acquirente;
	}

	public void setAcquirente(User acquirente) {
	    this.acquirente = acquirente;
	    if (acquirente != null) {
	        acquirente.getTransazioneAcquisto().add(this);
	    }
	}
	public User getVenditore() {
		return venditore;
	}

	public void setVenditore(User venditore) {
	    this.venditore = venditore;
	    if (venditore != null) {
	        venditore.getTransazioneVendita().add(this);
	    }
	}

	public void setIdMarket(Integer idMarket) {
		this.idMarket = idMarket;
	}




}
