package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<ComposizioneOrdineBean> prodotti;
	
	public CartBean() {
		prodotti = new ArrayList<>();
	}
	
	public List<ComposizioneOrdineBean> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<ComposizioneOrdineBean> prodotti) {
		this.prodotti = prodotti;
	}

	public void addProduct(ComposizioneOrdineBean prodotto) {
		for (ComposizioneOrdineBean p : prodotti) {
			if(isEqual(p, prodotto)) {
				p.setQuantita(p.getQuantita()+prodotto.getQuantita());
				return;
			}
		}
		
		prodotti.add(prodotto);
	}
	
	public void increaseQuantity(ComposizioneOrdineBean prodotto) {
		for (ComposizioneOrdineBean p : prodotti) {
			if(isEqual(p, prodotto)) {
				p.setQuantita(p.getQuantita()+1);
				return;
			}
		}
	}
	
	public void deleteProduct(ComposizioneOrdineBean prodotto) {
		for (ComposizioneOrdineBean p : prodotti) {
			if(isEqual(p, prodotto)) {
				prodotti.remove(p);
				return;
			}
		}
	}
	
	public void decreaseQuantity(ComposizioneOrdineBean prodotto) {
		for (ComposizioneOrdineBean p : prodotti) {
			if(isEqual(p, prodotto)) {
				p.setQuantita(p.getQuantita()-1);
				if(p.getQuantita() <= 0) {
					prodotti.remove(p);
				}
				return;
			}
		}
	}
	
	public double getTotalPrice() {
		double total = 0;
		for (ComposizioneOrdineBean p : prodotti) {
			total += p.getPrezzoAcquisto()*p.getQuantita();
		}
		
		return Math.round(total * 100.0) / 100.0;
	}
	
	public void clearCart() {
		prodotti.clear();
	}
	
	private boolean isEqual(ComposizioneOrdineBean p1, ComposizioneOrdineBean p2) {
		boolean stessoId = p1.getIdProdotto() == p2.getIdProdotto();
		boolean stessoColore = (p1.getColoreScelto() != null && p1.getColoreScelto().equals(p2.getColoreScelto()));
		boolean stessoTesto = (p1.getTestoPersonalizzato() == null && p2.getTestoPersonalizzato() == null) || 
				              (p1.getTestoPersonalizzato() != null && p1.getTestoPersonalizzato().equals(p2.getTestoPersonalizzato()));
		
		return (stessoId && stessoColore && stessoTesto);
	}

}
