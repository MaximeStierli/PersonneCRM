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
import java.util.Vector;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Maxime Stierli <maxime.stierli@he-arc.ch>
 */
public class CommentaireDAO {
    public CommentaireDAO(){};

    public Vector<Commentaire>  select(Commentaire comm) {
        Connection conn = DBDataSource.getJDBCConnection();
        Statement stmt = null;
        ResultSet rs = null;
        Vector<Commentaire> resultList=new Vector();
        try {
            String query=null,sn=null,scomm=null,susers=null;
            boolean onedone=false;
            query= "select * from Commentaire";
       
          //tester si on a des critères
           if(comm.getId()!=null)sn=" numero="+comm.getId();
           if(comm.getCommentaire()!=null) scomm=" COMMENTAIRE like '%" +comm.getCommentaire()+"%' ";
           if(comm.getComm_users()!=null) susers=" PRENOM like '%" +comm.getComm_users()+"%' ";
           //si critères, contruire la clause where
           if (sn!=null || scomm!=null || susers!=null) query=query.concat(" WHERE ");
           //construction de la clause where
           if(sn!=null){query=query.concat(sn);onedone=true;}
           if(scomm!=null){if (onedone)query=query.concat(" AND "); query=query.concat(scomm); onedone=true;}
           if(susers!=null){if (onedone)query=query.concat(" AND "); query=query.concat(susers); onedone=true;}

           System.out.println(query);
           stmt = conn.createStatement(); //create a statement
           rs = stmt.executeQuery(query);

            while (rs.next()) {
                Long n = rs.getLong("NUMERO");
                String commentaire = rs.getString("COMMENTAIRE_USERS");
                Long users_id = rs.getLong("COMMENTAIRE");
                Commentaire c = new Commentaire(commentaire,users_id);
                resultList.add(c);
                System.out.println(n + "\t" + commentaire + "\t" + users_id);
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

    public Long create(Long id, String commentaire) {

        Connection conn = DBDataSource.getJDBCConnection();
        OraclePreparedStatement pstmt = null;
        ResultSet rs = null;
        Long returnNumero = null;
        try {

            String query = "insert into Commentaire(Commentaire_users,commentaire) values (?,?) returning numero into ?";
            System.out.println("insertquery ->" + query);

            pstmt = (OraclePreparedStatement) conn.prepareStatement(query); //create a statement
            pstmt.setLong(1, id);
            pstmt.setString(2,commentaire);
            pstmt.registerReturnParameter(5, OracleTypes.NUMBER);

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

    public Long delete(Commentaire comm) {

       int executeUpdate=0;
       if(comm.getId()!=null){//update via l'identifiant numero

        Connection conn = DBDataSource.getJDBCConnection();
        PreparedStatement pstmt = null;

        try {
            String q="delete from COMMENTAIRE where numero=?";
     
            System.out.println("deletequery ->" + q);

            pstmt = conn.prepareStatement(q); //create a statement
                //create a statement
            pstmt.setLong(1,comm.getId());
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
       }else return new Long (executeUpdate);
    }
}
