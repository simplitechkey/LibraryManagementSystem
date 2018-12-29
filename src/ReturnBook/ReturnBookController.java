/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReturnBook;

import AddBookEntry.AddBookController;
import BeansPackage.IssuedBookObject;
import BeansPackage.IssuedBookObject;
import DatabaseHelper.DBLibraryDAO;
import DialogBox.DialogBox;
import IssueBook.IssueBookController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.IntegerValidator;
import com.jfoenix.validation.NumberValidator;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author omkarkamate
 */
public class ReturnBookController implements Initializable {

   ObservableList<IssuedBookObject> data=FXCollections.observableArrayList();
      @FXML
    private TableView<IssuedBookObject> bookTableView;

    @FXML
    private JFXButton allrec;

    @FXML
    private JFXTextField searchfield;
    
     @FXML
    private JFXButton returnBtn;
    

    
        @FXML
    void returnBookAction(ActionEvent event) {
       try {
        
            if((searchfield.getText().trim().isEmpty() || (searchfield.getText() == null))) {
          DialogBox.showDialog(DialogBox.dialog_text_null);
           returnBtn.setVisible(false);
            bookTableView.setItems(DBLibraryDAO.getAllIssuedBooksRecords());
        }else{
            DBLibraryDAO.returnBookfromIssuedBooks(Integer.parseInt(searchfield.getText()));
            DialogBox.showDialog(DialogBox.dialog_return_successful);
             bookTableView.setItems(DBLibraryDAO.getAllIssuedBooksRecords());
             bookTableView.refresh();
            }
        } catch (Exception ex) {
            Logger.getLogger(IssueBookController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    @FXML
    void searchAction(ActionEvent event) {
        try {
             data=DBLibraryDAO.searchIssuedBookBookById(Integer.parseInt(searchfield.getText()));
        if((searchfield.getText().trim().isEmpty() || (searchfield.getText() == null))) {
          DialogBox.showDialog(DialogBox.dialog_text_null);
           returnBtn.setVisible(false);
            bookTableView.setItems(DBLibraryDAO.getAllIssuedBooksRecords());
        }else{
            if(!searchfield.getText().trim().isEmpty() && data.isEmpty()){
                DialogBox.showDialog(DialogBox.no_values_found);
                  returnBtn.setVisible(false);
            }else{
            //data=DBLibraryDAO.searchIssuedBookBookById(Integer.parseInt(searchfield.getText()));
            returnBtn.setVisible(true);
            bookTableView.setItems(data);
        }
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
            Logger.getLogger(IssueBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
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
              
              
              
              bookTableView.setItems(DBLibraryDAO.getAllIssuedBooksRecords());
              bookTableView.getColumns().addAll(bookId,bookSubject,bookBranch,bookTitle,bookAccountNumber,bookAuthor,bookPublication,bookPrice,bookYear,bookEditionYear,bookSupplier,billNumber,bookbillDate);
              returnBtn.setVisible(false);
           
          
          } catch (Exception ex) {
              Logger.getLogger(IssueBookController.class.getName()).log(Level.SEVERE, null, ex);
          }
    
} 

}
