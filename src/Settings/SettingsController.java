/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Settings;

import ChangePassword.ChangePasswordController;
import DatabaseHelper.DBLibraryDAO;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
 * @author shriya
 */
public class SettingsController implements Initializable {

    @FXML
    private JFXTextField fineAmount;
    @FXML
    private JFXTextField maxDaysField;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            fineAmount.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                if (!newValue.matches("\\d*")) {
                    fineAmount.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
            
            maxDaysField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                if (!newValue.matches("\\d*")) {
                    maxDaysField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
            
            fineAmount.setText(String.valueOf(DBLibraryDAO.getFineAmount()));
            maxDaysField.setText(String.valueOf(DBLibraryDAO.getMaxDay()));
            
        } catch (Exception ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    @FXML
    private void backAction(ActionEvent event) {
        try {
            Stage stagetemp = (Stage) fineAmount.getScene().getWindow();
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
    private void saveFine(ActionEvent event) {
        DBLibraryDAO.insertFineAmount(Integer.parseInt(fineAmount.getText().trim()));
        DialogBox.DialogBox.showDialog(DialogBox.DialogBox.amount_saved_successfully);
        
    }

    @FXML
    private void saveDays(ActionEvent event) {
        DBLibraryDAO.insertMaxDays(Integer.parseInt(maxDaysField.getText().trim()));
          DialogBox.DialogBox.showDialog(DialogBox.DialogBox.days_saved_successfully);
    }
    
}
