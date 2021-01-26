package org.example;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Insert extends Login implements Initializable {

    @FXML
    private JFXComboBox<String> gender,branch,dept;
    @FXML
    private TextField regno,name,marks1,marks2,address;
    @FXML
    private Label status;

    String url = Login.getUrl();
    String user = Login.getUsername();
    String password = Login.getPassword();

    public void insertData(ActionEvent actionEvent) {
        String table = "CREATE TABLE IF NOT EXISTS student(regno int primary key not null,name varchar(50),gender varchar(10),branch varchar(10),dept varchar(10),address varchar(150),10th_marks float,12th_marks float)";
        String sql = "Insert into student values("+regno.getText().toString()+",'"+name.getText().toString()+"','"+gender.getSelectionModel().getSelectedItem()+"','"+branch.getSelectionModel().getSelectedItem()+"','"+dept.getSelectionModel().getSelectedItem()+"','"+address.getText().toString()+"',"+marks1.getText().toString()+","+marks2.getText().toString()+")";
        try(Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(table)) {
            pst.executeUpdate();
        }
        catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Dashbord.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(sql)){
            pst.executeUpdate();
            status.setText("Done");
            regno.setText("");
            name.setText("");
            marks1.setText("");
            marks2.setText("");
            address.setText("");
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Dashbord.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gender.getItems().addAll("Male");
        gender.getItems().addAll("Female");
        branch.getItems().addAll("BE");
        branch.getItems().addAll("BTech");
        branch.getItems().addAll("BCom");
        branch.getItems().addAll("BSe");
        dept.getItems().addAll("CSE");
        dept.getItems().addAll("ECE");
        dept.getItems().addAll("EEE");
        dept.getItems().addAll("IT");
        dept.getItems().addAll("MEDICAL");
        dept.getItems().addAll("CIVIL");
    }

    public void gotodashbord(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene((Parent) loadFXML("dashbord"));
        App.getDashboard().setScene(scene);
    }

    public static Object loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
