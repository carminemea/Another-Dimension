package control;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javax.sql.DataSource;

import dao.OrdineDao;
import dao.OrdineDaoImpl;
import model.CartBean;
import model.OrdineBean;
import model.UtenteBean;

@WebServlet("/CheckoutControl")
public class CheckoutControl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private OrdineDao ordineDao;

    public CheckoutControl() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        if (ds == null) throw new ServletException("DataSource non disponibile");
        
        ordineDao = new OrdineDaoImpl(ds);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/CartControl?action=showCart");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UtenteBean utente = (UtenteBean) session.getAttribute("utente");
        CartBean carrello = (CartBean) session.getAttribute("carrello");

        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/AuthControl?action=redirectLogin");
            return;
        }
        if (carrello == null || carrello.getProdotti().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/CartControl?action=showCart");
            return;
        }

        String indirizzo = request.getParameter("indirizzo");
        
        OrdineBean ordine = new OrdineBean();
        ordine.setData(new Date(System.currentTimeMillis()));
        ordine.setTotale(carrello.getTotalPrice());
        ordine.setIndirizzo(indirizzo);
        ordine.setIdUtente(utente.getId());
        
        ordine.setComposizioni(carrello.getProdotti());

        try {
            ordineDao.doSave(ordine);
            
            carrello.clearCart();
            
            response.sendRedirect(request.getContextPath() + "/UserOrderControl?action=viewOrders");
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante il salvataggio dell'ordine nel database.");
        }
    }
}