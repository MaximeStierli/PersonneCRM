/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Commentaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Maxime Stierli <maxime.stierli@he-arc.ch>
 */
public class CommentaireDAO {
    public CommentaireDAO(){};

    public Vector<Commentaire>  selectAll() {
        Connection conn = DBDataSource.getJDBCConnection();
        Statement stmt = null;
        ResultSet rs = null;
        Vector<Commentaire> resultList=new Vector();
        try {
            String query=null,sn=null,scomm=null,susers=null;
            boolean onedone=false;
            query= "select * from Commentaire";

           System.out.println(query);
           stmt = conn.createStatement(); //create a statement
           rs = stmt.executeQuery(query);

            while (rs.next()) {
                Long n = rs.getLong("NUMERO");
                String commentaire = rs.getString("COMMENTAIRE_USERS");
                Long users_id = rs.getLong("COMMENTAIRE");
                Date dateAjout = rs.getDate("DateAjout");
                Commentaire c = new Commentaire();
                c.setId(n);
                c.setCommentaire(commentaire);
                c.setComm_user(users_id);
                c.setDateAjout(dateAjout);
                resultList.add(c);
                System.out.println(n + "\t" + commentaire + "\t" + users_id + "\t" + dateAjout);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
                 return resultList;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } 
    }
    
    public Commentaire select(Long id) {
        Connection conn = DBDataSource.getJDBCConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Commentaire c = new Commentaire();
        try {
            String query = "SELECT * FROM Commentaire WHERE numero = ?";
            
            stmt = conn.prepareStatement(query);        
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
           
            // On ajoute les restaurants à la liste
            while(rs.next()){
                c.setId(rs.getLong("Numero"));
                c.setCommentaire(rs.getString("Commentaire"));
                c.setComm_user(rs.getLong("commentaire_users"));
                c.setDateAjout(rs.getDate("DateAjout"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
                 return c;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } 
    }

    public Long create(Long id, String commentaire) {

        Connection conn = DBDataSource.getJDBCConnection();
        OraclePreparedStatement pstmt = null;
        ResultSet rs = null;
        Long returnNumero = null;
        try {

            String query = "insert into Commentaire(USER_NUMERO,commentaire) values (?,?) returning numero into ?";
            System.out.println("insertquery ->" + query);

            pstmt = (OraclePreparedStatement) conn.prepareStatement(query); //create a statement
            pstmt.setLong(1, id);
            pstmt.setString(2,commentaire);
            pstmt.registerReturnParameter(3, OracleTypes.NUMBER);

            int count = pstmt.executeUpdate();
            conn.commit();

            if (count > 0) {
               rs = pstmt.getReturnResultSet(); //rest is not null and not empty
                while (rs.next()) {
                    returnNumero = rs.getLong(1);
                    System.out.println(returnNumero);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
                return returnNumero;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public Long delete(Long id) {

       int executeUpdate=0;

        Connection conn = DBDataSource.getJDBCConnection();
        PreparedStatement pstmt = null;

        try {
            String q="delete from COMMENTAIRE where numero=?";
     
            System.out.println("deletequery ->" + q);

            pstmt = conn.prepareStatement(q); //create a statement
                //create a statement
            pstmt.setLong(1,id);
            executeUpdate = pstmt.executeUpdate();
            conn.commit();
            System.out.println( executeUpdate + " Rows modified" ) ;


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
                conn.close();
                return new Long(executeUpdate);
            } catch (SQLException e) {
                e.printStackTrace();
                return new Long (executeUpdate);
            }
        }
    }
}
