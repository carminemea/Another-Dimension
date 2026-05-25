package model;

import java.io.Serializable;

public class ImmagineBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String path;
	private String mimeType;
	private int idProdotto;
	
	public ImmagineBean() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public int getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	
}
