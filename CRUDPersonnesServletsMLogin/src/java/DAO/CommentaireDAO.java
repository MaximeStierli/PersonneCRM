/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Commentaire;
import Model.Personne;
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

    public Vector<Personne>  select(Commentaire comm) {
        Connection conn = DBDataSource.getJDBCConnection();
        Statement stmt = null;
        ResultSet rs = null;
        Vector<Personne> resultList=new Vector();
        try {
            String query=null,sn=null,snom=null,sprenom=null,sadr=null,sville=null;
            boolean onedone=false;
            query= "select * from Commentaire";
       
          //tester si on a des critères
           if(comm.getId()!=null)sn=" numero="+comm.getId();
           if(comm.getNom()!=null) snom=" NOM like '%" +comm.getNom()+"%' ";
           if(p.getPrenom()!=null) sprenom=" PRENOM like '%" +comm.getPrenom()+"%' ";
           if(p.getAdresse()!=null) sadr=" ADRESSE like '%" +comm.getAdresse()+"%' ";
           if(p.getVille()!=null) sville=" VILLE like '%" +comm.getVille()+"%' ";
           //si critères, contruire la clause where
           if (sn!=null || snom!=null || sprenom!=null || sadr!=null || sville!=null) query=query.concat(" WHERE ");
           //construction de la clause where
           if(sn!=null){query=query.concat(sn);onedone=true;}
           if(snom!=null){if (onedone)query=query.concat(" AND "); query=query.concat(snom); onedone=true;}
           if(sprenom!=null){if (onedone)query=query.concat(" AND "); query=query.concat(sprenom); onedone=true;}
           if(sadr!=null){if (onedone)query=query.concat(" AND "); query=query.concat(sadr); onedone=true;}
           if(sville!=null){if (onedone)query=query.concat(" AND "); query=query.concat(sville); onedone=true;}

           System.out.println(query);
           stmt = conn.createStatement(); //create a statement
           rs = stmt.executeQuery(query);

            while (rs.next()) {
                Long n = rs.getLong("NUMERO");
                String nom = rs.getString("NOM");
                String prenom = rs.getString("PRENOM");
                String adresse = rs.getString("ADRESSE");
                String ville = rs.getString("VILLE");
                Personne pers=new Personne();
                pers.setId(n);pers.setNom(nom);pers.setPrenom(prenom);pers.setAdresse(adresse);pers.setVille(ville);
                resultList.add(pers);
                System.out.println(n + "\t" + nom + "\t" + prenom + "\t" + adresse + "\t" + ville);
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
