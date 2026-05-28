package control;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UtenteBean;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import dao.UtenteDao;
import dao.UtenteDaoImpl;

/**
 * Servlet implementation class AuthControl
 */
@WebServlet("/AuthControl")
public class AuthControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UtenteDao utenteDao;
       
    public AuthControl() {
        super();
    }

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		if (ds == null) {
			throw new ServletException("DataSource non disponibile nel contesto");
		}
		utenteDao = new UtenteDaoImpl(ds);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processAction(request, response);
		} catch (ServletException | SQLException e) {
			
			e.printStackTrace();
			// response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void processAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String action = request.getParameter("action");
		if (action != null) {
			if (action.equalsIgnoreCase("redirectLogin")) {
				redirectLogin(request, response);
			} else if (action.equalsIgnoreCase("redirectRegister")) {
				redirectRegister(request, response);
			} else if (action.equalsIgnoreCase("login")) {
				login(request, response);
			} else if (action.equalsIgnoreCase("register")) {
				register(request, response);
			}
		}
	}
	
	private void redirectLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/common/login.jsp").forward(request, response);
	}
	
	private void redirectRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/common/register.jsp").forward(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UtenteBean utente = utenteDao.doRetrieveByEmailAndPassword(email, password);
		
		if (utente != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("role", utente.getRuolo());
			
			response.sendRedirect(request.getContextPath() + "/Home");
		} else {
			request.setAttribute("error", "Email o password errati!");
			request.getRequestDispatcher("/WEB-INF/views/common/login.jsp").forward(request, response);
		}
	}

	private void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UtenteBean utente = new UtenteBean();
		utente.setNome(request.getParameter("nome"));
		utente.setCognome(request.getParameter("cognome"));
		utente.setEmail(request.getParameter("email"));
		utente.setPassword(request.getParameter("password"));
		utente.setRuolo("user");
		
		try {
			utenteDao.doSave(utente);
		} catch (SQLException e) {
			request.setAttribute("error", "Errore durante la registrazione! Potrebbe essistere un account già associato a quest'email.");
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/AuthControl?action=redirectLogin");
	}

}