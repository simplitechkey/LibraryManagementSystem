/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IssueBook;

import AddBookEntry.AddBookController;
import BeansPackage.ReturnedBookObject;
import DatabaseHelper.DBLibraryDAO;
import DialogBox.DialogBox;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author omkarkamate
 */
public class IssueBookController implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    ObservableList<ReturnedBookObject> data = FXCollections.observableArrayList();
    @FXML
    private TableView<ReturnedBookObject> bookTableView;
    LocalDate today;

    @FXML
    private Text userText;

    @FXML
    private JFXButton allrec;

    @FXML
    private JFXTextField studentNameField;

    @FXML
    private JFXTextField studentClassField;

    @FXML
    private JFXButton issueBtn;

    @FXML
    private JFXTextField searchfield;
    @FXML
    private JFXTextField enrollmentIdField;
    @FXML
    private ContextMenu studentIdList;

    @FXML
    void issueBookAction(ActionEvent event) {
        try {
            data = DBLibraryDAO.searchReturnedBookById(searchfield.getText());
            if ((searchfield.getText().trim().isEmpty() || (searchfield.getText() == null))) {
                DialogBox.showDialog(DialogBox.dialog_text_null);
                issueBtn.setVisible(false);
                bookTableView.setItems(DBLibraryDAO.getAllReturnedBooksRecords());
            } else {
                if (!searchfield.getText().trim().isEmpty() && data.isEmpty()) {
                    DialogBox.showDialog(DialogBox.no_values_found);

                } else {
                    DBLibraryDAO.issueBookfromTotalBooks(searchfield.getText().trim(), studentNameField.getText().trim(), studentClassField.getText().trim(), today.toString());
                    DialogBox.showDialog(DialogBox.dialog_issue_successful);
                    bookTableView.setItems(DBLibraryDAO.getAllReturnedBooksRecords());
                    bookTableView.refresh();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(IssueBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void searchAction(ActionEvent event) {
        try {
            data = DBLibraryDAO.searchReturnedBookById(searchfield.getText());
            if ((searchfield.getText().trim().isEmpty() || (searchfield.getText() == null))) {
                DialogBox.showDialog(DialogBox.dialog_text_null);
                issueBtn.setVisible(false);
                bookTableView.setItems(DBLibraryDAO.getAllReturnedBooksRecords());
            } else {
                if (!searchfield.getText().trim().isEmpty() && data.isEmpty()) {
                    DialogBox.showDialog(DialogBox.no_values_found);
                    issueBtn.setVisible(false);
                } else {
                    //data=DBLibraryDAO.searchIssuedBookBookById(Integer.parseInt(searchfield.getText()));
                    issueBtn.setVisible(true);
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
            bookTableView.setItems(DBLibraryDAO.getAllReturnedBooksRecords());
            bookTableView.refresh();
        } catch (Exception ex) {
            Logger.getLogger(IssueBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void backAction(ActionEvent event) {
        try {

            Stage stagetemp = (Stage) searchfield.getScene().getWindow();

            stagetemp.close();
            Stage stage = new Stage();
            AnchorPane root = FXMLLoader.load(getClass().getResource("/Dashboard/Dashboard.fxml"));
            Scene scene = new Scene(root, 1200, 600);
            // stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.setTitle("Add New Book");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(AddBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void initialize(URL url, ResourceBundle rb) {
        try {

            today = LocalDate.now();
            TableColumn<ReturnedBookObject, Integer> bookId = new TableColumn("bookID");
            bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));

            TableColumn<ReturnedBookObject, String> bookSubject = new TableColumn("bookSubject");
            bookSubject.setCellValueFactory(new PropertyValueFactory<>("bookSubject"));

            TableColumn<ReturnedBookObject, String> bookBranch = new TableColumn("bookBranch");
            bookBranch.setCellValueFactory(new PropertyValueFactory<>("bookBranch"));

            TableColumn<ReturnedBookObject, String> bookTitle = new TableColumn("bookTitle");
            bookTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));

            TableColumn<ReturnedBookObject, Integer> bookAccountNumber = new TableColumn("accountNumber");
            bookAccountNumber.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));

            TableColumn<ReturnedBookObject, String> bookAuthor = new TableColumn("bookAuthor");
            bookAuthor.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));

            TableColumn<ReturnedBookObject, String> bookPublication = new TableColumn("bookPublication");
            bookPublication.setCellValueFactory(new PropertyValueFactory<>("bookPublication"));

            TableColumn<ReturnedBookObject, String> bookPrice = new TableColumn("bookPrice");
            bookPrice.setCellValueFactory(new PropertyValueFactory<>("bookPrice"));

            TableColumn<ReturnedBookObject, String> bookYear = new TableColumn("bookYear");
            bookYear.setCellValueFactory(new PropertyValueFactory<>("bookYear"));

            TableColumn<ReturnedBookObject, String> bookEditionYear = new TableColumn("bookEditionYear");
            bookEditionYear.setCellValueFactory(new PropertyValueFactory<>("bookEditionYear"));

            TableColumn<ReturnedBookObject, String> bookSupplier = new TableColumn("bookSupplier");
            bookSupplier.setCellValueFactory(new PropertyValueFactory<>("bookSupplier"));

            TableColumn<ReturnedBookObject, String> billNumber = new TableColumn("billNumber");
            billNumber.setCellValueFactory(new PropertyValueFactory<>("billNumber"));

            TableColumn<ReturnedBookObject, String> bookbillDate = new TableColumn("billDate");
            bookbillDate.setCellValueFactory(new PropertyValueFactory<>("billDate"));

            userText.setText(DBLibraryDAO.userName);
            issueBtn.setVisible(false);
            bookTableView.setItems(DBLibraryDAO.getAllReturnedBooksRecords());
            bookTableView.getColumns().addAll(bookId, bookSubject, bookBranch, bookTitle, bookAccountNumber, bookAuthor, bookPublication, bookPrice, bookYear, bookEditionYear, bookSupplier, billNumber, bookbillDate);

            enrollmentIdField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                studentIdList.getItems().clear();
                studentIdList.getItems().addAll(DBLibraryDAO.getAllIdscontext(newValue));
                Bounds boundsInScene = enrollmentIdField.localToScreen(enrollmentIdField.getBoundsInLocal());
                studentIdList.show(enrollmentIdField, boundsInScene.getMinX(), boundsInScene.getMaxY());
            });

            studentIdList.setOnAction(e
                    -> {

                try {
                    enrollmentIdField.setText(((MenuItem) e.getTarget()).getText());
                    ResultSet rs = DBLibraryDAO.getStudentObjectById(((MenuItem) e.getTarget()).getText());
                    studentNameField.setText(rs.getString("studentName"));
                    studentClassField.setText(rs.getString("studentClass"));
                } catch (Exception ex) {
                    Logger.getLogger(IssueBookController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });
        } catch (Exception ex) {
            Logger.getLogger(IssueBookController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
