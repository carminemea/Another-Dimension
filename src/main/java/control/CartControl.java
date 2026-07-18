package control;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CartBean;
import model.ComposizioneOrdineBean;
import model.ProdottoBean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import dao.ProdottoDao;
import dao.ProdottoDaoImpl;

@WebServlet("/CartControl")
public class CartControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ProdottoDao prodottoDao;
	
    public CartControl() {
        super();
    }
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        if (ds == null) throw new ServletException("DataSource non disponibile");
        
        prodottoDao = new ProdottoDaoImpl(ds);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		try {
			if(action == null || action.equalsIgnoreCase("showCart")) {
				HttpSession session = request.getSession();
				CartBean cart = (CartBean) session.getAttribute("carrello");
			
				if(cart != null && !cart.getProdotti().isEmpty()) {
					Map<Integer, ProdottoBean> prodottiMap = new HashMap<>();
					for(ComposizioneOrdineBean comp : cart.getProdotti()) {
						if(!prodottiMap.containsKey(comp.getIdProdotto())) {
							prodottiMap.put(comp.getIdProdotto(), prodottoDao.doRetrieveByKey(comp.getIdProdotto()));
						}
					}
					request.setAttribute("prodottiMap", prodottiMap);
				}	
				request.getRequestDispatcher("/WEB-INF/views/common/cart.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore nel caricamento del carrello");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action!= null && action.equalsIgnoreCase("add")) {
			try {
				int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
				int quantita = Integer.parseInt(request.getParameter("quantita"));
				String colore = request.getParameter("coloreScelto");
				String testoPersonalizzato = request.getParameter("testoPersonalizzato");

				ProdottoBean prodotto = prodottoDao.doRetrieveByKey(idProdotto);
				
				if(prodotto != null && prodotto.isDisponibile()) {
					HttpSession session = request.getSession();
					CartBean cart = (CartBean) session.getAttribute("carrello");
					if(cart == null) {
						cart = new CartBean();
						session.setAttribute("carrello", cart);
					}
					
					ComposizioneOrdineBean comp = new ComposizioneOrdineBean();
					comp.setIdProdotto(idProdotto);
					comp.setQuantita(quantita);
					comp.setPrezzoAcquisto(prodotto.getPrezzo());
					if(colore != null && !colore.trim().isEmpty()) {
						comp.setColoreScelto(colore);
					}
					if(testoPersonalizzato != null && !testoPersonalizzato.trim().isEmpty()) {
						comp.setTestoPersonalizzato(testoPersonalizzato);
					}
					
					cart.addProduct(comp);
				}
				
				response.sendRedirect(request.getContextPath() + "/CartControl?action=showCart");
				
			} catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante l'aggiunta al carrello");
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/ProductControl");
            }
		}
	}

}
