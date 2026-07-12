package control;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ColoreBean;
import model.ImmagineBean;
import model.ProdottoBean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import dao.ColoreDao;
import dao.ColoreDaoImpl;
import dao.ImmagineDao;
import dao.ImmagineDaoImpl;
import dao.ProdottoDao;
import dao.ProdottoDaoImpl;

@WebServlet("/ProductControl")
public class ProductControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProdottoDao prodottoDao;
	private ImmagineDao immagineDao;
	private ColoreDao coloreDao;
       
    public ProductControl() {
        super();
    }

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		
		prodottoDao = new ProdottoDaoImpl(ds);
		immagineDao = new ImmagineDaoImpl(ds);
		coloreDao = new ColoreDaoImpl(ds);
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		try {
			if(action == null || action.equalsIgnoreCase("viewCatalog")) {
				Collection<ProdottoBean> tuttiProdotti = prodottoDao.doRetrieveAll("nome");
				List<ProdottoBean> prodottiDisp = new ArrayList<>();
				
				for(ProdottoBean p : tuttiProdotti) {
					if(p.isDisponibile()) {
						p.setImmagini((List<ImmagineBean>) immagineDao.doRetrieveByProdotto(p.getId()));
						prodottiDisp.add(p);
					}
				}
				
				request.setAttribute("prodotti", prodottiDisp);
				request.getRequestDispatcher("/WEB-INF/views/common/catalog.jsp").forward(request, response);
			
			} else if(action.equalsIgnoreCase("search")) {
				String text = request.getParameter("searchQuery");
				Collection<ProdottoBean> tuttiProdotti;
				
				if(text == null || text.trim().isEmpty()) 
					tuttiProdotti = prodottoDao.doRetrieveAll("nome");
				else
					tuttiProdotti = prodottoDao.doRetrieveByText(text);
				
				List<ProdottoBean> prodottiDisp = new ArrayList<>();
				
				for(ProdottoBean p : tuttiProdotti) {
					if(p.isDisponibile()) {
						p.setImmagini((List<ImmagineBean>) immagineDao.doRetrieveByProdotto(p.getId()));
						prodottiDisp.add(p);
					}
				}
				
				request.setAttribute("prodotti", prodottiDisp);
				request.getRequestDispatcher("/WEB-INF/views/common/catalog.jsp").forward(request, response);
				
			} else if(action.equalsIgnoreCase("viewProduct")) {
				int id = Integer.parseInt(request.getParameter("id"));
				ProdottoBean prodotto = prodottoDao.doRetrieveByKey(id);
				
				if(prodotto != null && prodotto.isDisponibile()) {
					prodotto.setImmagini((List<ImmagineBean>) immagineDao.doRetrieveByProdotto(id));
					prodotto.setColori((List<ColoreBean>) coloreDao.doRetrieveByProdotto(id));
					request.setAttribute("prodotto", prodotto);
					request.getRequestDispatcher("/WEB-INF/views/common/product.jsp").forward(request, response);
				} else {
					response.sendRedirect(request.getContextPath() + "/ProductControl");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore nel caricamento del catalogo");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
