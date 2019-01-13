/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fromDashboard;

import AddBookEntry.AddBookController;
import BeansPackage.DatabaseSample;
import Dashboard.LibraryDashBoardController;
import DatabaseHelper.DBLibraryDAO;
import DatabaseHelper.DBUtil;
import DialogBox.DialogBox;
import animatefx.animation.FadeInRightBig;
import bvjiniolibrarymanagement.FXMLDocumentController;
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
public class TotalNumberofBooksController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ObservableList<DatabaseSample> data=FXCollections.observableArrayList();
         
    @FXML
   TableView<DatabaseSample> bookTableView;
     
  
     @FXML
    private Text userText;

    private JFXTextField subjectField;

    
    @FXML
    private JFXTextField searchfield;
    @FXML
    private JFXButton allrec;
    @FXML
    private JFXButton newBookButton;
    @FXML
    private JFXButton deleteBookButton;
    
    
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
            bookTableView.setItems(DBLibraryDAO.getAllRecords());
              bookTableView.refresh();
        }else{
            data=DBLibraryDAO.searchBookById(searchfield.getText());
            bookTableView.setItems(data);
              bookTableView.refresh();
               }
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
    void newBookAction(ActionEvent event) {
        try {

            AnchorPane root = FXMLLoader.load(getClass().getResource("/AddBookEntry/AddBookEntry.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root, 1200, 600);
            stage.setTitle("Add New Book");
            stage.setScene(scene);
            stage.show();
            new FadeInRightBig(root).play();
            Stage closestage = (Stage) searchfield.getScene().getWindow();
            closestage.close();
        } catch (IOException ex) {
            Logger.getLogger(LibraryDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void deleteBookAction(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/DeleteBookEntry/DeleteBookEntry.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root, 1200, 600);
            stage.setTitle("Delete Book");
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
            new FadeInRightBig(root).play();

            Stage closestage = (Stage) searchfield.getScene().getWindow();
            closestage.close();

        } catch (IOException ex) {
            Logger.getLogger(LibraryDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
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
            stage.initStyle(StageStyle.UTILITY);
             stage.setResizable(false);
             stage.setTitle("Add New Book");
            stage.setScene(scene);
            stage.show();
       } catch (IOException ex) {
           Logger.getLogger(AddBookController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    void addAction(ActionEvent event) {
        try {
            //DBLibraryDAO.insertBook(Integer.parseInt(idField.getText()), subjectField.getText(), branchField.getText());
           bookTableView.setItems(DBLibraryDAO.getAllRecords());
           bookTableView.refresh();
        } catch (Exception ex) {
            Logger.getLogger(TotalNumberofBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void deleteAction(ActionEvent event) {
        try{
        DBLibraryDAO.deleteBookbyID(searchfield.getText());
         bookTableView.setItems(DBLibraryDAO.getAllRecords());
          bookTableView.refresh();
        }catch(Exception e){
            
        }
    }

    void updateAction(ActionEvent event) {
            try{
                DBLibraryDAO.updateBook(searchfield.getText(), subjectField.getText());
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
                 
                  userText.setText(DBLibraryDAO.userName);
           
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
    
    
    

