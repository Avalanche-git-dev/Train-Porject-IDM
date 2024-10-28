package com.treno.application.dto;

public class VagoneDTO {

	private long idVagone;
	
	private String nome;
	
	private Integer posizione;

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getPosizione() {
		return posizione;
	}
	public void setPosizione(Integer posizione) {
		this.posizione = posizione;
	}
	private double peso;

	private double costo;

	private double lunghezza;

	private String marca;

	private Long idTreno;
	private double volumeCargo;

	private double pesoTrainabile;

	private String tipoMotore;

	private int numeroPosti;

	private String classe;

	private int numeroPostiRistorante;
	private String menu;
	public VagoneDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VagoneDTO(long idVagone, double peso, double costo, double lunghezza, String marca, Long idTreno,
			double volumeCargo, double pesoTrainabile, String tipoMotore, int numeroPosti, String classe,
			int numeroPostiRistorante, String menu) {
		super();
		this.idVagone = idVagone;
		this.peso = peso;
		this.costo = costo;
		this.lunghezza = lunghezza;
		this.marca = marca;
		this.idTreno = idTreno;
		this.volumeCargo = volumeCargo;
		this.pesoTrainabile = pesoTrainabile;
		this.tipoMotore = tipoMotore;
		this.numeroPosti = numeroPosti;
		this.classe = classe;
		this.numeroPostiRistorante = numeroPostiRistorante;
		this.menu = menu;
	}
	public long getIdVagone() {
		return idVagone;
	}
	public void setIdVagone(long idVagone) {
		this.idVagone = idVagone;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	public double getLunghezza() {
		return lunghezza;
	}
	public void setLunghezza(double lunghezza) {
		this.lunghezza = lunghezza;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Long getIdTreno() {
		return idTreno;
	}
	public void setIdTreno(Long idTreno) {
		this.idTreno = idTreno;
	}
	public double getVolumeCargo() {
		return volumeCargo;
	}
	public void setVolumeCargo(double volumeCargo) {
		this.volumeCargo = volumeCargo;
	}
	public double getPesoTrainabile() {
		return pesoTrainabile;
	}
	public void setPesoTrainabile(double pesoTrainabile) {
		this.pesoTrainabile = pesoTrainabile;
	}
	public String getTipoMotore() {
		return tipoMotore;
	}
	public void setTipoMotore(String tipoMotore) {
		this.tipoMotore = tipoMotore;
	}
	public int getNumeroPosti() {
		return numeroPosti;
	}
	public void setNumeroPosti(int numeroPosti) {
		this.numeroPosti = numeroPosti;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public int getNumeroPostiRistorante() {
		return numeroPostiRistorante;
	}
	public void setNumeroPostiRistorante(int numeroPostiRistorante) {
		this.numeroPostiRistorante = numeroPostiRistorante;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}

}
