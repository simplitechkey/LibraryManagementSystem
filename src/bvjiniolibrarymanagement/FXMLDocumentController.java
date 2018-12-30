/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bvjiniolibrarymanagement;

import DatabaseHelper.DBLibraryDAO;
import DatabaseHelper.DBUtil;
import DialogBox.DialogBox;
import animatefx.animation.AnimateFXInterpolator;
import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 *
 * @author Omskamate
 */
public class FXMLDocumentController implements Initializable {
  
   RequiredFieldValidator rv=new RequiredFieldValidator();
   
   
      
     @FXML
    private Label label;

    @FXML
    private JFXTextField loginfield;

    @FXML
    private JFXPasswordField passwordfield;

    @FXML
    private JFXButton login;

  
   
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            String uname=loginfield.getText();
            String pass=passwordfield.getText();
             if(uname.equals("") || pass.equals("")){
                    DialogBox.showDialog(DialogBox.user_pass_blank);
                     loginfield.setText("");
                     passwordfield.setText("");
             }else{
            if(DBLibraryDAO.validateUser(uname, pass)){
                
                 DBLibraryDAO.userName=uname;
             Stage stage2 = (Stage) login.getScene().getWindow();
             stage2.close();
             
              AnchorPane root = FXMLLoader.load(getClass().getResource("/Dashboard/Dashboard.fxml"));
            Scene scene = new Scene(root,1200,600);
            // stage.initStyle(StageStyle.UTILITY);
            Stage stage=new Stage();
             stage.setResizable(false);
             stage.setTitle("Add New Book");
            stage.setScene(scene);
            stage.show();
           
            }else{
                               
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Login Failed");
                alert.setHeaderText("Wrong Username and Password");
                alert.setContentText("Please Enter Correct Username and Password");
                Optional<ButtonType> result = alert.showAndWait();
                 if (result.get() == ButtonType.OK){
                     loginfield.requestFocus();
                loginfield.setText("");
                passwordfield.setText("");
            }
                 }
            }
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
              loginfield.requestFocus();
            
           
            
            
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
 }    
    
}
