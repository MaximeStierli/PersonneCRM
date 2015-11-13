/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Maxime Stierli <maxime.stierli@he-arc.ch>
 */
public class Commentaire {
    private Long id=null;
    private Long comm_user=null;
    private String commentaire;
    private Date dateAjout=null;
    
    public Commentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Long getComm_user() {
        return comm_user;
    }

    public void setComm_user(Long comm_user) {
        this.comm_user = comm_user;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Long getId() {
        return id;
    }
}
