/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fromDashboard;

import AddBookEntry.AddBookController;
import BeansPackage.DatabaseSample;
import DatabaseHelper.DBLibraryDAO;
import DatabaseHelper.DBUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class TotalNumberofBooksController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ObservableList<DatabaseSample> data=FXCollections.observableArrayList();
         
    @FXML
   TableView<DatabaseSample> bookTableView;
     
   
   

    @FXML
    private JFXTextField branchField;

    @FXML
    private JFXTextField subjectField;

    @FXML
    private JFXTextField idField;
    
    @FXML
    private JFXTextField searchfield;
    @FXML
    private JFXButton allrec;
    
    @FXML
    void searchAction(ActionEvent event) {
        try {
           
            data=DBLibraryDAO.searchBookById(Integer.parseInt(searchfield.getText()));
            bookTableView.setItems(data);
        } catch (Exception ex) {
            Logger.getLogger(TotalNumberofBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void showallrecs(ActionEvent event) {
        try {
            bookTableView.setItems(DBLibraryDAO.getAllRecords());
            bookTableView.refresh();
           
        } catch (Exception ex) {
            Logger.getLogger(TotalNumberofBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void backAction(ActionEvent event) {
       try {
          
          Stage stagetemp = (Stage) searchfield.getScene().getWindow();
    // do what you have to do
            stagetemp.close();
           Stage stage=new Stage();
          
       } catch (Exception ex) {
           Logger.getLogger(AddBookController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    @FXML
    void addAction(ActionEvent event) {
        try {
            //DBLibraryDAO.insertBook(Integer.parseInt(idField.getText()), subjectField.getText(), branchField.getText());
           bookTableView.setItems(DBLibraryDAO.getAllRecords());
           bookTableView.refresh();
        } catch (Exception ex) {
            Logger.getLogger(TotalNumberofBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void deleteAction(ActionEvent event) {
        try{
        DBLibraryDAO.deleteBookbyID(Integer.parseInt(searchfield.getText()));
         bookTableView.setItems(DBLibraryDAO.getAllRecords());
          bookTableView.refresh();
        }catch(Exception e){
            
        }
    }

    @FXML
    void updateAction(ActionEvent event) {
            try{
                DBLibraryDAO.updateBook(Integer.parseInt(searchfield.getText()), subjectField.getText());
                    bookTableView.setItems(DBLibraryDAO.getAllRecords());
                     bookTableView.refresh();
            }catch(Exception e){
            }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
               TableColumn<DatabaseSample,Integer> bookId=new TableColumn("bookID");
               bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
               
               TableColumn<DatabaseSample,String> bookSubject=new TableColumn("bookSubject");
               bookSubject.setCellValueFactory(new PropertyValueFactory<>("bookSubject"));
             
               TableColumn<DatabaseSample,String>bookBranch=new TableColumn("bookBranch");          
                 bookBranch.setCellValueFactory(new PropertyValueFactory<>("bookBranch"));
                 
                  TableColumn<DatabaseSample,String>bookTitle=new TableColumn("bookTitle");          
                 bookTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
                 
                  TableColumn<DatabaseSample,Integer>bookAccountNumber=new TableColumn("accountNumber");          
                 bookAccountNumber.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
                 
                  TableColumn<DatabaseSample,String>bookAuthor=new TableColumn("bookAuthor");          
                 bookAuthor.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));
                 
                  TableColumn<DatabaseSample,String>bookPublication=new TableColumn("bookPublication");          
                 bookPublication.setCellValueFactory(new PropertyValueFactory<>("bookPublication"));
                 
                  TableColumn<DatabaseSample,String>bookPrice=new TableColumn("bookPrice");          
                 bookPrice.setCellValueFactory(new PropertyValueFactory<>("bookPrice"));
                 
                  TableColumn<DatabaseSample,String>bookYear=new TableColumn("bookYear");          
                 bookYear.setCellValueFactory(new PropertyValueFactory<>("bookYear"));
                 
                  TableColumn<DatabaseSample,String>bookEditionYear=new TableColumn("bookEditionYear");          
                 bookEditionYear.setCellValueFactory(new PropertyValueFactory<>("bookEditionYear"));
                 
                  TableColumn<DatabaseSample,String>bookSupplier=new TableColumn("bookSupplier");          
                 bookSupplier.setCellValueFactory(new PropertyValueFactory<>("bookSupplier"));
                 
                  TableColumn<DatabaseSample,String>billNumber=new TableColumn("billNumber");          
                    billNumber.setCellValueFactory(new PropertyValueFactory<>("billNumber"));
                 
                  TableColumn<DatabaseSample,String>bookbillDate=new TableColumn("billDate");          
                 bookbillDate.setCellValueFactory(new PropertyValueFactory<>("billDate"));
                 
                 
           
            bookTableView.setItems(DBLibraryDAO.getAllRecords());
          // loadDatabaseData();
            bookTableView.getColumns().addAll(bookId,bookSubject,bookBranch,bookTitle,bookAccountNumber,bookAuthor,bookPublication,bookPrice,bookYear,bookEditionYear,bookSupplier,billNumber,bookbillDate);
           
        } catch (Exception ex) {
            Logger.getLogger(TotalNumberofBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    
     public void loadDatabaseData(){
                     
              String query="select * from totalNumberofBooks";
            
              ResultSet rs;
              try{
                    rs=DBUtil.dbExecute(query);
                    while(rs.next()){
                 // data.add(new DatabaseSample(rs.getInt("bookId"),rs.getString("bookSubject"),rs.getString("bookBranch")  ));
                  bookTableView.setItems(data);
                  System.out.println
                          (rs);
              
                    }
              rs.close();
          } catch (Exception ex) {
            //  Logger.getLogger(th.class.getName()).log(Level.SEVERE, null, ex);
          }
     }
}
    
    
    
