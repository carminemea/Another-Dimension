package control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import dao.ImmagineDao;
import dao.ImmagineDaoImpl;
import dao.ProdottoDao;
import dao.ProdottoDaoImpl;
import model.ImmagineBean;
import model.ProdottoBean;

@WebServlet("/admin/AdminProductControl")
@MultipartConfig(
	fileSizeThreshold = 1024 * 1024 * 2,  // 2MB: i file più grandi vengono scritti su disco temporaneo
	maxFileSize = 1024 * 1024 * 10,       // 10MB: dimensione massima del singolo file
	maxRequestSize = 1024 * 1024 * 50     // 50MB: dimensione massima della request
)
public class AdminProductControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProdottoDao prodottoDao;
	private ImmagineDao immagineDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		if (ds == null) {
			throw new ServletException("DataSource non disponibile nel contesto");
		}
		prodottoDao = new ProdottoDaoImpl(ds);
		immagineDao = new ImmagineDaoImpl(ds);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		try {
			if (action == null || action.equalsIgnoreCase("viewCatalog")) {
				Collection<ProdottoBean> prodotti = prodottoDao.doRetrieveAll("id");
				request.setAttribute("prodotti", prodotti);
				request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);
			
			} else if (action.equalsIgnoreCase("showInsertForm")) {
				request.getRequestDispatcher("/WEB-INF/views/admin/insertProduct.jsp").forward(request, response);
			
			} else if (action.equalsIgnoreCase("showUpdateForm")) {
				int id = Integer.parseInt(request.getParameter("id"));
				ProdottoBean prodotto = prodottoDao.doRetrieveByKey(id);
				
				request.setAttribute("prodotto", prodotto);
				request.getRequestDispatcher("/WEB-INF/views/admin/updateProduct.jsp").forward(request, response);
		
			} else if (action.equalsIgnoreCase("delete")) {
				int id = Integer.parseInt(request.getParameter("id"));
				prodottoDao.doDelete(id);
				response.sendRedirect(request.getContextPath() + "/admin/AdminProductControl?action=viewCatalog");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore nel database");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action != null && action.equalsIgnoreCase("insert")) {
			try {
				String nome = request.getParameter("nome");
				String descrizione = request.getParameter("descrizione");
				double prezzo = Double.parseDouble(request.getParameter("prezzo"));
				boolean disponibile = request.getParameter("disponibile") != null;
				boolean testoPersonalizzabile = request.getParameter("testoPersonalizzabile") != null;
				
				ProdottoBean prodotto = new ProdottoBean();
				prodotto.setNome(nome);
				prodotto.setDescrizione(descrizione);
				prodotto.setPrezzo(prezzo);
				prodotto.setDisponibile(disponibile);
				prodotto.setTestoPersonalizzabile(testoPersonalizzabile);
				
				prodottoDao.doSave(prodotto); 
				
				Part filePart = request.getPart("immagine");
				if (filePart != null && filePart.getSize() > 0) {
					String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
					String mimeType = filePart.getContentType();
					
					String uploadPath = getServletContext().getRealPath("") + File.separator + "images" + File.separator + "products";
					File uploadDir = new File(uploadPath);
					if (!uploadDir.exists()) {
						uploadDir.mkdir();
					}
					
					String filePath = uploadPath + File.separator + fileName;
					filePart.write(filePath);
					
					ImmagineBean immagine = new ImmagineBean();
					immagine.setIdProdotto(prodotto.getId());
					immagine.setPath("images/products/" + fileName);
					immagine.setMimeType(mimeType);
					
					immagineDao.doSave(immagine);
				}
				
				response.sendRedirect(request.getContextPath() + "/admin/AdminProductControl?action=viewCatalog");
				
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante l'inserimento del prodotto.");
			}
		} else if (action != null && action.equalsIgnoreCase("update")) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				String nome = request.getParameter("nome");
				String descrizione = request.getParameter("descrizione");
				double prezzo = Double.parseDouble(request.getParameter("prezzo"));
				boolean disponibile = request.getParameter("disponibile") != null;
				boolean testoPersonalizzabile = request.getParameter("testoPersonalizzabile") != null;
				
				ProdottoBean prodotto = new ProdottoBean();
				prodotto.setId(id);
				prodotto.setNome(nome);
				prodotto.setDescrizione(descrizione);
				prodotto.setPrezzo(prezzo);
				prodotto.setDisponibile(disponibile);
				prodotto.setTestoPersonalizzabile(testoPersonalizzabile);
				
				prodottoDao.doUpdate(prodotto); 
				
				response.sendRedirect(request.getContextPath() + "/admin/AdminProductControl?action=viewCatalog");
				
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante l'aggiornamento del prodotto.");
			} 
		} else {
			doGet(request, response);
		}
	}
}