package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProdottoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nome;
	private String descrizione;
	private double prezzo;
	private boolean disponibile;
	private boolean testoPersonalizzabile;
	private List<ImmagineBean> immagini;
	private List<ColoreBean> colori;
	
	public ProdottoBean() {
		immagini = new ArrayList<ImmagineBean>();
		colori = new ArrayList<ColoreBean>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public boolean isDisponibile() {
		return disponibile;
	}

	public void setDisponibile(boolean disponibile) {
		this.disponibile = disponibile;
	}

	public boolean isTestoPersonalizzabile() {
		return testoPersonalizzabile;
	}

	public void setTestoPersonalizzabile(boolean testoPersonalizzabile) {
		this.testoPersonalizzabile = testoPersonalizzabile;
	}

	public List<ImmagineBean> getImmagini() {
		return immagini;
	}

	public void setImmagini(List<ImmagineBean> immagini) {
		this.immagini = immagini;
	}
	
	public void addImmagine(ImmagineBean immagine) {
		immagini.add(immagine);
	}
	
	public List<ColoreBean> getColori() {
		return colori;
	}

	public void setColori(List<ColoreBean> colori) {
		this.colori = colori;
	}
	
	public void addColore(ColoreBean colore) {
		this.colori.add(colore);
	}
	
}
