/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.DemandeSponsoring;
import Entities.DemandeSponsoringReponse;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import projet.Source;

/**
 *
 * @author Khoubaib
 */
public class DemandeService {
    Connection connection;
    PreparedStatement pst;
    ResultSet rs;
    public DemandeService(){
        connection =(Connection) (Source.getInstance()).getConnection();
    }
   
     public ArrayList<DemandeSponsoringReponse> afficherDemande(){
            ArrayList<DemandeSponsoringReponse> listDemandeReponse = new ArrayList();
        try {
          
            PreparedStatement pst = (PreparedStatement) connection.prepareStatement("select d.Id , D.description , D.type , C.nom as nomCandidat , D.date from DemandeSponsoring as D , Candidat as C  where  D.Candidat_id=C.id ");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                DemandeSponsoringReponse d;
                d = new DemandeSponsoringReponse(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5));
                listDemandeReponse.add(d);
            }
            
            for (DemandeSponsoringReponse d : listDemandeReponse){
                System.out.println(d.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(DemandeService.class.getName()).log(Level.SEVERE, null, ex);
             
        }       
        return listDemandeReponse;
    } 
    
    
    
    
    public void addDemande (DemandeSponsoring d){
       
        try {
            String req="insert into Demandesponsoring (description,type,date,candidat_id) values (?,?,?,?)";
            pst=(PreparedStatement) connection.prepareStatement(req);
            pst.setString(1, d.getDescription());
            pst.setString(2, d.getType());
            pst.setString(3, d.getDate());
            pst.setInt(4, d.getCandidat_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DemandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }  
    public void removeDemande(int id) {

            String requete = "delete from Demandesponsoring where id='" + id + "'";
        try {
            java.sql.PreparedStatement pst = connection.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Demande supprimé");
        } catch (SQLException ex) {
           ex.printStackTrace();

       }
    } 
     public DemandeSponsoring getDemandeSponsoring(int id) {
        String req = "select * from demandeSponsoring where id = ?";
        DemandeSponsoring demandeSponsoring = new DemandeSponsoring();
        try {
            pst = (PreparedStatement) connection.prepareStatement(req);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                demandeSponsoring.setId(id);
                demandeSponsoring.setDescription(rs.getString(2));
                demandeSponsoring.setType(rs.getString(3));
                demandeSponsoring.setDate(rs.getString(4));    
                demandeSponsoring.setEtat(rs.getInt(5));                    
                demandeSponsoring.setCandidat_id(rs.getInt(6));   
            }
            return demandeSponsoring;
        } catch (SQLException ex) {
            Logger.getLogger(SponsoringService.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }
    }
}

