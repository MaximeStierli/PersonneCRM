package servlets;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import DAO.AjoutDAO;
import DAO.PersonneDAO;
import DAO.UsersDAO;
import Model.Personne;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author termine
 */
public class ServletCreationPersonne extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String nom = null, prenom = null, adresse = null, ville = null;
        try {

            HtmlHttpUtils.doHeader("creation personne", out);
            if (HtmlHttpUtils.isAuthenticate(request)) {
                nom = request.getParameter("nom");
                prenom = request.getParameter("prenom");
                adresse = request.getParameter("adresse");
                ville = request.getParameter("ville");

                if (nom != null && prenom != null) {
                    if (!nom.equals("") && !prenom.equals("")) {
                        PersonneDAO p = new PersonneDAO();
                        Long id = p.create(new Personne(nom, prenom, adresse, ville));
                        out.println("<p>" + id + "/" + nom + "/" + prenom + "/" + adresse + "/" + ville + "</p>");
                    } else {
                        out.println("<p>nom et prenom ne doivent pas etre null !!</p>");
                    }
                }
                /* syteme des points */
                HttpSession s = request.getSession(true);
                String username = (String) s.getAttribute("username");

                UsersDAO usersDAO = new UsersDAO();
                Users userEnCour = usersDAO.select(username);
                
                AjoutDAO ajoutDAO = new AjoutDAO();
                ajoutDAO.create(userEnCour.getId());
            }
            request.getRequestDispatcher("/listeDesPersonnes.jsp").forward(request, response);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
