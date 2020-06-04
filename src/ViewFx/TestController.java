/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewFx;

import Entities.Candidat;
import Entities.Sponsoring;
import Entities.SponsoringModel;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Khoubaib
 */
public class TestController implements Initializable {

    @FXML
    public Label candidat; 
    SponsoringModel sponsoringModel;
    @FXML
    private Label description;
    @FXML
    private Label type;
    @FXML
    private Label date_debut;
    @FXML
    private Label date_fin;
    @FXML
    private Label image;
     public Sponsoring sponsoring ; 
    @FXML
    private Button btnBack;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnConsulter;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnLineChart;
    AnchorPane leb;
   @FXML
    private ListView<SponsoringModel> listView;
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
        
        listView.setCellFactory(lv -> new ListCell<SponsoringModel>() {
        @Override
         public void updateItem(SponsoringModel item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
            } else {
                String text = item.getNomCandidat() + "      "  + "      "  + "      "  +  
                              item.getDescription() + "      "  + "      "  + "      "  +  
                              item.getType() + "      "  + "      "  + "      "  +  
                              item.getDate_debut() + "      "  + "      "  +      
                              item.getDate_fin();
                setText(text);
            }
        }
        });
        
    }    
      @FXML
    private void btnExitAction(ActionEvent event) {
        Stage primaryStage = (Stage) btnExit.getScene().getWindow();
        primaryStage.close();
    }
    @FXML
    private void btnBackAction(ActionEvent event) {
         ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AccueilFXML.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
        primaryStage.setTitle("Welcom !!");
        primaryStage.show();
    }
    @FXML
     public void AfficheSponsoring() {
       // ListView<SponsoringModel> listView = new ListView<SponsoringModel>();
        SponsoringService dc = new SponsoringService();
        ArrayList<SponsoringModel> listSponsoringModel = dc.afficherSponsoring();
        
        ObservableList<SponsoringModel> list = FXCollections.<SponsoringModel>observableArrayList(listSponsoringModel); 
       // ListView<SponsoringModel> listView = new ListView<SponsoringModel>(list);
       
        listView.setItems(list);
        
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SponsoringModel>() {
            @Override
            public void changed(ObservableValue<? extends SponsoringModel> observable, SponsoringModel oldValue, SponsoringModel newValue) {
                sponsoringModel = listView.getSelectionModel().getSelectedItem();
            }} 
        );
       
   }
     public void loadSponsoring(int idSpons) {//methode permet retouner un objet a une id qui est en paramétre
        System.out.println(idSpons);
        SponsoringService sponsoringService = new SponsoringService();
        sponsoring = sponsoringService.getSponsoring(idSpons);
       
    
    }


    @FXML
    private void btnConsulterAction(ActionEvent event) {
           if(sponsoringModel != null){
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
            pac.loadSponsoring(sponsoringModel.getIdSpons());
            primaryStage.show();
        
    }
    }

    @FXML
    private void btnAjouterAction(ActionEvent event) {
          ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AjoutSponsoringFXL.fxml"));
       
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
        primaryStage.setTitle("Ajouter Publicité");
        AjoutSponsoringFXLController pac =loader.getController();
        primaryStage.show();
    }

    @FXML
    private void btnModifierAction(ActionEvent event) {
         if(sponsoringModel != null){
            ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ModifierSponsoring.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Parent p = loader.getRoot();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(p));
            primaryStage.setTitle("Modifier Publicité");
            ModifierSponsoringController pac =loader.getController();
            pac.loadSponsoring(sponsoringModel.getIdSpons());
            primaryStage.show();
        }
    }

    @FXML
    private void btnSupprimerAction(ActionEvent event) {
         if(sponsoringModel != null){
            SponsoringService sponsoringService = new SponsoringService();
            sponsoringService.removeSponsoring(sponsoringModel.getIdSpons());
            AfficheSponsoring();
       }
    }

    @FXML
    private void btnLineChartAction(ActionEvent event) {
          ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("LineChart.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
        primaryStage.setTitle("Bar Chart");
        LineChartController pac =loader.getController();
        primaryStage.show();
    }
}
        