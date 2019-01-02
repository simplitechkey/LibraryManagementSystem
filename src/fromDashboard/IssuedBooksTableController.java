/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fromDashboard;

import AddBookEntry.AddBookController;
import BeansPackage.IssuedBookObject;
import BeansPackage.IssuedBookObject;
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
public class IssuedBooksTableController implements Initializable {

    /**
     * Initializes the controller class.
     */
     ObservableList<IssuedBookObject> data=FXCollections.observableArrayList();
    @FXML
    private TableView<IssuedBookObject> bookTableView;
    
     @FXML
    private Text userText;

    @FXML
    private JFXButton allrec;

    @FXML
    private JFXTextField searchfield;

    @FXML
    void backAction(ActionEvent event) {
       try {
          
          Stage stagetemp = (Stage) searchfield.getScene().getWindow();
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
    void searchAction(ActionEvent event) {
 try {
            if((searchfield.getText().trim().isEmpty() || (searchfield.getText() == null))) {
            DialogBox.showDialog(DialogBox.dialog_text_null);
            bookTableView.setItems(DBLibraryDAO.getAllIssuedBooksRecords());
              bookTableView.refresh();
             }else{
            data=DBLibraryDAO.searchIssuedBookBookById(searchfield.getText());
            bookTableView.setItems(data);
            bookTableView.refresh();
             } 
        } catch (Exception ex) {
            Logger.getLogger(IssueBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void showallrecs(ActionEvent event) {
        try {
            bookTableView.setItems(DBLibraryDAO.getAllIssuedBooksRecords());
            bookTableView.refresh();
            searchfield.setText("");
        } catch (Exception ex) {
            Logger.getLogger(IssuedBooksTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
     @SuppressWarnings("unchecked")
    public void initialize(URL url, ResourceBundle rb) {
         try {
              TableColumn<IssuedBookObject,Integer> bookId=new TableColumn("bookID");
              bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
              
              TableColumn<IssuedBookObject,String> bookSubject=new TableColumn("bookSubject");
              bookSubject.setCellValueFactory(new PropertyValueFactory<>("bookSubject"));
              
              TableColumn<IssuedBookObject,String>bookBranch=new TableColumn("bookBranch");
              bookBranch.setCellValueFactory(new PropertyValueFactory<>("bookBranch"));
              
              TableColumn<IssuedBookObject,String>bookTitle=new TableColumn("bookTitle");
              bookTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
              
              TableColumn<IssuedBookObject,Integer>bookAccountNumber=new TableColumn("accountNumber");
              bookAccountNumber.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
              
              TableColumn<IssuedBookObject,String>bookAuthor=new TableColumn("bookAuthor");
              bookAuthor.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));
              
              TableColumn<IssuedBookObject,String>bookPublication=new TableColumn("bookPublication");
              bookPublication.setCellValueFactory(new PropertyValueFactory<>("bookPublication"));
              
              TableColumn<IssuedBookObject,String>bookPrice=new TableColumn("bookPrice");
              bookPrice.setCellValueFactory(new PropertyValueFactory<>("bookPrice"));
              
              TableColumn<IssuedBookObject,String>bookYear=new TableColumn("bookYear");
              bookYear.setCellValueFactory(new PropertyValueFactory<>("bookYear"));
              
              TableColumn<IssuedBookObject,String>bookEditionYear=new TableColumn("bookEditionYear");
              bookEditionYear.setCellValueFactory(new PropertyValueFactory<>("bookEditionYear"));
              
              TableColumn<IssuedBookObject,String>bookSupplier=new TableColumn("bookSupplier");
              bookSupplier.setCellValueFactory(new PropertyValueFactory<>("bookSupplier"));
              
              TableColumn<IssuedBookObject,String>billNumber=new TableColumn("billNumber");
              billNumber.setCellValueFactory(new PropertyValueFactory<>("billNumber"));
              
              TableColumn<IssuedBookObject,String>bookbillDate=new TableColumn("billDate");
              bookbillDate.setCellValueFactory(new PropertyValueFactory<>("billDate"));
              
              TableColumn<IssuedBookObject,String>issuedTo=new TableColumn("issuedTo");
              issuedTo.setCellValueFactory(new PropertyValueFactory<>("issuedTo"));
              
              TableColumn<IssuedBookObject,String>studentClass=new TableColumn("studentClass");
              studentClass.setCellValueFactory(new PropertyValueFactory<>("studentClass"));
              
              TableColumn<IssuedBookObject,String>issuedDate=new TableColumn("issuedDate");
              issuedDate.setCellValueFactory(new PropertyValueFactory<>("issuedDate"));
              
              
               userText.setText(DBLibraryDAO.userName);
              bookTableView.setItems(DBLibraryDAO.getAllIssuedBooksRecords());
              bookTableView.getColumns().addAll(bookId,bookSubject,bookBranch,bookTitle,bookAccountNumber,bookAuthor,bookPublication,bookPrice,bookYear,bookEditionYear,bookSupplier,billNumber,bookbillDate,issuedTo,studentClass,issuedDate);
           
          } catch (Exception ex) {
              Logger.getLogger(IssueBookController.class.getName()).log(Level.SEVERE, null, ex);
          }
           
    }    
    }    
    

