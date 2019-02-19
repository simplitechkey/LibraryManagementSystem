/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChangePassword;

import DatabaseHelper.DBLibraryDAO;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Omskamate
 */
public class ChangePasswordController implements Initializable {

    String regex2 = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{5,}$";
    String regex = "^(?=.*[a-z])(?=.*[A-Z]).{5,}$";

    String newpassword = "";

     
    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField currentPasswordField;

    @FXML
    private JFXTextField newPasswordField;

    @FXML
    private JFXPasswordField confirmPasswordField;

    @FXML
    void backAction(ActionEvent event) {
        try {
            Stage stagetemp = (Stage) usernameField.getScene().getWindow();
            stagetemp.close();

            Stage stage = new Stage();
            AnchorPane root = FXMLLoader.load(getClass().getResource("/Dashboard/Dashboard.fxml"));
            Scene scene = new Scene(root, 1200, 600);
            // stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.setTitle("Add New Book");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void changePasswordBtn(ActionEvent event) {
        String username = usernameField.getText();
        String currentpass = currentPasswordField.getText();
        String newpass = newPasswordField.getText();
        String confirmpass = confirmPasswordField.getText();
        if (DBLibraryDAO.validateUser(username, currentpass)) {
            if (newpass.matches(regex)) {
                if (newpass.equals(confirmpass)) {
                    DBLibraryDAO.changePassword(username, currentpass, newpass);
                     DialogBox.DialogBox.showDialog(DialogBox.DialogBox.change_pass_success);
                      try {
            Stage stagetemp = (Stage) usernameField.getScene().getWindow();
            stagetemp.close();

            Stage stage = new Stage();
            AnchorPane root = FXMLLoader.load(getClass().getResource("/Dashboard/Dashboard.fxml"));
            Scene scene = new Scene(root, 1200, 600);
            // stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.setTitle("Add New Book");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    usernameField.setText("");
                    currentPasswordField.setText("");
                    newPasswordField.setText("");
                    confirmPasswordField.setText("");
                } else {
                    DialogBox.DialogBox.showDialog(DialogBox.DialogBox.pass_not_matched);

                }
            } else {
                DialogBox.DialogBox.showDialog(DialogBox.DialogBox.pattern_not_matched);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Credentials Failed");
            alert.setHeaderText("Wrong Username and Password");
            alert.setContentText("Please Enter Correct Username and Password");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                currentPasswordField.requestFocus();
                currentPasswordField.setText("");
                newPasswordField.setText("");
                confirmPasswordField.setText("");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
    }

}
