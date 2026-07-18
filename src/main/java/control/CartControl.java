package control;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import dao.ImmagineDao;
import dao.ImmagineDaoImpl;
import dao.ProdottoDao;
import dao.ProdottoDaoImpl;
import model.CartBean;
import model.ComposizioneOrdineBean;
import model.ImmagineBean;
import model.ProdottoBean;

@WebServlet("/CartControl")
public class CartControl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ProdottoDao prodottoDao;
    private ImmagineDao immagineDao;
       
    public CartControl() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        if (ds == null) throw new ServletException("DataSource non disponibile");
        
        prodottoDao = new ProdottoDaoImpl(ds);
        immagineDao = new ImmagineDaoImpl(ds);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            if(action == null || action.equalsIgnoreCase("showCart")) {
                HttpSession session = request.getSession();
                CartBean cart = (CartBean) session.getAttribute("carrello");
                
                if (cart != null && !cart.getProdotti().isEmpty()) {
                    Map<Integer, ProdottoBean> prodottiMap = new HashMap<>();
                    for (ComposizioneOrdineBean comp : cart.getProdotti()) {
                        if (!prodottiMap.containsKey(comp.getIdProdotto())) {
                            ProdottoBean p = prodottoDao.doRetrieveByKey(comp.getIdProdotto());
                            p.setImmagini((List<ImmagineBean>) immagineDao.doRetrieveByProdotto(p.getId()));
                            prodottiMap.put(comp.getIdProdotto(), p);
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
        
        if(action != null) {
            try {
                int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
                String colore = request.getParameter("coloreScelto");
                String testoPersonalizzato = request.getParameter("testoPersonalizzato");
                
                HttpSession session = request.getSession();
                CartBean cart = (CartBean) session.getAttribute("carrello");
                if (cart == null) {
                    cart = new CartBean();
                    session.setAttribute("carrello", cart);
                }
                
                ComposizioneOrdineBean comp = new ComposizioneOrdineBean();
                comp.setIdProdotto(idProdotto);
                if (colore != null && !colore.trim().isEmpty()) comp.setColoreScelto(colore);
                if (testoPersonalizzato != null && !testoPersonalizzato.trim().isEmpty()) comp.setTestoPersonalizzato(testoPersonalizzato);
                
                if(action.equalsIgnoreCase("add")) {
                    int quantita = Integer.parseInt(request.getParameter("quantita"));
                    ProdottoBean prodotto = prodottoDao.doRetrieveByKey(idProdotto);
                    if (prodotto != null && prodotto.isDisponibile()) {
                        comp.setQuantita(quantita);
                        comp.setPrezzoAcquisto(prodotto.getPrezzo());
                        cart.addProduct(comp);
                    }
                } else if(action.equalsIgnoreCase("increase")) {
                    cart.increaseQuantity(comp);
                } else if(action.equalsIgnoreCase("decrease")) {
                    cart.decreaseQuantity(comp);
                } else if(action.equalsIgnoreCase("delete")) {
                    cart.deleteProduct(comp);
                }
                
                response.sendRedirect(request.getContextPath() + "/CartControl?action=showCart");
                
            } catch (SQLException | NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/CartControl?action=showCart");
            }
        }
    }
}