package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.ColoreDao;
import dao.ColoreDaoImpl;
import model.ColoreBean;

@WebServlet("/admin/AdminColorControl")
public class AdminColorControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ColoreDao coloreDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		if (ds == null) throw new ServletException("DataSource non disponibile");
		
		coloreDao = new ColoreDaoImpl(ds);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		try {
			if (action == null || action.equalsIgnoreCase("viewColori")) {
				Collection<ColoreBean> colori = coloreDao.doRetrieveAll();
				request.setAttribute("colori", colori);
				request.getRequestDispatcher("/WEB-INF/views/admin/colori.jsp").forward(request, response);
				
			} else if (action.equalsIgnoreCase("delete")) {
				int id = Integer.parseInt(request.getParameter("id"));
				coloreDao.doDelete(id);
				response.sendRedirect(request.getContextPath() + "/admin/AdminColorControl?action=viewColori");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore nella gestione dei colori");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action != null && action.equalsIgnoreCase("insert")) {
			try {
				String nome = request.getParameter("nome");
				String codiceHex = request.getParameter("codiceHex");
				
				ColoreBean colore = new ColoreBean();
				colore.setNome(nome);
				colore.setCodiceHex(codiceHex);
				
				coloreDao.doSave(colore);
				
				response.sendRedirect(request.getContextPath() + "/admin/AdminColorControl?action=viewColori");
				
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante l'inserimento del colore.");
			}
		} else {
			doGet(request, response);
		}
	}
}