package com.treno.application.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



/* InheritanceType.JOINED crea una tabella separata per TreniInVendita 
   che è collegata alla tabella "Treni" tramite chiave esterna */
//@Component
@Entity
@Table(name="treni")
public class Treno {
	
	@Id // Chiave primaria dell'entità
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	/* Specifica che il valore della chiave verrà creato automaticamente 
	   tramite la strategy di auto-incremento. Non c'è bisogno di
	   specificare Column(...) come per gli altri attributi perché la sua 
	   creazione è garantita da GeneratedValue */
	private Integer idTreno;
	
	@Column(name="sigla", length=50, unique=true)
	private String sigla;
	
	@Column(name="immagine", length=255)
	private String immagine;

    // Relazione Many-to-One con Market
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_market") // Chiave esterna che collega Treno con Market
//    private Market market; 
	@Column(name = "in_vendita" )
    private  boolean InVendita;
	@Column(name = "prezzo_vendita")
	private double prezzoVendita;
	
	/* La relazione tra treni e vagoni è 1:N, perciò usiamo l'annotazione @OneToMany.
	   mappedBy indica che la relazione è gestita dal campo treno in Vagone.
	   cascade indica che ogni operazione fatta sul treno (persist, save, delete) viene propagata sul vagone.
	   fetch indica che i vagoni associati al treno non verranno caricati subito, ma solo quando ce ne sarà 
	   l'effettivo bisogno. */
    @OneToMany(mappedBy="treno", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
	private List<Vagone> vagoni = new LinkedList<Vagone>();
    
    // questo rimane treno perchè è il nome della chiave esterna che abbiamo dato al riferimento nella classe vagoni.
	
	@ElementCollection(targetClass=Valutazione.class)
	/* Si usa per mappare una collezione di valori base o tipi enum senza creare un'entità separata.
	   Semplicemente crea una tabella secondaria che contiene i valori della lista associandoli
	   all'entità principale. targetClass specifica il tipo di dati memorizzato nella collezione,
	   ovvero un enum di tipo Valutazione */
	@CollectionTable(name="valutazioni", joinColumns=@JoinColumn(name="id_treno"))
	/* I dati della collezione verranno salvati in una tabella separata valutazioni_treno.
	   In questa tabella sarà presente una chiave esterna che fa riferimento alla tabella Treni. */
	@Enumerated(EnumType.STRING)
	/* Mappa gli enum nel database. I vari valori verranno memorizzati come stringhe. */
	//@Column(name="valutazioni") DA RIGUARDARE E STUDIARE QUESTA TIPOLOGIA DI GESTIONE BIAGIO GENIO 
	private List<Valutazione> valutazioni = new LinkedList<Valutazione>();
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_owner")
	private User owner;
	
	
	@OneToMany(mappedBy="treno")
	private List <Transazione> transazioni = new ArrayList <Transazione>();
	
	public void addTransazione(Transazione t) {
		//if(this.transazioni.isEmpty())
		this.transazioni.add(t);
	}
	
	public void removeTransazione(Transazione t) {
		this.transazioni.remove(t);
	}
	
	
	
	
	public List<Transazione> getTransazioni() {
		return transazioni;
	}
	public void setTransazioni(List<Transazione> transazioni) {
		this.transazioni = transazioni;
	}
	
	
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	private Treno() {
		super();
	}
	//Static metodo per costruire il treno all'esterno della classe.
	public static Treno build() {
		return new Treno();
	}
	 // non so lo useremo mai teniamolo 
	private Treno(List<Vagone> vagoni) {
		super();
		this.vagoni = vagoni;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public List<Vagone> getVagoni() {
		return vagoni;
	}

	public void setVagoni(List<Vagone> vagoni) {
		this.vagoni = vagoni;
	}

	public List<Valutazione> getValutazioni() {
		return valutazioni;
	}

	public void setValutazioni(List<Valutazione> valutazioni) {
		this.valutazioni = valutazioni;
	}

    public final double getPeso() {
		Iterator<Vagone> it = vagoni.iterator();
		double pesoTotale = 0;
		while(it.hasNext()) {
			Vagone vagone = it.next(); 
			pesoTotale += vagone.getPeso(); // Correzione dell'operatore per somma
		}
		return pesoTotale;
	}
	
	public final double getCosto() {
		Iterator<Vagone> it = vagoni.iterator();
		double costoTotale = 0;
		while(it.hasNext()) {
			Vagone vagone = it.next();
			costoTotale += vagone.getCosto(); // Correzione dell'operatore per somma
		}
		return costoTotale;
	}
	
	public final double getLunghezza() {
		Iterator<Vagone> it = vagoni.iterator();
		double lunghezzaTotale = 0;
		while(it.hasNext()) {
			Vagone vagone = it.next();
			lunghezzaTotale += vagone.getLunghezza(); // Correzione dell'operatore per somma
		}
		return lunghezzaTotale;
	}

    public void add(Vagone vagone) {
		vagoni.add(vagone);
		vagone.setTreno(this);// antilogica oop
	}
    public void remove(Vagone vagone) {
		vagoni.remove(vagone);
		vagone.setTreno(null);// antilogica oop
	}
	
	public final void addValutazione(Valutazione valutazione) {
		valutazioni.add(valutazione);
	}
	
    public boolean parte() {
		Vagone v = vagoni.get(0);
		if(v instanceof Motrice) {
			Motrice locomotiva = (Motrice) v;
			if(locomotiva.getPesoTrainabile() > this.getPeso())
				return true;
		}
		return false;
	}

    public final double getPostiTotali() {
		Iterator<Vagone> it = vagoni.iterator();
		double postiTotali = 0;
		while(it.hasNext()) {
			Vagone vagone = it.next();
			if(vagone instanceof Passeggero) {
				Passeggero passeggeri = (Passeggero) vagone;
				postiTotali += passeggeri.getNumeroPosti();
			}	
		}
		return postiTotali;
	}
	
	public Iterator<Vagone> iterator() {
		return vagoni.iterator();
	}
	
	public enum Valutazione {
		UNO, DUE, TRE, QUATTRO, CINQUE;
	}

	public void setInVendita(boolean InVendita) {
		this.InVendita=InVendita;
		
	}

	public boolean getInVendita() {
		return this.InVendita;
	}

	public Integer getIdTreno() {
		return idTreno;
	}

	public void setIdTreno(Integer idTreno) {
		this.idTreno = idTreno;
	}

	public double getPrezzoVendita() {
		return prezzoVendita;
	}

	public void setPrezzoVendita(double prezzoVendita) {
		this.prezzoVendita = prezzoVendita;
	}
	public Treno getTreno() {
		return new Treno(Collections.unmodifiableList(vagoni));  // Restituisce una copia immutabile del treno.
	}
	
	//imposta il riferimento al treno delle sue trasazioni .
	}

