/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Users;
import java.util.ArrayList;

/**
 *
 * @author yasmine.mabrouk
 */
public class Main {
     public static void main(String[] args) {
         
         CommentaireDAO commentaireDAO = new CommentaireDAO();
         
         ArrayList<Users> list = commentaireDAO.top5Commentaire();
         
         for (int i=0 ; i <= list.size()-1 ; i++ ){
           
             System.out.println("user " + list.get(i).getUsername());
         }
     
     }
}
