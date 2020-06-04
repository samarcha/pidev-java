 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewFx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Khoubaib
 */
public class AccueilFXMLController implements Initializable {
     @FXML
    private Button btnPublicite;
     @FXML
    private Button btnDemande;
     @FXML
    private Button btnCandidat;
     @FXML
    private ImageView imageview;
     @FXML
    private Button btnDeconnecter;

    @FXML
    private void handleButton(ActionEvent event) {
        Image image =new Image("Imges/image.jpg");
        imageview.setImage(image);
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {}
    
   /* @FXML
    private void btnPubliciteAction(ActionEvent event) throws IOException {
       
       ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AfficherSponsoring.fxml"));
            loader.load();
            Parent p = loader.getRoot();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(p));
            AfficherSponsoringController afficherSponsoringController = loader.getController();
            afficherSponsoringController.AfficheSponsoring();
            //stage.getIcons().add(new Image("pidev/gui/img/icone.png"));
            primaryStage.setTitle("Liste des publicités");
             primaryStage.show();
        
    }*/
      @FXML
    private void btnPubliciteAction(ActionEvent event) throws IOException {
       
       ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("test.fxml"));
            loader.load();
            Parent p = loader.getRoot();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(p));
            TestController afficherSponsoringController = loader.getController();
            afficherSponsoringController.AfficheSponsoring();
            primaryStage.setTitle("Liste des publicités");
            primaryStage.show();
        
    }
    @FXML
     private void btnDemandeAction(ActionEvent event) throws IOException {
       
         ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AfficherDemande.fxml"));
            loader.load();
            Parent p = loader.getRoot();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(p));
            AfficherDemandeController afficherDemandeController = loader.getController();
            afficherDemandeController.AfficheDemande();
            //stage.getIcons().add(new Image("pidev/gui/img/icone.png"));
            primaryStage.setTitle("Liste des Demandes");
             primaryStage.show();
    }
     
    @FXML
     private void btnCandidatAction(ActionEvent event) throws IOException {
       ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ListeCandidats.fxml"));
            loader.load();
            Parent p = loader.getRoot();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(p));
            ListeCandidatsController listeCadidatsController = loader.getController();
            listeCadidatsController.AfficheCandidat();
            //stage.getIcons().add(new Image("pidev/gui/img/icone.png"));
            primaryStage.setTitle("Liste des Candidats");
            primaryStage.show();
    }
     @FXML
    private void btnDeconnecterAction(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) btnDeconnecter.getScene().getWindow();
        primaryStage.close();
    }
}
