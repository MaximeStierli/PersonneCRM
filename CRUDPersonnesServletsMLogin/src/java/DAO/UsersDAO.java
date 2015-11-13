/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Commentaire;
import Model.Users;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Vector;

/**
 *
 * @author Maxime Stierli <maxime.stierli@he-arc.ch>
 */
public class UsersDAO {
    public UsersDAO(){};

    public Users select(String username) {
        Connection conn = DBDataSource.getJDBCConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Users u = new Users();
        try {
            String query = "SELECT * FROM Users WHERE Username = ?";
            
            stmt = conn.prepareStatement(query);        
            stmt.setString(1, username);
            rs = stmt.executeQuery();
           
            // On ajoute les restaurants à la liste
            while(rs.next()){
                u.setId(rs.getLong("Numero"));
                u.setUsername(rs.getString("Username"));
                u.setPwd(rs.getString("pwd"));
                u.setPhoto(rs.getBlob("Photo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
                 return u;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } 
    }

    public Long create(int id, String username, String pwd) {
        return new Long(0);
    }
    
    public Long update(int id, String username, String pwd){
        return new Long(0);
    }

    public Long delete(int id) {
        return new Long(0);
    }
}
