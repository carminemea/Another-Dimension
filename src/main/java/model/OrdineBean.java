package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class OrdineBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private Date data;
	private double totale;
	private String indirizzo;
	private int idUtente;
	private List<ComposizioneOrdineBean> composizioni;
	
	public OrdineBean() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	
	public List<ComposizioneOrdineBean> getComposizioni() {
	    return composizioni;
	}

	public void setComposizioni(List<ComposizioneOrdineBean> composizioni) {
	    this.composizioni = composizioni;
	}

	public void addComposizione(ComposizioneOrdineBean composizione) {
	    this.composizioni.add(composizione);
	}
}
