/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddBookEntry;

import DatabaseHelper.DBLibraryDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Omskamate
 */
public class AddBookController implements Initializable {

   @FXML
    private JFXTextField bookIdField;

    @FXML
    private JFXTextField bookSubjectField;

    @FXML
    private JFXTextField bookBranchField;

    @FXML
    private JFXTextField bookTitleField;

    @FXML
    private JFXTextField bookAccNoField;

    @FXML
    private JFXTextField bookAuthorField;

    @FXML
    private JFXTextField bookPublicationField;

    @FXML
    private JFXTextField bookPriceField;

    @FXML
    private JFXTextField bookYearField;

    @FXML
    private JFXTextField bookEditionField;

    @FXML
    private JFXTextField bookSupplierField;

    @FXML
    private JFXTextField bookNoField;

    @FXML
    private JFXDatePicker bookbillDateField;

    @FXML
    void addRecord(ActionEvent event) {
       
            DBLibraryDAO.insertBook(Integer.parseInt(bookIdField.getText()), bookSubjectField.getText(), bookBranchField.getText(), bookTitleField.getText(), Integer.parseInt(bookAccNoField.getText()), bookAuthorField.getText(), bookPublicationField.getText(), bookPriceField.getText(), bookYearField.getText(), bookEditionField.getText(), bookSupplierField.getText(), bookNoField.getText(), bookbillDateField.getValue().toString());
        
    }

    @FXML
    void backAction(ActionEvent event) {
       try {
          
          Stage stagetemp = (Stage) bookNoField.getScene().getWindow();
           stagetemp.close();
           
           Stage stage=new Stage();
         AnchorPane root = FXMLLoader.load(getClass().getResource("/bvjiniolibrarymanagement/Dashboard/Dashboard.fxml"));
            Scene scene = new Scene(root,1200,600);
            // stage.initStyle(StageStyle.UTILITY);
             stage.setResizable(false);
             stage.setTitle("Add New Book");
            stage.setScene(scene);
            stage.show();
          
       } catch (Exception ex) {
           Logger.getLogger(AddBookController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
