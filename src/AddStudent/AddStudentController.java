/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddStudent;

import BeansPackage.ReturnedBookObject;
import BeansPackage.StudentItem;
import DatabaseHelper.DBLibraryDAO;
import ReturnBook.ReturnBookController;
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
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddStudentController implements Initializable {

    @FXML
    private JFXTextField classField;
    @FXML
    private JFXTextField nameField;
    @FXML
    private TableView<StudentItem> studentTableView;

    @FXML
    private Text userText;
    @FXML
    private JFXTextField enrollmentIdField;
    @FXML
    private JFXTextField idFIeld;
    @FXML
    private JFXButton allrec;

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
    private void addStudentAction(ActionEvent event) {
        if (enrollmentIdField.getText().trim().isEmpty() || nameField.getText().trim().isEmpty() || classField.getText().trim().isEmpty()) {
            DialogBox.DialogBox.showDialog(DialogBox.DialogBox.field_empty);
        } else {
            try {
                DBLibraryDAO.insertStudent(enrollmentIdField.getText().trim(), nameField.getText().trim(), classField.getText().trim());
                studentTableView.setItems(DBLibraryDAO.getAllStudents());
                studentTableView.refresh();
            } catch (Exception ex) {
                Logger.getLogger(AddStudentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void backAction(ActionEvent event) {
        try {

            Stage stagetemp = (Stage) enrollmentIdField.getScene().getWindow();
            stagetemp.close();
            Stage stage = new Stage();
            AnchorPane root = FXMLLoader.load(getClass().getResource("/Dashboard/Dashboard.fxml"));
            Scene scene = new Scene(root, 1200, 600);
            stage.setResizable(false);
            stage.setTitle("Add New Book");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(ReturnBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deleteStudentAction(ActionEvent event) {
        if (idFIeld.getText().trim().isEmpty()) {
            DialogBox.DialogBox.showDialog(DialogBox.DialogBox.field_empty);

        } else {
            try {
                DBLibraryDAO.deleteStudentById(idFIeld.getText().trim());
                studentTableView.setItems(DBLibraryDAO.getAllStudents());
                studentTableView.refresh();
            } catch (Exception ex) {
                Logger.getLogger(AddStudentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void showallrecs(ActionEvent event) {
        try {
            studentTableView.setItems(DBLibraryDAO.getAllStudents());
            studentTableView.refresh();
        } catch (Exception ex) {
            Logger.getLogger(AddStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
