package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.ImmagineDao;
import dao.ImmagineDaoImpl;
import dao.OrdineDao;
import dao.OrdineDaoImpl;
import dao.ProdottoDao;
import dao.ProdottoDaoImpl;
import dao.UtenteDao;
import dao.UtenteDaoImpl;
import model.ComposizioneOrdineBean;
import model.ImmagineBean;
import model.OrdineBean;
import model.ProdottoBean;
import model.UtenteBean;

@WebServlet("/UserOrderControl")
public class UserOrderControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private OrdineDao ordineDao;
	private UtenteDao utenteDao;
	private ProdottoDao prodottoDao;
	private ImmagineDao immagineDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		if (ds == null) throw new ServletException("DataSource non disponibile");
		
		ordineDao = new OrdineDaoImpl(ds);
		utenteDao = new UtenteDaoImpl(ds);
		prodottoDao = new ProdottoDaoImpl(ds);
		immagineDao = new ImmagineDaoImpl(ds);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		try {
			if (action == null || action.equalsIgnoreCase("viewOrders")) {
				
				UtenteBean user = (UtenteBean) request.getSession().getAttribute("utente");
				
				Collection<OrdineBean> ordini = ordineDao.doRetrieveByUser(user.getId());
				
				request.setAttribute("ordini", ordini);
				
				request.getRequestDispatcher("/WEB-INF/views/common/userOrders.jsp").forward(request, response);
				
			} else if (action.equalsIgnoreCase("viewDetails")) {
				int id = Integer.parseInt(request.getParameter("id"));
				
				OrdineBean ordine = ordineDao.doRetrieveByKey(id);
				
				if (ordine != null) {
					UtenteBean cliente = utenteDao.doRetrieveByKey(ordine.getIdUtente());
					
					//Recupero i nomi dei Prodotti (usiamo una Map per comodità nella JSP)
					Map<Integer, ProdottoBean> prodottiMap = new HashMap<>();
					for (ComposizioneOrdineBean comp : ordine.getComposizioni()) {
						if (!prodottiMap.containsKey(comp.getIdProdotto())) {
							ProdottoBean prodotto = prodottoDao.doRetrieveByKey(comp.getIdProdotto());
							List<ImmagineBean> immagini = (List<ImmagineBean>) immagineDao.doRetrieveByProdotto(prodotto.getId());
							prodotto.setImmagini(immagini);
							prodottiMap.put(comp.getIdProdotto(), prodotto);
						}
					}
					
					request.setAttribute("ordine", ordine);
					request.setAttribute("cliente", cliente);
					request.setAttribute("prodottiMap", prodottiMap);
					
					request.getRequestDispatcher("/WEB-INF/views/common/userOrderDetails.jsp").forward(request, response);
				} else {
					response.sendRedirect(request.getContextPath() + "/UserOrderControl");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore nel caricamento degli ordini");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}