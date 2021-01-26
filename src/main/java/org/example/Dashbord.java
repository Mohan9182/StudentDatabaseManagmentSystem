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
import java.sql.ResultSet;

public class Dashbord extends Login implements Initializable {

    @FXML
    private TextField regno,name,marks1,marks2,address;
    @FXML
    private JFXComboBox<String> gender,branch,dept;
    @FXML
    private Label status,updateStatus;

    String url = Login.getUrl();
    String user = Login.getUsername();
    String password = Login.getPassword();



    public void GetUpdateData(ActionEvent actionEvent) {
        String query = "SELECT * FROM student where regno="+regno.getText().toString();

        try(Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    name.setText(rs.getString(2));
                    gender.setValue(rs.getString(3));
                    branch.setValue(rs.getString(4));
                    dept.setValue(rs.getString(5));
                    address.setText(rs.getString(6));
                    marks1.setText(String.valueOf(rs.getFloat(7)));
                    marks2.setText(String.valueOf(rs.getFloat(8)));
                }
        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(Dashbord.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public void updateData(ActionEvent actionEvent) {
        String query = "Update student set name='"+name.getText().toString()+"',gender='"+gender.getSelectionModel().getSelectedItem()+"',branch='"+branch.getSelectionModel().getSelectedItem()+"',dept='"+dept.getSelectionModel().getSelectedItem()+"',address='"+address.getText().toString()+"',10th_marks="+marks1.getText().toString()+",12th_marks="+marks2.getText().toString()+" where regno="+regno.getText().toString();
        try(Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query)) {
            pst.executeUpdate();
            updateStatus.setText("Done");
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Dashbord.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @FXML
    private void goToInsertPage(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene((Parent) loadFXML("insert"));
        App.getDashboard().setScene(scene);
    }

    public static Object loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
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
}
