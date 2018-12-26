/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bvjiniolibrarymanagement.Dashboard;

import DatabaseHelper.DBLibraryDAO;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
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
    private  TextField labelTotalEntries;
          
    @FXML
    private JFXTextField booksRemainingLabel;
    
    @FXML
    private JFXTextField booksRemaininglabel;
    
    @FXML
    private Label labelTotalNumberOfBookslabelTotalNumberOfBooksIssued;

    @FXML
    private AnchorPane labelTotalNumberOfBookslabelTotalNumberOfBooksRemaining;
        @FXML
    private JFXTextField labelnumberofbooksIssued;

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
              try {
                  AnchorPane root = FXMLLoader.load(getClass().getResource("/IssueBook/IssueBook.fxml"));
                  Stage stage=new Stage();
                  Scene scene = new Scene(root,1200,600);
                  //stage.initStyle(StageStyle.UNDECORATED);
                  stage.setScene(scene);
                  stage.show();
              } catch (IOException ex) {
                  Logger.getLogger(LibraryDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
              }
    }

    @FXML
    void newBookAction(ActionEvent event) {
           try {
               AnchorPane root = FXMLLoader.load(getClass().getResource("/AddBookEntry/AddBookEntry.fxml"));
               Stage stage=new Stage();
               Scene scene = new Scene(root,1200,600);
               //stage.initStyle(StageStyle.UNDECORATED);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(LibraryDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
           }
    }

    @FXML
    void returnBookAction(ActionEvent event) {
              try {
                  AnchorPane root = FXMLLoader.load(getClass().getResource("/ReturnBook/ReturnBook.fxml"));
                  Stage stage=new Stage();
                  Scene scene = new Scene(root,1200,600);
                  //stage.initStyle(StageStyle.UNDECORATED);
                  stage.setScene(scene);
                  stage.show();
              } catch (IOException ex) {
                  Logger.getLogger(LibraryDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
              }
    }
    @FXML
    void totalbookscreenpress(MouseEvent event) {
           try {
               Stage stage;
               stage=new Stage();
               Parent root = FXMLLoader.load(getClass().getResource("/fromDashboard/TotalNumberofBooks.fxml"));
               Scene scene = new Scene(root,1200,600);
        
               stage.setResizable(false);
               stage.setTitle("Total Books");
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(LibraryDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
     @FXML
    void gotoIssuebookTable(MouseEvent event) {
        try {
               Stage stage;
               stage=new Stage();
               Parent root = FXMLLoader.load(getClass().getResource("/fromDashboard/IssuedBooksTable.fxml"));
               Scene scene = new Scene(root,1200,600);
        
               stage.setResizable(false);
               stage.setTitle("Issue New Book");
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(LibraryDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    @FXML
    void gotoRemainingBookTable(MouseEvent event) {
         try {
               Stage stage;
               stage=new Stage();
               Parent root = FXMLLoader.load(getClass().getResource("/fromDashboard/ReturnedBooksTable.fxml"));
               Scene scene = new Scene(root,1200,600);
        
               stage.setResizable(false);
               stage.setTitle("Return Book");
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(LibraryDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         labelTotalEntries.setStyle("-fx-text-fill: white;");
              try {
                  String totalBooks=String.valueOf(DBLibraryDAO.getAllRecords().size());
                  String totalIssuedBooks=String.valueOf(String.valueOf(DBLibraryDAO.getAllIssuedBooksRecords().size()));
                  String remainingBooks=String.valueOf((DBLibraryDAO.getAllRecords().size()-DBLibraryDAO.getAllIssuedBooksRecords().size()));
                 
                  labelTotalEntries.setText(totalBooks);
                  
                  labelnumberofbooksIssued.setText(totalIssuedBooks);
                  booksRemainingLabel.setText(remainingBooks);
                 
                       
                  
              } catch (Exception ex) {
                  Logger.getLogger(LibraryDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
              }
         
    }    
     
}
