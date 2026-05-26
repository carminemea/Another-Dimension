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
			boolean stessoId = p.getIdProdotto() == prodotto.getIdProdotto();
			boolean stessoColore = (p.getColoreScelto() != null && p.getColoreScelto().equals(prodotto.getColoreScelto()));
			boolean stessoTesto = (p.getTestoPersonalizzato() == null && prodotto.getTestoPersonalizzato() == null) || 
					              (p.getTestoPersonalizzato() != null && p.getTestoPersonalizzato().equals(prodotto.getTestoPersonalizzato()));
			
			if(stessoId && stessoColore && stessoTesto) {
				p.setQuantita(p.getQuantita()+prodotto.getQuantita());
				return;
			}
		}
		
		prodotti.add(prodotto);
	}
	
	public void increaseQuantity(ComposizioneOrdineBean prodotto) {
		for (ComposizioneOrdineBean p : prodotti) {
			boolean stessoId = p.getIdProdotto() == prodotto.getIdProdotto();
			boolean stessoColore = (p.getColoreScelto() != null && p.getColoreScelto().equals(prodotto.getColoreScelto()));
			boolean stessoTesto = (p.getTestoPersonalizzato() == null && prodotto.getTestoPersonalizzato() == null) || 
					              (p.getTestoPersonalizzato() != null && p.getTestoPersonalizzato().equals(prodotto.getTestoPersonalizzato()));
			
			if(stessoId && stessoColore && stessoTesto) {
				p.setQuantita(p.getQuantita()+1);
				return;
			}
		}
	}
	
	public void deleteProduct(ComposizioneOrdineBean prodotto) {
		prodotti.remove(prodotto);
	}
	
	public void decreaseQuantity(ComposizioneOrdineBean prodotto) {
		for (ComposizioneOrdineBean p : prodotti) {
			boolean stessoId = p.getIdProdotto() == prodotto.getIdProdotto();
			boolean stessoColore = (p.getColoreScelto() != null && p.getColoreScelto().equals(prodotto.getColoreScelto()));
			boolean stessoTesto = (p.getTestoPersonalizzato() == null && prodotto.getTestoPersonalizzato() == null) || 
					              (p.getTestoPersonalizzato() != null && p.getTestoPersonalizzato().equals(prodotto.getTestoPersonalizzato()));
			
			if(stessoId && stessoColore && stessoTesto) {
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
		
		return total;
	}
	
	public void clearCart() {
		prodotti.clear();
	}

}
