package com.treno.application.model;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "valutazioni")
@NaturalIdCache
public class Valutazione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NaturalId
	private long idValutazione;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@NaturalId
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "treno_id", nullable = false)
	@NaturalId
	private Treno treno;

//	@Enumerated(EnumType.STRING)
//	private Voto voto;
	@Column(name = "voti", nullable = false)
	@Min(1)
	@Max(5) // JSR 303 Bean Validation, ogni tanto java si rende utile.
	private int votazione;

	public Valutazione() {
		super();
	}
	
	

}
