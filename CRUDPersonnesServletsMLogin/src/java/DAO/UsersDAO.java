/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Users;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleTypes;

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

    public Long create(int id, String username, String pwd, Blob photo) {
        Connection conn = DBDataSource.getJDBCConnection();
        OraclePreparedStatement pstmt = null;
        ResultSet rs = null;
        Long returnNumero = null;
        try {

            String query = "insert into Users(username,pwd,photo) values (?,?,?) returning numero into ?";
            System.out.println("insertquery ->" + query);

            pstmt = (OraclePreparedStatement) conn.prepareStatement(query); //create a statement
            pstmt.setString(1, username);
            pstmt.setString(2,pwd);
            pstmt.setBlob(3,photo);
            pstmt.setLong(4, OracleTypes.NUMBER);

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
    
    public Long update(int id, String username, String pwd){
        return new Long(0);
    }

    public Long delete(int id) {
        int executeUpdate=0;

        Connection conn = DBDataSource.getJDBCConnection();
        PreparedStatement pstmt = null;

        try {
            String q="delete from Users where numero=?";
     
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
