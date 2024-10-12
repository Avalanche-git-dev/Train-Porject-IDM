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
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Entity
@Table(name = "transazioni")
public class Transazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_transazione")
	private Integer idTransazione;
	
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

    //Costruttore vuoto 
	
	public Transazione() {
		super();
	}



	
}