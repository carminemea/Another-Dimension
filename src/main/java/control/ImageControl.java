package control;

import java.io.*;
import java.sql.SQLException;

import javax.sql.DataSource;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import dao.ImmagineDao;
import dao.ImmagineDaoImpl;
import model.ImmagineBean;

@WebServlet("/ImageControl")
public class ImageControl extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ImmagineDao immagineDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        if (ds == null) {
            throw new ServletException("DataSource non disponibile nel contesto applicativo.");
        }
        immagineDao = new ImmagineDaoImpl(ds);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action != null && action.equalsIgnoreCase("show")) {
            int idImmagine = Integer.parseInt(request.getParameter("id"));
            try {
                ImmagineBean bean = immagineDao.doRetrieveByKey(idImmagine);
                
                if (bean != null) {
                    String mimeType = bean.getMimeType();
                    String path = bean.getPath();
                    response.setContentType(mimeType);
                    
                    try (InputStream is = new FileInputStream(path)) {
                        OutputStream os = response.getOutputStream();
                        is.transferTo(os);
                    } catch (IOException ioe) {
                        System.err.println("Error:" + ioe.getMessage());
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error:" + e.getMessage());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}