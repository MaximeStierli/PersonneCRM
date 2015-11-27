/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static MemoryUser.Utilisateurs.users;
import Model.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.registry.infomodel.User;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Maxime Stierli <maxime.stierli@he-arc.ch>
 */
public class AjoutDAO {

    public AjoutDAO() {
    }

    ;
    
    
    public ArrayList<Users> top5Ajout() {
        UsersDAO usersDAO = new UsersDAO();
        ArrayList<Users> usersSelect = usersDAO.selectAll();
        ArrayList<Users> userTrie = new ArrayList<Users>();
        int value = this.countAjout(usersSelect.get(0).getId());
        int index = 0;
        Users user = null ;
        int conteur ;
        while (!usersSelect.isEmpty()) {
            conteur = 0;
            for (Users u : usersSelect) {
                if (countAjout(u.getId()) < value) {
                    value = countAjout(u.getId());
                    index = conteur;
                    user = u;
                }
                conteur = conteur + 1;
            }
            userTrie.add(user);
            usersSelect.remove(index);
        }
      return userTrie ;
    }

    public int countAjout(Long users_id) {
        Connection conn = DBDataSource.getJDBCConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int total = 0;
        try {
            String query = "SELECT COUNT(*) AS TOTAL FROM AJOUT WHERE users_numero = ? AND EXTRACT(month from dateAjout) = EXTRACT(month from sysdate) AND EXTRACT(year from dateAjout) = EXTRACT(year from sysdate)";

            stmt = conn.prepareStatement(query);
            stmt.setLong(1, users_id);
            rs = stmt.executeQuery();
            rs.next();
            total = rs.getInt("total");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
                return total;
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    public Long create(Long users_id) {
        Connection conn = DBDataSource.getJDBCConnection();
        OraclePreparedStatement pstmt = null;
        ResultSet rs = null;
        Long returnNumero = null;
        try {

            String query = "insert into Ajout(USERS_NUMERO) values (?) returning numero into ?";
            System.out.println("insertquery ->" + query);

            pstmt = (OraclePreparedStatement) conn.prepareStatement(query); //create a statement
            pstmt.setLong(1, users_id);
            pstmt.registerReturnParameter(2, OracleTypes.NUMBER);

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
}
