package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.OrdineDao;
import dao.OrdineDaoImpl;
import dao.ProdottoDao;
import dao.ProdottoDaoImpl;
import dao.UtenteDao;
import dao.UtenteDaoImpl;
import model.ComposizioneOrdineBean;
import model.OrdineBean;
import model.ProdottoBean;
import model.UtenteBean;

@WebServlet("/admin/AdminOrderControl")
public class AdminOrderControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private OrdineDao ordineDao;
	private UtenteDao utenteDao;
	private ProdottoDao prodottoDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		if (ds == null) throw new ServletException("DataSource non disponibile");
		
		ordineDao = new OrdineDaoImpl(ds);
		utenteDao = new UtenteDaoImpl(ds);
		prodottoDao = new ProdottoDaoImpl(ds);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		try {
			if (action == null || action.equalsIgnoreCase("viewOrders")) {
				String startDate = request.getParameter("startDate");
				String endDate = request.getParameter("endDate");
				String utenteId = request.getParameter("utenteId");
				
				Collection<OrdineBean> ordini = ordineDao.doRetrieveAllFiltered(startDate, endDate, utenteId);
				Collection<UtenteBean> utenti = utenteDao.doRetrieveAll("cognome"); // per la tendina utenti
				
				request.setAttribute("ordini", ordini);
				request.setAttribute("utenti", utenti);

				// per mantenere i from popolati
				request.setAttribute("paramStartDate", startDate);
				request.setAttribute("paramEndDate", endDate);
				request.setAttribute("paramUtenteId", utenteId);
				
				request.getRequestDispatcher("/WEB-INF/views/admin/orders.jsp").forward(request, response);
				
			} else if (action.equalsIgnoreCase("viewDetails")) {
				int id = Integer.parseInt(request.getParameter("id"));
				
				OrdineBean ordine = ordineDao.doRetrieveByKey(id);
				
				if (ordine != null) {
					UtenteBean cliente = utenteDao.doRetrieveByKey(ordine.getIdUtente());
					
					Map<Integer, ProdottoBean> prodottiMap = new HashMap<>();
					for (ComposizioneOrdineBean comp : ordine.getComposizioni()) {
						if (!prodottiMap.containsKey(comp.getIdProdotto())) {
							prodottiMap.put(comp.getIdProdotto(), prodottoDao.doRetrieveByKey(comp.getIdProdotto()));
						}
					}
					
					request.setAttribute("ordine", ordine);
					request.setAttribute("cliente", cliente);
					request.setAttribute("prodottiMap", prodottiMap);
					
					request.getRequestDispatcher("/WEB-INF/views/admin/orderDetails.jsp").forward(request, response);
				} else {
					response.sendRedirect(request.getContextPath() + "/admin/AdminOrderControl");
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