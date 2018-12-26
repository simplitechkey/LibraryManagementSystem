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
import IssueBook.IssueBookController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    void searchAction(ActionEvent event) {
        try {
            data=DBLibraryDAO.searchIssuedBookBookById(Integer.parseInt(searchfield.getText()));
            bookTableView.setItems(data);
        } catch (Exception ex) {
            Logger.getLogger(IssueBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void showallrecs(ActionEvent event) {
        try {
            bookTableView.setItems(DBLibraryDAO.getAllIssuedBooksRecords());
            bookTableView.refresh();
        } catch (Exception ex) {
            Logger.getLogger(IssueBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void backAction(ActionEvent event) {
       try {
          
          Stage stagetemp = (Stage) searchfield.getScene().getWindow();
    // do what you have to do
            stagetemp.close();
          
          
       } catch (Exception ex) {
           Logger.getLogger(AddBookController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
   
    @Override
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
              // loadDatabaseData();
              bookTableView.getColumns().addAll(bookId,bookSubject,bookBranch,bookTitle,bookAccountNumber,bookAuthor,bookPublication,bookPrice,bookYear,bookEditionYear,bookSupplier,billNumber,bookbillDate);
          } catch (Exception ex) {
              Logger.getLogger(IssueBookController.class.getName()).log(Level.SEVERE, null, ex);
          }
    
} 
    
}
