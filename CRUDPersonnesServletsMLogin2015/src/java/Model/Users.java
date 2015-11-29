/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Blob;

/**
 *
 * @author Maxime Stierli <maxime.stierli@he-arc.ch>
 */
public class Users {
    private Long id=null;
    private String username;
    private String pwd;
    private String email;
    private Blob photo = null;

    public Users() {
    }
    
     public Users(String username) {
        this.username = username;
    }
     public Users(Long id,String username,String adress) {
        this.id = id;
        this.username = username;
        this.email = adress ;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    
}
