/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Users;
import java.awt.Image;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Maxime Stierli <maxime.stierli@he-arc.ch>
 */
public class UsersDAO {
    public UsersDAO(){};
    
        public ArrayList<Users> selectAll() {
        Connection conn = DBDataSource.getJDBCConnection();
        Statement stmt = null;
        ResultSet rs = null;
        
         ArrayList<Users> users = new  ArrayList<Users>();
        try {
             stmt = conn.createStatement();
            String query = "SELECT Numero,Username,email,pwd FROM Users ";
            
           
            rs = stmt.executeQuery(query);
           
            while(rs.next()){
                Users u = new Users();
                u.setId(rs.getLong("Numero"));
                u.setUsername(rs.getString("Username"));
                u.setEmail(rs.getString("email"));
                u.setPwd(rs.getString("pwd"));
                users.add(u);
            }
            return users ;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                
            }
            return users ;
        } 
    }

     public Image getphotoById (long user_id){
     Connection conn = DBDataSource.getJDBCConnection();
         System.out.println("connexion base donnée sucsse");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT BLOBTOIMAGE(?) as image FROM DUAL";
            
            stmt = conn.prepareStatement(query);        
            stmt.setLong(1, user_id);
            rs = stmt.executeQuery();
            System.out.println("image avant finally");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
                System.out.println("image finally");
                System.out.println(((Image) rs.getObject("image")).toString());
                return (Image) rs.getObject("image");
                
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } 
   }

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
           
            while(rs.next()){
                u.setId(rs.getLong("Numero"));
                u.setUsername(rs.getString("Username"));
                u.setEmail(rs.getString("email"));
                u.setPwd(rs.getString("pwd"));
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

    public Long create(Long id, String username, String pwd, String email,Blob photo) {
        Connection conn = DBDataSource.getJDBCConnection();
        OraclePreparedStatement pstmt = null;
        ResultSet rs = null;
        Long returnNumero = null;
        try {

            String query = "insert into Users(username,pwd,email) values (?,?,?) returning numero into ?";
            System.out.println("insertquery ->" + query);

            pstmt = (OraclePreparedStatement) conn.prepareStatement(query); //create a statement
            pstmt.setString(1, username);
            pstmt.setString(2,pwd);
            pstmt.setString(3,email);
            pstmt.registerReturnParameter(4, OracleTypes.NUMBER);

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
    
    public Long updateProfil(Long id, String username, String pwd, String email, Blob photo){
        int executeUpdate = 0;
            String query = null, endquery = null,susername = null, spwd = null, semail = null;
            Connection conn = DBDataSource.getJDBCConnection();
            Statement stmt = null;
            try {
                               
                boolean onedone = false;
                query = "update USERS SET ";
                System.out.println("init query " + query);
                endquery = " WHERE numero=" + id;

                if (username != null) {
                    susername = " USERNAME='" + username + "'";
                }
                System.out.println("spwd " + spwd);
                if (pwd != null) {
             
                    spwd = " PWD='" + pwd + "'";
                }
                System.out.println("spwd " + spwd);
                if (email != null) {
                    
                    semail = " EMAIL='" + email + "'";
                }
                
                 if (susername != null) {
                    query = query.concat(susername);
                    onedone = true;
                }
           System.out.println("avant pwd query " + query);
                 if (spwd != null) {
                    if (onedone) {
                        query = query.concat(",");
                    }
                    query = query.concat(spwd);
                    onedone = true;
                }
                 System.out.println("apres pwd query " + query);
                 if (semail != null) {
                    if (onedone) {
                        query = query.concat(",");
                    }
                    query = query.concat(semail);
                    onedone = true;
                }              
              
                query = query.concat(endquery);

                System.out.println("updatequery ->" + query);

                //create a statement
                stmt = conn.createStatement();
                executeUpdate = stmt.executeUpdate(query);
                conn.commit();
                System.out.println(executeUpdate + " Rows modified");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    stmt.close();
                    conn.close();
                    return new Long(executeUpdate);
                } catch (SQLException e) {
                    e.printStackTrace();
                    return new Long(executeUpdate);
                }
            }
    }

    public Long delete(Long id) {
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
