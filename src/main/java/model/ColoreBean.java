package model;

import java.io.Serializable;

public class ColoreBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nome;
	private String codiceHex;
	
	public ColoreBean() {
		
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

	public String getCodiceHex() {
		return codiceHex;
	}

	public void setCodiceHex(String codiceHex) {
		this.codiceHex = codiceHex;
	}
	
	
}
