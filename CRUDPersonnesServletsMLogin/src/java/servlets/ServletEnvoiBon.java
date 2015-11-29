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
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maxime Stierli <maxime.stierli@he-arc.ch>
 */
public class ServletEnvoiBon extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      
      
      // Recipient's email ID needs to be mentioned.
      HttpSession s = request.getSession(true); 
      
      String username = (String) s.getAttribute("username");
      UsersDAO userDao = new UsersDAO();
      Users user = userDao.select(username);
      //mettre a jour date base de donnée
      userDao.updateDate(user);
      
      String to = user.getEmail();
 
      // Sender's email ID needs to be mentioned
      String from = "ArcPeople@he-arc.ch";
 
      // Assuming you are sending email from localhost
      String host = "outlook.he-arc.ch";
 
      // Get system properties
      Properties properties = System.getProperties();
 
      // Setup mail server
      properties.setProperty("mail.smtp.host", host);
 
      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);
      
	  // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      
      String path = getServletContext().getRealPath("/");
      String filename = "Bon.pdf";
      DataSource source = new FileDataSource(path + filename);
      
      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);
         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));
         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));
         // Set Subject: header field
         message.setSubject("Gain du bon Arc'People");
         
         //partie message
         BodyPart messageBodyPart = new MimeBodyPart();
         messageBodyPart.setText("Bravo ! Tu as gagné notre bon Arc'People ! Félicitations !");
        
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        //partie fichier joint
        messageBodyPart = new MimeBodyPart();
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);
        // Send message
        Transport.send(message);
        String title = "Send Email";
        String res = "Sent message successfully....";
        String docType =
        "<!doctype html public \"-//w3c//dtd html 4.0 " +
        "transitional//en\">\n";
        out.println(docType +
        "<html>\n" +
        "<head><title>" + title + "</title></head>\n" +
        "<body bgcolor=\"#f0f0f0\">\n" +
        "<h1 align=\"center\">" + title + "</h1>\n" +
        "<p align=\"center\">" + res + "</p>\n" +
        "</body></html>");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
      
      
      
      
      
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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletEnvoiBon</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletEnvoiBon at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
   // @Override
    //protected void doGet(HttpServletRequest request, HttpServletResponse response)
    //       throws ServletException, IOException {
    //    processRequest(request, response);
   // }

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
