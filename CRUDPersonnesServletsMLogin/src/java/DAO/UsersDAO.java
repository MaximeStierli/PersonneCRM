/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Users;

/**
 *
 * @author Maxime Stierli <maxime.stierli@he-arc.ch>
 */
public class UsersDAO {
    public UsersDAO(){};

    public Users select(int id) {
        
        return new Users(null, null);
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
