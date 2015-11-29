/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAO.DBDataSource;
import DAO.UsersDAO;
import Model.Users;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yasmine.mabrouk
 */
public class testImage extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        Blob photo = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String query = "select photo from users where numero = ?";
            ServletOutputStream out = response.getOutputStream();
            HttpSession s = request.getSession(true);
            String username = (String) s.getAttribute("username");

            UsersDAO usersDAO = new UsersDAO();
            Users utilusateur = usersDAO.select(username);

            conn = DBDataSource.getJDBCConnection();
            stmt = conn.prepareStatement(query);
            stmt.setLong(1, utilusateur.getId());
            rs = stmt.executeQuery();
            if (rs.next()) {
                photo = rs.getBlob(1);
            } else {
                response.setContentType("text/html");
                out.println("<html><head><title>Person Photo</title></head>");
                out.println("<body><h1>No photo found for id= 001 </h1></body></html>");
                return;
            }

            response.setContentType("image/gif");
            response.setContentLength( (int) photo.length());
             InputStream is = photo.getBinaryStream();
            OutputStream os = response.getOutputStream();
            byte buf[] = new byte[(int) photo.length()];
            request.setAttribute(username, os);
            is.read(buf);
            os.write(buf);
            os.close();
//            InputStream in;
//
//            in = photo.getBinaryStream();
//
//            int length = (int) photo.length();
//
//            int bufferSize = 1024;
//            byte[] buffer = new byte[bufferSize];
//
//            while ((length = in.read(buffer)) != -1) {
//                System.out.println("writing " + length + " bytes");
//                out.write(buffer, 0, length);
//            }
//
//            in.close();
//            out.flush();
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(testImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
