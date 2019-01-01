/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fromDashboard;

import AddBookEntry.AddBookController;
import BeansPackage.ReturnedBookObject;
import DatabaseHelper.DBLibraryDAO;
import DialogBox.DialogBox;
import IssueBook.IssueBookController;
import bvjiniolibrarymanagement.FXMLDocumentController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author omkarkamate
 */
public class ReturnedBooksTableController implements Initializable {
  ObservableList<ReturnedBookObject> data=FXCollections.observableArrayList();
  
   @FXML
    private Text userText;
    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<ReturnedBookObject> bookTableView;

    @FXML
    private JFXButton allrec;

    @FXML
    private JFXTextField searchfield;

    @FXML
    void backAction(ActionEvent event) {
       try {
          
          Stage stagetemp = (Stage) searchfield.getScene().getWindow();
    // do what you have to do
            stagetemp.close();
          Stage stage=new Stage();
         AnchorPane root = FXMLLoader.load(getClass().getResource("/Dashboard/Dashboard.fxml"));
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
    
    @FXML
    void logOutAction(ActionEvent event) {
       try {
           
           Stage closeStage = (Stage) userText.getScene().getWindow();
           closeStage.close();
           Parent root = FXMLLoader.load(getClass().getResource("/bvjiniolibrarymanagement/FXMLDocument.fxml"));
           Stage stage=new Stage();
           Scene scene = new Scene(root);
           stage.initStyle(StageStyle.UNDECORATED);
           stage.setScene(scene);
           stage.show();
       } catch (IOException ex) {
           Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }

    }

    @FXML
    void showallrecs(ActionEvent event) {
        try {
            bookTableView.setItems(DBLibraryDAO.getAllReturnedBooksRecords());
            bookTableView.refresh();
        } catch (Exception ex) {
            Logger.getLogger(IssueBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void searchAction(ActionEvent event) {
        try {
             if((searchfield.getText().trim().isEmpty() || (searchfield.getText() == null))) {
            DialogBox.showDialog(DialogBox.dialog_text_null);
            bookTableView.setItems(DBLibraryDAO.getAllReturnedBooksRecords());
              bookTableView.refresh();
             }else{
            data=DBLibraryDAO.searchReturnedBookById(searchfield.getText().trim());
            bookTableView.setItems(data);
            bookTableView.refresh();
             } 
           
        } catch (Exception ex) {
            Logger.getLogger(IssueBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
              TableColumn<ReturnedBookObject,Integer> bookId=new TableColumn("bookID");
              bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
              
              TableColumn<ReturnedBookObject,String> bookSubject=new TableColumn("bookSubject");
              bookSubject.setCellValueFactory(new PropertyValueFactory<>("bookSubject"));
              
              TableColumn<ReturnedBookObject,String>bookBranch=new TableColumn("bookBranch");
              bookBranch.setCellValueFactory(new PropertyValueFactory<>("bookBranch"));
              
              TableColumn<ReturnedBookObject,String>bookTitle=new TableColumn("bookTitle");
              bookTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
              
              TableColumn<ReturnedBookObject,Integer>bookAccountNumber=new TableColumn("accountNumber");
              bookAccountNumber.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
              
              TableColumn<ReturnedBookObject,String>bookAuthor=new TableColumn("bookAuthor");
              bookAuthor.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));
              
              TableColumn<ReturnedBookObject,String>bookPublication=new TableColumn("bookPublication");
              bookPublication.setCellValueFactory(new PropertyValueFactory<>("bookPublication"));
              
              TableColumn<ReturnedBookObject,String>bookPrice=new TableColumn("bookPrice");
              bookPrice.setCellValueFactory(new PropertyValueFactory<>("bookPrice"));
              
              TableColumn<ReturnedBookObject,String>bookYear=new TableColumn("bookYear");
              bookYear.setCellValueFactory(new PropertyValueFactory<>("bookYear"));
              
              TableColumn<ReturnedBookObject,String>bookEditionYear=new TableColumn("bookEditionYear");
              bookEditionYear.setCellValueFactory(new PropertyValueFactory<>("bookEditionYear"));
              
              TableColumn<ReturnedBookObject,String>bookSupplier=new TableColumn("bookSupplier");
              bookSupplier.setCellValueFactory(new PropertyValueFactory<>("bookSupplier"));
              
              TableColumn<ReturnedBookObject,String>billNumber=new TableColumn("billNumber");
              billNumber.setCellValueFactory(new PropertyValueFactory<>("billNumber"));
              
              TableColumn<ReturnedBookObject,String>bookbillDate=new TableColumn("billDate");
              bookbillDate.setCellValueFactory(new PropertyValueFactory<>("billDate"));
              
              
               userText.setText(DBLibraryDAO.userName);
              bookTableView.setItems(DBLibraryDAO.getAllReturnedBooksRecords());
              bookTableView.getColumns().addAll(bookId,bookSubject,bookBranch,bookTitle,bookAccountNumber,bookAuthor,bookPublication,bookPrice,bookYear,bookEditionYear,bookSupplier,billNumber,bookbillDate);
          } catch (Exception ex) {
              Logger.getLogger(IssueBookController.class.getName()).log(Level.SEVERE, null, ex);
          }
           
    }    
}
