/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Blob;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Maxime Stierli <maxime.stierli@he-arc.ch>
 */
public class Users {
    private Long id=null;
    private String username;
    private String pwd;
    private String email;
    private Date dateReceptionBon;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateReceptionBon() {
        return dateReceptionBon;
    }

    public void setDateReceptionBon(Date ReceptionBon) {
        this.dateReceptionBon = ReceptionBon;
    }
    
    public boolean isReceiveThisMonth(){
        if(this.dateReceptionBon == null){
            return false;
        }else{
            //Initialisation du calendrier qui contient la date "dateReceptionBon"
        Calendar calReceive = Calendar.getInstance();
        calReceive.setTime(dateReceptionBon);
        //Initialisation du calendrier qui contient la date actuelle.
        Calendar calCurrentDate = Calendar.getInstance();
        //Si les mois sont identiques, on retourne true.
        return calCurrentDate.get(Calendar.MONTH) == calReceive.get(Calendar.MONTH);
        }    
    }    
}
