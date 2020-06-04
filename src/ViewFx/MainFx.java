/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewFx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
//import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainFx extends Application {
    @Override
   public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("AccueilFXML.fxml"));
    Scene scene = new Scene(root);        
    primaryStage.centerOnScreen();
    //primaryStage.getIcons().add(new Image("/bienvenue.gif"));
    primaryStage.setScene(scene);
    primaryStage.setTitle("Bienvenue!!");
    primaryStage.show();
    }
    public static void main (String[]args){
       launch(args);  
    }
}
