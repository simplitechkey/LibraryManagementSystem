/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeleteBookEntry;

import AddBookEntry.AddBookController;
import BeansPackage.DatabaseSample;
import DatabaseHelper.DBLibraryDAO;
import DatabaseHelper.DBUtil;
import DialogBox.DialogBox;
import IssueBook.IssueBookController;
import animatefx.animation.FadeInLeftBig;
import animatefx.animation.FadeInRightBig;
import animatefx.animation.FadeOutLeftBig;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import fromDashboard.TotalNumberofBooksController;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
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
public class DeleteBookEntryController implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    ObservableList<DatabaseSample> data = FXCollections.observableArrayList();

    @FXML
    TableView<DatabaseSample> bookTableView;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private Text userText;

    @FXML
    private JFXTextField searchfield;

    @FXML
    void searchAction(ActionEvent event) {
        try {
            data = DBLibraryDAO.getAllRecords();
            if ((searchfield.getText().trim().isEmpty() || (searchfield.getText() == null))) {
                DialogBox.showDialog(DialogBox.dialog_text_null);
                deleteBtn.setVisible(false);
                bookTableView.setItems(DBLibraryDAO.getAllRecords());
            } else {
                if (!searchfield.getText().trim().isEmpty() && DBLibraryDAO.searchBookById(searchfield.getText().trim()).isEmpty()) {
                    DialogBox.showDialog(DialogBox.no_values_found);
                    deleteBtn.setVisible(false);
                } else {

                    deleteBtn.setVisible(true);
                    bookTableView.setItems(DBLibraryDAO.searchBookById(searchfield.getText().trim()));
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(IssueBookController.class.getName()).log(Level.SEVERE, null, ex);
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

            Parent root = FXMLLoader.load(getClass().getResource("/Dashboard/Dashboard.fxml"));
            Scene scene = new Scene(root, 1200, 600);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Library DashBoard");
            stage.setScene(scene);
            stage.show();
            new FadeInRightBig(root).play();

            Stage stagetemp = (Stage) searchfield.getScene().getWindow();
            stagetemp.close();
        } catch (IOException ex) {
            Logger.getLogger(AddBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    void deleteBookAction(ActionEvent event) {
        try {
            data = DBLibraryDAO.searchBookById(searchfield.getText());
            if ((searchfield.getText().trim().isEmpty() || (searchfield.getText() == null))) {

                DialogBox.showDialog(DialogBox.dialog_text_null);
                deleteBtn.setVisible(false);
                bookTableView.setItems(DBLibraryDAO.getAllRecords());
            } else {
                if (!searchfield.getText().trim().isEmpty() && data.isEmpty()) {
                    DialogBox.showDialog(DialogBox.no_values_found);
                } else {
                    DBLibraryDAO.deleteBookbyID(searchfield.getText().trim());
                    DBLibraryDAO.removeEntryfromIssuedIfAvailable(searchfield.getText().trim());
                    DBLibraryDAO.removeEntryfromReturnedIfAvailable(searchfield.getText().trim());
                    DialogBox.showDialog(DialogBox.delete_successful);
                    bookTableView.setItems(DBLibraryDAO.getAllRecords());
                    bookTableView.refresh();
                }
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            TableColumn<DatabaseSample, String> bookId = new TableColumn("bookID");
            bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));

            TableColumn<DatabaseSample, String> bookSubject = new TableColumn("bookSubject");
            bookSubject.setCellValueFactory(new PropertyValueFactory<>("bookSubject"));

            TableColumn<DatabaseSample, String> bookBranch = new TableColumn("bookBranch");
            bookBranch.setCellValueFactory(new PropertyValueFactory<>("bookBranch"));

            TableColumn<DatabaseSample, String> bookTitle = new TableColumn("bookTitle");
            bookTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));

            TableColumn<DatabaseSample, Integer> bookAccountNumber = new TableColumn("accountNumber");
            bookAccountNumber.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));

            TableColumn<DatabaseSample, String> bookAuthor = new TableColumn("bookAuthor");
            bookAuthor.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));

            TableColumn<DatabaseSample, String> bookPublication = new TableColumn("bookPublication");
            bookPublication.setCellValueFactory(new PropertyValueFactory<>("bookPublication"));

            TableColumn<DatabaseSample, String> bookPrice = new TableColumn("bookPrice");
            bookPrice.setCellValueFactory(new PropertyValueFactory<>("bookPrice"));

            TableColumn<DatabaseSample, String> bookYear = new TableColumn("bookYear");
            bookYear.setCellValueFactory(new PropertyValueFactory<>("bookYear"));

            TableColumn<DatabaseSample, String> bookEditionYear = new TableColumn("bookEditionYear");
            bookEditionYear.setCellValueFactory(new PropertyValueFactory<>("bookEditionYear"));

            TableColumn<DatabaseSample, String> bookSupplier = new TableColumn("bookSupplier");
            bookSupplier.setCellValueFactory(new PropertyValueFactory<>("bookSupplier"));

            TableColumn<DatabaseSample, String> billNumber = new TableColumn("billNumber");
            billNumber.setCellValueFactory(new PropertyValueFactory<>("billNumber"));

            TableColumn<DatabaseSample, String> bookbillDate = new TableColumn("billDate");
            bookbillDate.setCellValueFactory(new PropertyValueFactory<>("billDate"));

            userText.setText(DBLibraryDAO.userName);
            bookTableView.setItems(DBLibraryDAO.getAllRecords());
            deleteBtn.setVisible(false);
            bookTableView.getColumns().addAll(bookId, bookSubject, bookBranch, bookTitle, bookAccountNumber, bookAuthor, bookPublication, bookPrice, bookYear, bookEditionYear, bookSupplier, billNumber, bookbillDate);

        } catch (Exception ex) {
            Logger.getLogger(TotalNumberofBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
