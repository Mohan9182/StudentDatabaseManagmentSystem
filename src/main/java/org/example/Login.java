package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login extends App{

    private static Login scene;

    @FXML
    private TextField user,pass,database;

    @FXML
    private Label status;

    public static String url,username,password;

    public static String getUrl(){
        return url;
    }

    public static String getUsername(){
        return username;
    }

    public static String getPassword(){
        return password;
    }

    public void Login(ActionEvent actionEvent) throws IOException {

        url = "jdbc:mysql://localhost:3306/"+database.getText().toString()+"?useSSL=false";
        username = user.getText().toString();
        password = pass.getText().toString();

        try (Connection con = DriverManager.getConnection(url, username, password)){
            Scene scene = new Scene((Parent) loadFXML("dashbord"));
            App.getDashboard().setScene(scene);

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(Login.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            status.setText("Something went Wrong!Please check the Details");

        }
    }

    public static Object loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


}
