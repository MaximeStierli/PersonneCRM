/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAO.UsersDAO;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yasmine.mabrouk
 */
public class ServletEditerProfil extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();
            request.setAttribute("EditUserError" ,"sucess");
            
            //recupere l'attribut username de la session
            HttpSession s = request.getSession(true);
            String username = (String)s.getAttribute("username");
            
            //recupere le user de la session
            UsersDAO usersDAO = new UsersDAO();
            Users userEnCour = usersDAO.select(username);
            
            
            if (!request.getParameter("Ancien_mots_passe").equals((String)userEnCour.getPwd())){
            session.setAttribute("editeProfilError1", "oui");}
            
            if (!request.getParameter("pwd").equals(request.getParameter("pwd2")) ){
                session.setAttribute("editeProfilError2", "oui");}
            
          if( request.getParameter("pwd").equals(request.getParameter("pwd2")) &&
                  request.getParameter("Ancien_mots_passe").equals((String)userEnCour.getPwd())){
            Long error = usersDAO.updateProfil(userEnCour.getId(),
                                                null,
                                                request.getParameter("pwd"),
                                                null,
                                                null);
            if(error>0){
                session.setAttribute("pwdModifier", "oui");
           request.getRequestDispatcher("/profil.jsp").forward(request, response); 
            }else{
           request.getRequestDispatcher("/modefierPwd.jsp").forward(request, response);
           }
           }else{
           request.getRequestDispatcher("/modefierPwd.jsp").forward(request, response);
           }    
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
