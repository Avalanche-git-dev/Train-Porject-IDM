package com.treno.application.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Check;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Entity
@Table(name = "treni")
@Check(constraints = "voti >= 1 AND voti <= 5") // Questo invece è proprio di hibernate ...
public class Treno {

	@Id // Chiave primaria dell'entità
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/*
	 * Specifica che il valore della chiave verrà creato automaticamente tramite la
	 * strategy di auto-incremento. Non c'è bisogno di specificare Column(...) come
	 * per gli altri attributi perché la sua creazione è garantita da GeneratedValue
	 */
	
	
	private long idTreno;
	
	@Column(name = "nome" , unique = true)
    private String nome;
	
	
	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "sigla", length = 50, unique = false)
	private String sigla;

	@Column(name = "immagine", length = 255)
	private String immagine;

	// Relazione Many-to-One con Market
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_market") // Chiave esterna che collega Treno con Market
//    private Market market; 
	@Column(name = "in_vendita")
	private boolean inVendita;

	@Column(name = "prezzo_vendita")
	private double prezzoVendita;
	
	@Column(name = "marca")
	private String marca;
	
	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public Set<Transazione> getTransazioni() {
		return transazioni;
	}




	public void setTransazioni(Set<Transazione> transazioni) {
		this.transazioni = transazioni;
	}




	public void setIdTreno(long idTreno) {
		this.idTreno = idTreno;
	}

	/*
	 * La relazione tra treni e vagoni è 1:N, perciò usiamo
	 * l'annotazione @OneToMany. mappedBy indica che la relazione è gestita dal
	 * campo treno in Vagone. cascade indica che ogni operazione fatta sul treno
	 * (persist, save, delete) viene propagata sul vagone. fetch indica che i vagoni
	 * associati al treno non verranno caricati subito, ma solo quando ce ne sarà
	 * l'effettivo bisogno.
	 */
	@OneToMany(mappedBy = "treno", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Vagone> vagoni;

	/*
	 * 
	 * @ElementCollection(targetClass = Valutazione.class ,fetch = FetchType.LAZY)
	 *
	 * Si usa per mappare una collezione di valori base o tipi enum senza creare
	 * un'entità separata. Semplicemente crea una tabella secondaria che contiene i
	 * valori della lista associandoli all'entità principale. targetClass specifica
	 * il tipo di dati memorizzato nella collezione, ovvero un enum di tipo
	 * Valutazione
	 *
	 * @CollectionTable(name = "valutazioni", joinColumns = @JoinColumn(name =
	 * "id_treno"))
	 *
	 * I dati della collezione verranno salvati in una tabella separata
	 * valutazioni_treno. In questa tabella sarà presente una chiave esterna che fa
	 * riferimento alla tabella Treni.
	 *
	 * @Enumerated(EnumType.STRING) /* Mappa gli enum nel database. I vari valori
	 * verranno memorizzati come stringhe.
	 */
	// @Column(name="valutazioni") DA RIGUARDARE E STUDIARE QUESTA TIPOLOGIA DI
	// GESTIONE BIAGIO GENIO
	// private List<Valutazione> valutazioni;

	@OneToMany( mappedBy = "treno", cascade = CascadeType.ALL)
	private Set <Valutazione> valutazioni;

	// Caricamente dell'utente per ora solo quando serve ma poco robusta a livello
	// di architettura ideata
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_owner", nullable = false)
	private User owner;

	@OneToMany( mappedBy = "treno", fetch = FetchType.LAZY)
	private Set<Transazione> transazioni;

	// Static metodo per costruire il treno all'esterno della classe.
	public static Treno build() {
		return new Treno();
	}
	
	
	

	// Costruttore
	public Treno() {
	    super();
	    this.vagoni = new LinkedList<Vagone>();         // Collezione di tipo List
	    this.valutazioni = new HashSet<Valutazione>();  // Collezione di tipo Set
	    this.transazioni = new HashSet<Transazione>(); 
	    this.inVendita= false; // Collezione di tipo Set
	}

	// Vagoni

	public List<Vagone> getVagoni() {
		return vagoni;
	}

	public void setVagoni(List<Vagone> vagoni) {
		this.vagoni = vagoni;
	}

	public void add(Vagone vagone) {
		vagoni.add(vagone);
		vagone.setTreno(this);// antilogica oop
	}

	public void remove(Vagone vagone) {
		vagoni.remove(vagone);
		vagone.setTreno(null);// antilogica oop
	}

	// Valutazioni


	public final void addValutazione(Valutazione valutazione) {
		valutazioni.add(valutazione);
		valutazione.setTreno(this);
	}

	public Set<Valutazione> getValutazioni() {
		return valutazioni;
	}




	public void setValutazioni(Set<Valutazione> valutazioni) {
		this.valutazioni = valutazioni;
	}




	public final void removeValutazione(Valutazione valutazione) {
		valutazioni.remove(valutazione);
		valutazione.setTreno(null);
	}
	
	
	
	//Calcoli valutazioni.
	 public double getValutazioneTotale() {
	        Iterator<Valutazione> it = valutazioni.iterator();
	        double sommaValutazioni = 0;
	        while (it.hasNext()) {
	            Valutazione valutazione = it.next();
	            sommaValutazioni += valutazione.getVotazione();
	        }
	        return sommaValutazioni;
	    }
	 
	 
	 public final double getMediaValutazioni() {
		    if (valutazioni.isEmpty()) {
		        return 0; // Nessuna valutazione disponibile
		    }
		    
		    Iterator<Valutazione> it = valutazioni.iterator();
		    double sommaValutazioni = 0;
		    while (it.hasNext()) {
		        Valutazione valutazione = it.next();
		        sommaValutazioni += valutazione.getVotazione();
		    }
		    return sommaValutazioni / valutazioni.size(); // Calcolo della media
		}


	// Metodi per evitare proprietà inutili

	public final double getPeso() {
		Iterator<Vagone> it = vagoni.iterator();
		double pesoTotale = 0;
		while (it.hasNext()) {
			Vagone vagone = it.next();
			pesoTotale += vagone.getPeso(); // Correzione dell'operatore per somma
		}
		return pesoTotale;
	}

	public final double getCosto() {
		Iterator<Vagone> it = vagoni.iterator();
		double costoTotale = 0;
		while (it.hasNext()) {
			Vagone vagone = it.next();
			costoTotale += vagone.getCosto(); // Correzione dell'operatore per somma
		}
		return costoTotale;
	}

	public final double getLunghezza() {
		Iterator<Vagone> it = vagoni.iterator();
		double lunghezzaTotale = 0;
		while (it.hasNext()) {
			Vagone vagone = it.next();
			lunghezzaTotale += vagone.getLunghezza(); // Correzione dell'operatore per somma
		}
		return lunghezzaTotale;
	}

	public boolean parte() {
		Vagone v = vagoni.get(0);
		if (v instanceof Motrice) {
			Motrice locomotiva = (Motrice) v;
			if (locomotiva.getPesoTrainabile() > this.getPeso())
				return true;
		}
		return false;
	}

	public final double getPostiTotali() {
		Iterator<Vagone> it = vagoni.iterator();
		double postiTotali = 0;
		while (it.hasNext()) {
			Vagone vagone = it.next();
			if (vagone instanceof Passeggero) {
				Passeggero passeggeri = (Passeggero) vagone;
				postiTotali += passeggeri.getNumeroPosti();
			}
		}
		return postiTotali;
	}

	// In Vendita ( boolean per gestione market )

	// Id Treno

	public Long getIdTreno() {
		return idTreno;
	}

	public boolean isInVendita() {
		return inVendita;
	}


	public void setInVendita(boolean inVendita) {
		this.inVendita = inVendita;
	}


	public void setIdTreno(Long idTreno) {
		this.idTreno = idTreno;
	}

	// Prezzo vendita (diveterà un calcolo che dipende dal prezzo del treno + il
	// prezzo di vendita dell'utente)

	public double getPrezzoVendita() {
		return prezzoVendita;
	}

	public void setPrezzoVendita(double prezzoVendita) {
		this.prezzoVendita = prezzoVendita;
	}

	// Owner

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	// Sigla usata per crearlo.

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	// Immagine del treno.

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	// Iterator per metodi

	public Iterator<Vagone> iterator() {
		return vagoni.iterator();
	}




	public double setLunghezza() {
		return this.getLunghezza();
	}


	@Override
	public String toString() {
		return "Treno [idTreno=" + idTreno + ", nome=" + nome + ", sigla=" + sigla + ", immagine=" + immagine
				+ ", InVendita=" + inVendita + ", prezzoVendita=" + prezzoVendita + ", marca=" + marca + ", vagoni="
				+ vagoni + ", valutazioni=" + valutazioni + ", owner=" + owner.getUserId() + ", transazioni=" + transazioni + "]";
	}

	
	




}
