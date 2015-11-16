/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
public class AjoutDAO {
    public AjoutDAO(){};
    // attribut id??  id = ajout_user?
    public int countAjout(long id) {
        return 0;
    }

    public Long create(long id) {
        return new Long(0);
    }
    
    
    public Long delete(long id) {
        return new Long(0);
    }
}
