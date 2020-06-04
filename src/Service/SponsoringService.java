 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Sponsoring;
import Entities.SponsoringModel;
import Entities.SponsoringReponse;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import projet.Source;

public class SponsoringService {
    
    Connection connection;
    PreparedStatement pst;
    ResultSet rs;
    
    public SponsoringService(){
        connection = (Connection) (Source.getInstance()).getConnection();//represente une connexion à une BD
    }
     public ArrayList<SponsoringModel> afficherSponsoring(){
            ArrayList<SponsoringModel> listSponsoringModel = new ArrayList();
        try {
         // Pour réaliser des requêtes de sélection, un objet de type Statement doit être généré
            PreparedStatement pst = (PreparedStatement) connection.prepareStatement("select S.id, S.description , S.type , C.nom as nomCandidat ,  S.image , S.date_debut , S.date_fin  from Sponsoring as S , Candidat as C where  S.Candidat_id=C.id ");
            //Le résultat d'une requête est récupéré au moyen d'un objet de type ResultSet
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                SponsoringModel s;
                 s = new SponsoringModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                listSponsoringModel.add(s);
             
            }
            
            for (SponsoringModel d : listSponsoringModel){
                System.out.println(d.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(SponsoringService.class.getName()).log(Level.SEVERE, null, ex);
             
        }       
        return listSponsoringModel;
    } 
    
    public void add(Sponsoring a) {
        try {
            String req="insert into Sponsoring (description,type,candidat_id,sponsor_id,image,date_debut,date_fin) values (?,?,?,?,?,?,?)";
            pst= (PreparedStatement) connection.prepareStatement(req);
            pst.setString(1, a.getDescription());
            pst.setString(2, a.getType());
            pst.setInt(3, a.getCandidat_id());
            pst.setInt(4, a.getSponsor_id());
            pst.setString(5, a.getImage());
            pst.setString(6, a.getDate_debut());
            pst.setString(7, a.getDate_fin());
            pst.executeUpdate();
        } catch (SQLException ex) {
           Logger.getLogger(SponsoringService.class.getName()).log(Level.SEVERE, null, ex);
         
        }
    }
  
    public void removeSponsoring(int id) {

            String requete = "delete from Sponsoring where id='" + id + "'";
        try {
           java.sql.PreparedStatement ps = connection.prepareStatement(requete);
            ps.executeUpdate();
            System.out.println("publicité supprimé");
        } catch (SQLException ex) {
            ex.printStackTrace();

       }
    } 
    public void update(Sponsoring c) {
         String req = "update sponsoring set description = ?, type = ?, image=?,  candidat_id= ?, date_debut=?, date_fin=?  where id = ?";
        try {
            pst= (PreparedStatement) connection.prepareStatement(req);
            pst.setString(1, c.getDescription());
            pst.setString(2, c.getType());
            pst.setString(3, c.getImage());
            pst.setInt(4, c.getCandidat_id()); 
            pst.setString(5, c.getDate_debut());
            pst.setString(6, c.getDate_fin());
            pst.setInt(7, c.getId());
            
            pst.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        } 
    }
       
    public Sponsoring getSponsoring(int id) {
        String req = "select * from sponsoring where id = ?";
        Sponsoring sponsoring = new Sponsoring();
        try {
            pst = (PreparedStatement) connection.prepareStatement(req);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                sponsoring.setId(id);
                sponsoring.setDescription(rs.getString(2));
                sponsoring.setType(rs.getString(3));
                sponsoring.setImage(rs.getString(4));    
                sponsoring.setSponsor_id(rs.getInt(5));                    
                sponsoring.setCandidat_id(rs.getInt(6));
                sponsoring.setDate_debut(rs.getString(7)); 
                sponsoring.setDate_fin(rs.getString(8)); 
                
            }
            return sponsoring;
        } catch (SQLException ex) {
            Logger.getLogger(SponsoringService.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }
    }
    public  ObservableList<XYChart.Series<String, Number>> dataChartSecond(int sponsorId){
        ObservableList<XYChart.Series<String, Number>> answer = FXCollections.observableArrayList();
        try {
            String requete = "SELECT  CASE month(S.date_debut) "
                        + "                    when \"01\" then \"Janvier\" "
                        + "                    when \"02\" then \"Fevrier\" "
                        + "                    when \"03\" then \"Mars\" "
                        + "                    when \"04\" then \"Avril\" "
                        + "                    when \"05\" then \"Mai\" "
                        + "                    when \"06\" then \"Juin\" "
                        + "                    when \"07\" then \"Juillet\" "
                        + "                    when \"08\" then \"Aout\" "
                        + "                    when \"09\" then \"Septembre\" "
                        + "                    when \"10\" then \"Octobre\" "
                        + "                    when \"11\" then \"Novembre\" "
                        + "                    when \"12\" then \"Decembre\" "
                        + "                    END ,  "
                        + "                    count(S.id) From Sponsoring S where S.sponsor_id = ? GROUP BY month(S.date_debut)" ;
                
            pst = (PreparedStatement) connection.prepareStatement(requete);
            pst.setInt(1, sponsorId);
            rs = pst.executeQuery();
            while(rs.next()){
                System.out.println("nbr: " + rs.getInt(2) + " Month: " +rs.getString(1));
               // var serie = new XYChart.Series<String, Number>();
               XYChart.Series<String, Number> serie = new XYChart.Series<String, Number>();
                
                serie.getData().add(new XYChart.Data(rs.getString(1), rs.getInt(2)));
                answer.add(serie);
            }
            return answer; 
            } catch (SQLException ex) {
                Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);
            }
        return null;
    }
}
        

        
        
          
        
          
          
         
    
    
    

  

   
