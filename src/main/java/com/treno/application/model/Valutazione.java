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

	public long getIdValutazione() {
		return idValutazione;
	}

	public void setIdValutazione(long idValutazione) {
		this.idValutazione = idValutazione;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Treno getTreno() {
		return treno;
	}

	public void setTreno(Treno treno) {
		this.treno = treno;
	}

	public int getVotazione() {
		return votazione;
	}

	public void setVotazione(int votazione) {
		this.votazione = votazione;
	}

	@Override
	public String toString() {
		return "Valutazione [idValutazione=" + idValutazione + ", user=" + user.getUsername() + ", treno=" + treno.getNome() + ", votazione="
				+ votazione + "]";
	}
	
	

}
