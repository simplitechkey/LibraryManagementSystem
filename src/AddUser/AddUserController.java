/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddUser;

import DatabaseHelper.DBLibraryDAO;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Omskamate
 */
public class AddUserController implements Initializable {
    
     String regex = "^(?=.*[a-z])(?=.*[A-Z]).{5,}$";
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

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
    
}
