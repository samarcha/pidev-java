/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewFx;

import Entities.Candidat;
import Entities.Sponsoring;
import Entities.SponsoringReponse;
import Service.SponsoringService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Khoubaib
 */
public class AfficherSponsoringController implements Initializable {
   
    
    public Candidat candidat;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnModifier;
    @FXML
    private Label candidatsColumn;
    @FXML
    private Label descriptionColumn;
    @FXML
    private Label typeColumn;
    @FXML
    private Label imageColumn;
    @FXML
    private ListView<SponsoringReponse> table ; 
    @FXML
    private Label leb;
    @FXML
    private Button btnSupprimer;
    public Sponsoring newSponsoring ;
    public Sponsoring sponsoring ; 
   
    SponsoringReponse sponsoringReponse;
    @FXML
    private Button btnConsulter;
    @FXML
    private Button btnLineChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           DropShadow shadow = new DropShadow();

        btnExit.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                shadow.setColor(Color.RED);
                btnExit.setEffect(shadow);
            }
        });
        btnExit.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                btnExit.setEffect(null);
            }
        });
    }
    @FXML
    private void btnExitAction(ActionEvent event) {
        Stage primaryStage = (Stage) btnExit.getScene().getWindow();
        primaryStage.close();
    } 
    
    
      @FXML
    private void btnBackAction(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AccueilFXML.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
        primaryStage.setTitle("Welcom !!");
        
        primaryStage.show();
    }
   public void AfficheSponsoring() {
        SponsoringService dc = new SponsoringService();
       // ArrayList<SponsoringReponse> listSponsoringReponse = dc.afficherSponsoring();
      //  ObservableList<SponsoringReponse> list = FXCollections.<SponsoringReponse>observableList(listSponsoringReponse);
        //descriptionColumn.setCellValueFactory(new PropertyValueFactory("description"));
        ///typeColumn.setCellValueFactory(new PropertyValueFactory("type"));
        //candidatsColumn.setCellValueFactory(new PropertyValueFactory("nomCandidat"));
        //imageColumn.setCellValueFactory(new PropertyValueFactory("image"));
    //  table.setItems(list);
        
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SponsoringReponse>() {
            
            @Override
            public void changed(ObservableValue<? extends SponsoringReponse> observable, SponsoringReponse oldValue, SponsoringReponse newValue) {
                sponsoringReponse = table.getSelectionModel().getSelectedItem();
            }} 
        );
   }
   
   
  @FXML
    public void btnAjouterAction(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AjoutSponsoringFXL.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
        primaryStage.setTitle("Ajouter Publicité");
        AjoutSponsoringFXLController pac =loader.getController();
        primaryStage.show();
    }
    
  
    
   
   
   public void loadSponsoring(int idSponsoring) {
         System.out.println(idSponsoring);
        SponsoringService sponsoringService = new SponsoringService();
        sponsoring = sponsoringService.getSponsoring(idSponsoring);
       
   }   
     @FXML
   private void btnSupprimerAction(ActionEvent event){
       if(sponsoringReponse != null){
            SponsoringService sponsoringService = new SponsoringService();
            sponsoringService.removeSponsoring(sponsoringReponse.getIdSponsoring());
            AfficheSponsoring();
       }
   }
   
    @FXML
    private void btnModifierAction(ActionEvent event) {
        if(sponsoringReponse != null){
            ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ModifierSponsoring.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(AfficherSponsoringController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Parent p = loader.getRoot();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(p));
            primaryStage.setTitle("Modifier Publicité");
            ModifierSponsoringController pac =loader.getController();
           // pac.loadSponsoring(sponsoringReponse.getIdSponsoring());
            primaryStage.show();
        }
    }

    @FXML
    private void btnConsulterAction(ActionEvent event) {
        if(sponsoringReponse != null){
            ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Consulter.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(AfficherSponsoringController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Parent p = loader.getRoot();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(p));
            primaryStage.setTitle("Consulter Publicité");
            ConsulterController pac =loader.getController();
            pac.loadSponsoring(sponsoringReponse.getIdSponsoring());
            primaryStage.show();
        
    }
    }

    
    }

    
    
  
    
    
   
    
 

