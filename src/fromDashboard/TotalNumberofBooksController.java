/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fromDashboard;

import BeansPackage.DatabaseSample;
import DatabaseHelper.DBLibraryDAO;
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

/**
 * FXML Controller class
 *
 * @author omkarkamate
 */
public class TotalNumberofBooksController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
   TableView bookTableView;
     ObservableList<DatabaseSample> data=FXCollections.observableArrayList();
         
    @FXML
    private TableColumn<DatabaseSample,Integer> bookId=new TableColumn();

    @FXML
    private TableColumn<DatabaseSample,String> bookSubject=new TableColumn();

    @FXML
    private TableColumn<DatabaseSample,String> bookBranch=new TableColumn();

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
    void showallrecs(ActionEvent event) {
        try {
            bookTableView.setItems(data);
            bookTableView.refresh();
           
        } catch (Exception ex) {
            Logger.getLogger(TotalNumberofBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void addAction(ActionEvent event) {
            DBLibraryDAO.insertBook(Integer.parseInt(idField.getText()), subjectField.getText(), branchField.getText());
    }

    @FXML
    void deleteAction(ActionEvent event) {
        DBLibraryDAO.deleteBookbyID(Integer.parseInt(searchfield.getText()));
    }

    @FXML
    void updateAction(ActionEvent event) {
            try{
                DBLibraryDAO.updateBook(Integer.parseInt(searchfield.getText()), subjectField.getText());
            }catch(Exception e){
                e.printStackTrace();
            }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            bookId.setCellValueFactory(new PropertyValueFactory("bookId"));
            bookSubject.setCellValueFactory(new PropertyValueFactory("bookSubject"));
            bookBranch.setCellValueFactory(new PropertyValueFactory("bookBranch"));
            bookTableView.setItems(DBLibraryDAO.getAllRecords());
           
        } catch (Exception ex) {
            Logger.getLogger(TotalNumberofBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
