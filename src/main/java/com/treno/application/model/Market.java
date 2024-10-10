package com.treno.application.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "markets")
public class Market {

	private Double amount;

	private LocalDateTime transactionDate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_market")
	private Integer idMarket;

	// @OneToMany(mappedBy="market", cascade=CascadeType.ALL, orphanRemoval=true,
	// fetch=FetchType.LAZY)
	@OneToOne
	@JoinColumn(name = "id_treno")
	private Treno treniInVendita;

	@ManyToOne
	@JoinColumn(name = "buyer_id")
	private User acquirente;

	@ManyToOne
	@JoinColumn(name = "seller_id")
	private User venditore;

	public Integer getIdMarket() {
		return idMarket;
	}

	public Market(Double amount, LocalDateTime transactionDate, Integer idMarket, Treno treniInVendita, User acquirente,
			User venditore) {
		super();
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.idMarket = idMarket;
		this.treniInVendita = treniInVendita;
		this.acquirente = acquirente;
		this.venditore = venditore;
	}

	public Market() {
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

	public Treno getTreniInVendita() {
		return treniInVendita;
	}

	public void setTreniInVendita(Treno treniInVendita) {
		this.treniInVendita = treniInVendita;
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

	public void setIdMarket(Integer idMarket) {
		this.idMarket = idMarket;
	}




}
