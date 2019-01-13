/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddUser;

import DatabaseHelper.DBLibraryDAO;
import ReturnBook.ReturnBookController;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Omskamate
 */
public class AddUserController implements Initializable {
    
     String regex = "^.{5,}$";
    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField currentPasswordField;

    @FXML
    private JFXPasswordField confirmPasswordField;

    @FXML
    void addUser(ActionEvent event) {
        String username=usernameField.getText().trim();
        String pass=currentPasswordField.getText().trim();
        String newPass=confirmPasswordField.getText().trim();
        
        if(username.equals("") || pass.equals("") || newPass.equals("")){
            DialogBox.DialogBox.showDialog(DialogBox.DialogBox.field_empty);
        }else{
          
            if(pass.equals(newPass)){
                 
                if(pass.matches(regex)){
                DBLibraryDAO.addNewUser(username, pass);
                }else{
                    DialogBox.DialogBox.showDialog(DialogBox.DialogBox.pattern_not_matched);
                }
            }else{
                DialogBox.DialogBox.showDialog(DialogBox.DialogBox.pass_not_matched);
            }
        }

    }

    @FXML
    void backAction(ActionEvent event) {
        
        try {

            Stage stagetemp = (Stage) usernameField.getScene().getWindow();
            stagetemp.close();
            Stage stage = new Stage();
            AnchorPane root = FXMLLoader.load(getClass().getResource("/Dashboard/Dashboard.fxml"));
            Scene scene = new Scene(root, 1200, 600);
            stage.setResizable(false);
            stage.setTitle("Add New Book");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(ReturnBookController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
    
}
