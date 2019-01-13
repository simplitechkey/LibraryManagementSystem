/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fromDashboard;

import AddStudent.AddStudentController;
import BeansPackage.StudentItem;
import DatabaseHelper.DBLibraryDAO;
import DialogBox.DialogBox;
import IssueBook.IssueBookController;
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
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Omskamate
 */
public class StudentsTableController implements Initializable {
    
  ObservableList<StudentItem> data=FXCollections.observableArrayList();

    @FXML
    private Text userText;
    @FXML
    private TableView<StudentItem> studentTableView;
    @FXML
    private JFXButton allrec;
    @FXML
    private JFXTextField searchfield;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            TableColumn<StudentItem, String> enrollmentId = new TableColumn("EnrollmentId");
            enrollmentId.setCellValueFactory(new PropertyValueFactory<>("enrollmentId"));

            TableColumn<StudentItem, String> studentName = new TableColumn("StudentName");
            studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));

            TableColumn<StudentItem, String> studentClass = new TableColumn("StudentClass");
            studentClass.setCellValueFactory(new PropertyValueFactory<>("studentClass"));

            studentTableView.getColumns().addAll(enrollmentId, studentName, studentClass);
            studentTableView.setItems(DBLibraryDAO.getAllStudents());
            studentTableView.refresh();
        } catch (Exception ex) {
            Logger.getLogger(AddStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void backAction(ActionEvent event) {
        try {
            Stage stagetempadmin     = (Stage) searchfield.getScene().getWindow();
                  stagetempadmin.close();
            Stage stage=new Stage();
            AnchorPane root = FXMLLoader.load(getClass().getResource("/Dashboard/Dashboard.fxml"));
            Scene scene = new Scene(root,1200,600);
           
            stage.setResizable(false);
            stage.setTitle("Add New Book");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StudentsTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void showallrecs(ActionEvent event) {
        try {
            studentTableView.setItems(DBLibraryDAO.getAllStudents());
            studentTableView.refresh();
        } catch (Exception ex) {
            Logger.getLogger(StudentsTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void searchAction(ActionEvent event) {
        
         try {
             if((searchfield.getText().trim().isEmpty() || (searchfield.getText() == null))) {
            DialogBox.showDialog(DialogBox.dialog_text_null);
            studentTableView.setItems(DBLibraryDAO.getAllStudents());
              studentTableView.refresh();
             }else{
            data=DBLibraryDAO.searchStudentById(searchfield.getText().trim());
            studentTableView.setItems(data);
            studentTableView.refresh();
             } 
           
        } catch (Exception ex) {
            Logger.getLogger(IssueBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
