package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    private static Stage Dashbord;

    @Override
    public void start(Stage login) throws IOException {
        scene = new Scene((Parent) loadFXML("login"));
        Dashbord = login;
        login.setScene(scene);
        login.show();
    }

    public static Stage getDashboard(){
        return Dashbord;
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot((Parent) loadFXML(fxml));
    }

    private static Object loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) { launch(args); }

}