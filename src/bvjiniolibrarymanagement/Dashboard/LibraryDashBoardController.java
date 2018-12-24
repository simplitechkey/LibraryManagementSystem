/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bvjiniolibrarymanagement.Dashboard;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Omskamate
 */

public class LibraryDashBoardController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
       @FXML
    private AnchorPane labelTotalNumberOfBooks;

    @FXML
    private Label labelTotalNumberOfBookslabelTotalNumberOfBooksIssued;

    @FXML
    private AnchorPane labelTotalNumberOfBookslabelTotalNumberOfBooksRemaining;

    @FXML
    private AnchorPane labelTotalNumberOfBookslabelTotalNumberOfUsers;
    
    @FXML
    private JFXButton bookReturnBtn;

    @FXML
    private JFXButton deleteBookButton;

    @FXML
    private JFXButton newBookButton;

    @FXML
    private JFXButton bookIssueButton;

    @FXML
    void deleteBookAction(ActionEvent event) {

    }

    @FXML
    void issueBookAction(ActionEvent event) {

    }

    @FXML
    void newBookAction(ActionEvent event) {

    }

    @FXML
    void returnBookAction(ActionEvent event) {

    }
    @FXML
    void totalbookscreenpress(MouseEvent event) {
           try {
               Stage stage;
               stage=new Stage();
               AnchorPane root = FXMLLoader.load(getClass().getResource("/fromDashboard/TotalNumberofBooks.fxml"));
               Scene scene = new Scene(root,1200,600);
        
               stage.setResizable(false);
               stage.setTitle("Add New Book");
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(LibraryDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
     
}
