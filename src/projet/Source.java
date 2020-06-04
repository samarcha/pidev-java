/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Khoubaib
 */
public class Source {
    private String url;
    private String login;
    private String password;
    private Connection connection;
    private Properties properties;
    private static Source instance;

    private Source() {
        //on utilise try catch pour capturer l'exception
        try {
            properties = new Properties();
            properties.load(new FileInputStream(new File("configuration.properties")));
            // la classe FileInputStream permet d'ouvrir un fichier en lecture et d'en lire le contenu
           //url de la base de donnée
            url = properties.getProperty("url");//récupèrer la valeur de la propriété 
            login = properties.getProperty("login");
            password = properties.getProperty("password");
          connection = DriverManager.getConnection(url, login, password);
          //get connection est une methode qui utilise la classe DriverManager pour etablir la connection avec la BD
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Source getInstance() {
  //la methode get instance permet de verifier si l'instance est null, sinon on va appeler au constructeur pour creer l'instance      
        if (instance == null) {
            instance = new Source();
        }
        return instance;
    }
    
}

