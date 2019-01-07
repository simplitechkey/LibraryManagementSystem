/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReturnBook;

import AddBookEntry.AddBookController;
import BeansPackage.IssuedBookObject;
import BeansPackage.IssuedBookObject;
import Dashboard.LibraryDashBoardController;
import DatabaseHelper.DBLibraryDAO;
import DialogBox.DialogBox;
import IssueBook.IssueBookController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.IntegerValidator;
import com.jfoenix.validation.NumberValidator;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * FXML Controller class
 *
 * @author omkarkamate
 */
public class ReturnBookController implements Initializable {

    public static final long MAX_DAYS_OF_BOOKS = 3;
    public static long diff;
  
    ObservableList<IssuedBookObject> data = FXCollections.observableArrayList();
 
    @FXML
    private TableView<IssuedBookObject> bookTableView;

    @FXML
    private JFXTextField searchfield;

    @FXML
    private JFXButton returnBtn;

    @FXML
    void returnBookAction(ActionEvent event) {
        try {
            String bookId = searchfield.getText().trim();
            if ((bookId.isEmpty())) {
                DialogBox.showDialog(DialogBox.dialog_text_null);
                returnBtn.setVisible(false);
                bookTableView.setItems(DBLibraryDAO.getAllIssuedBooksRecords());
            } else {
                if (!isBookPeriodOverLimit(DBLibraryDAO.getIssuedDateFromId(bookId))) {
                    DBLibraryDAO.returnBookfromIssuedBooks(bookId);
                    DialogBox.showDialog(DialogBox.dialog_return_successful);
                    bookTableView.setItems(DBLibraryDAO.getAllIssuedBooksRecords());
                    bookTableView.refresh();

                } else {

                    Dialog<Pair<String, String>> dialog = new Dialog<>();
                    dialog.setTitle("OverDue");
                    dialog.setHeaderText("Book Due Date is Over .Return is " + String.valueOf(diff-MAX_DAYS_OF_BOOKS) + " days late \n Fine to Be Collected = " + String.valueOf((diff-MAX_DAYS_OF_BOOKS) * 2)+"\n If fine is Paid Please enter username and password");

                    //dialog.setGraphic(new ImageView(this.getClass().getResource("l1.jpg").toString()));
                    ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(20, 150, 10, 10));

                    TextField username = new TextField();
                    username.setPromptText("Username");
                    PasswordField password = new PasswordField();
                    password.setPromptText("Password");

                    grid.add(new Label("Username:"), 0, 0);
                    grid.add(username, 1, 0);
                    grid.add(new Label("Password:"), 0, 1);
                    grid.add(password, 1, 1);

                    Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
                    loginButton.setDisable(true);

                    username.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                        loginButton.setDisable(newValue.trim().isEmpty());
                    });

                    dialog.getDialogPane().setContent(grid);

                    Platform.runLater(() -> username.requestFocus());

                    dialog.setResultConverter(dialogButton -> {
                        if (dialogButton == loginButtonType) {
                            return new Pair<>(username.getText(), password.getText());
                        }
                        return null;
                    });

                    Optional<Pair<String, String>> result = dialog.showAndWait();

                    result.ifPresent(usernamePassword -> {
                        System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
                        if (DBLibraryDAO.validateUser(usernamePassword.getKey(), usernamePassword.getValue())) {
                              
                            try {
                                DBLibraryDAO.returnBookfromIssuedBooks(bookId);
                                  DialogBox.showDialog(DialogBox.dialog_return_successful);
                                  bookTableView.setItems(DBLibraryDAO.getAllIssuedBooksRecords());
                                  bookTableView.refresh();
                            } catch (Exception ex) {
                                Logger.getLogger(ReturnBookController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {

                        }

                    });

                }
            }

        } catch (Exception ex) {
            Logger.getLogger(IssueBookController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void searchAction(ActionEvent event) {
        try {
            String bookId = searchfield.getText().trim();
            data = DBLibraryDAO.searchIssuedBookBookById(searchfield.getText().trim());
            if ((bookId.isEmpty() || (bookId == null))) {
                DialogBox.showDialog(DialogBox.dialog_text_null);
                returnBtn.setVisible(false);
                bookTableView.setItems(DBLibraryDAO.getAllIssuedBooksRecords());
            } else {
                if (!searchfield.getText().trim().isEmpty() && data.isEmpty()) {
                    DialogBox.showDialog(DialogBox.no_values_found);
                    returnBtn.setVisible(false);
                } else {
                     returnBtn.setVisible(true);
                    bookTableView.setItems(data);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(ReturnBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void showallrecs(ActionEvent event) {
        try {
            bookTableView.setItems(DBLibraryDAO.getAllIssuedBooksRecords());
            bookTableView.refresh();
            searchfield.setText("");
        } catch (Exception ex) {
            Logger.getLogger(ReturnBookController.class.getName()).log(Level.SEVERE, null, ex);
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
            stage.setResizable(false);
            stage.setTitle("Add New Book");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(ReturnBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void initialize(URL url, ResourceBundle rb) {
        try {
            TableColumn<IssuedBookObject, Integer> bookId = new TableColumn("bookID");
            bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));

            TableColumn<IssuedBookObject, String> bookSubject = new TableColumn("bookSubject");
            bookSubject.setCellValueFactory(new PropertyValueFactory<>("bookSubject"));

            TableColumn<IssuedBookObject, String> bookBranch = new TableColumn("bookBranch");
            bookBranch.setCellValueFactory(new PropertyValueFactory<>("bookBranch"));

            TableColumn<IssuedBookObject, String> bookTitle = new TableColumn("bookTitle");
            bookTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));

            TableColumn<IssuedBookObject, Integer> bookAccountNumber = new TableColumn("accountNumber");
            bookAccountNumber.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));

            TableColumn<IssuedBookObject, String> bookAuthor = new TableColumn("bookAuthor");
            bookAuthor.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));

            TableColumn<IssuedBookObject, String> bookPublication = new TableColumn("bookPublication");
            bookPublication.setCellValueFactory(new PropertyValueFactory<>("bookPublication"));

            TableColumn<IssuedBookObject, String> bookPrice = new TableColumn("bookPrice");
            bookPrice.setCellValueFactory(new PropertyValueFactory<>("bookPrice"));

            TableColumn<IssuedBookObject, String> bookYear = new TableColumn("bookYear");
            bookYear.setCellValueFactory(new PropertyValueFactory<>("bookYear"));

            TableColumn<IssuedBookObject, String> bookEditionYear = new TableColumn("bookEditionYear");
            bookEditionYear.setCellValueFactory(new PropertyValueFactory<>("bookEditionYear"));

            TableColumn<IssuedBookObject, String> bookSupplier = new TableColumn("bookSupplier");
            bookSupplier.setCellValueFactory(new PropertyValueFactory<>("bookSupplier"));

            TableColumn<IssuedBookObject, String> billNumber = new TableColumn("billNumber");
            billNumber.setCellValueFactory(new PropertyValueFactory<>("billNumber"));

            TableColumn<IssuedBookObject, String> bookbillDate = new TableColumn("billDate");
            bookbillDate.setCellValueFactory(new PropertyValueFactory<>("billDate"));

            TableColumn<IssuedBookObject, String> issuedTo = new TableColumn("issuedTo");
            issuedTo.setCellValueFactory(new PropertyValueFactory<>("issuedTo"));

            TableColumn<IssuedBookObject, String> studentClass = new TableColumn("studentClass");
            studentClass.setCellValueFactory(new PropertyValueFactory<>("studentClass"));

            TableColumn<IssuedBookObject, String> issuedDate = new TableColumn("issuedDate");
            issuedDate.setCellValueFactory(new PropertyValueFactory<>("issuedDate"));

            //userText.setText(DBLibraryDAO.userName);
            bookTableView.setItems(DBLibraryDAO.getAllIssuedBooksRecords());
            bookTableView.getColumns().addAll(bookId, bookSubject, bookBranch, bookTitle, bookAccountNumber, bookAuthor, bookPublication, bookPrice, bookYear, bookEditionYear, bookSupplier, billNumber, bookbillDate, issuedTo, studentClass, issuedDate);

            returnBtn.setVisible(false);

        } catch (Exception ex) {
            Logger.getLogger(IssueBookController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean isBookPeriodOverLimit(String bookissuedDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate issuedDate = LocalDate.parse(bookissuedDate, formatter);
        LocalDate today = LocalDate.now();
        diff = DAYS.between(issuedDate, today);

        return diff > MAX_DAYS_OF_BOOKS;
    }

}
