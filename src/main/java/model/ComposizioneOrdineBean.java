package model;

import java.io.Serializable;
import java.util.List;

public class ComposizioneOrdineBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int idOrdine;
	private int idProdotto;
	private int quantita;
	private double prezzoAcquisto;
	private String coloreScelto;
	private String testoPersonalizzato;
	
	public ComposizioneOrdineBean() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public int getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public double getPrezzoAcquisto() {
		return prezzoAcquisto;
	}

	public void setPrezzoAcquisto(double prezzoAcquisto) {
		this.prezzoAcquisto = prezzoAcquisto;
	}

	public String getColoreScelto() {
		return coloreScelto;
	}

	public void setColoreScelto(String coloreScelto) {
		this.coloreScelto = coloreScelto;
	}

	public String getTestoPersonalizzato() {
		return testoPersonalizzato;
	}

	public void setTestoPersonalizzato(String testoPersonalizzabile) {
		this.testoPersonalizzato = testoPersonalizzabile;
	}

}
