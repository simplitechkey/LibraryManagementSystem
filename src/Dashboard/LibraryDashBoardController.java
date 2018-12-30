/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashboard;

import DatabaseHelper.DBLibraryDAO;
import DatabaseHelper.DBUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.util.Pair;

/**
 * FXML Controller class
 *
 * @author Omskamate
 */
public class LibraryDashBoardController implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    String totalBooks = "";
    String totalIssuedBooks = "";
    String remainingBooks = "";
    @FXML
    private TextField labelTotalEntries;

    @FXML
    private JFXTextField booksRemainingLabel;

    @FXML
    private Label labelTotalNumberOfBookslabelTotalNumberOfBooksIssued;

    @FXML
    private AnchorPane labelTotalNumberOfBookslabelTotalNumberOfBooksRemaining;
    @FXML
    private JFXTextField labelnumberofbooksIssued;

    @FXML
    private AnchorPane labelTotalNumberOfBookslabelTotalNumberOfUsers;

    @FXML
    private JFXButton bookReturnBtn;

    @FXML
    private JFXButton deleteBookButton;

    @FXML
    private JFXButton newBookButton;

    @FXML
    private JFXButton bookIssueButton;

    @FXML
    void deleteBookAction(ActionEvent event) {
        try {
            Stage closestage = (Stage) labelTotalEntries.getScene().getWindow();
            closestage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/DeleteBookEntry/DeleteBookEntry.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root, 1200, 600);
            stage.setTitle("Delete Book");
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(LibraryDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void issueBookAction(ActionEvent event) {
        try {
            Stage closestage = (Stage) labelTotalEntries.getScene().getWindow();
            closestage.close();
            AnchorPane root = FXMLLoader.load(getClass().getResource("/IssueBook/IssueBook.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root, 1200, 600);
            stage.setTitle("Issue a book from Total Books");
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LibraryDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void newBookAction(ActionEvent event) {
        try {
            Stage closestage = (Stage) labelTotalEntries.getScene().getWindow();
            closestage.close();
            AnchorPane root = FXMLLoader.load(getClass().getResource("/AddBookEntry/AddBookEntry.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root, 1200, 600);
            stage.setTitle("Add New Book");
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LibraryDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void accountChangeAction(ActionEvent event) {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Authorisation");
        dialog.setHeaderText("Please use admin username and password");

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
            if(DBLibraryDAO.validateUser(usernamePassword.getKey(),usernamePassword.getValue())){
                try {
                    Stage closestage = (Stage) labelTotalEntries.getScene().getWindow();
                    closestage.close();
                    AnchorPane root = FXMLLoader.load(getClass().getResource("/ChangePassword/ChangePassword.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root, 1200, 600);
                    stage.setTitle("Total Issued Books");
                    //stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LibraryDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

       @FXML
    void changePasswordAction(ActionEvent event) {
        try {
            Stage closestage = (Stage) labelTotalEntries.getScene().getWindow();
            closestage.close();
            AnchorPane root = FXMLLoader.load(getClass().getResource("/ChangePassword/ChangePassword.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root, 1200, 600);
            stage.setTitle("Total Issued Books");
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(LibraryDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void returnBookAction(ActionEvent event) {
        try {
            Stage closestage = (Stage) labelTotalEntries.getScene().getWindow();
            closestage.close();
            AnchorPane root = FXMLLoader.load(getClass().getResource("/ReturnBook/ReturnBook.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root, 1200, 600);
            stage.setTitle("Total Issued Books");
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LibraryDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void totalbookscreenpress(MouseEvent event) {
        try {
            Stage closestage = (Stage) labelTotalEntries.getScene().getWindow();
            closestage.close();
            Stage stage;
            stage = new Stage();
            AnchorPane root = FXMLLoader.load(getClass().getResource("/fromDashboard/TotalNumberofBooks.fxml"));
            Scene scene = new Scene(root, 1200, 600);

            stage.setResizable(false);
            stage.setTitle("Total Books");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LibraryDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void gotoIssuebookTable(MouseEvent event) {
        try {

            Stage closestage = (Stage) labelTotalEntries.getScene().getWindow();
            closestage.close();
            Stage stage;
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fromDashboard/IssuedBooksTable.fxml"));
            Scene scene = new Scene(root, 1200, 600);
            stage.setResizable(false);
            stage.setTitle("Issue New Book");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(LibraryDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void gotoRemainingBookTable(MouseEvent event) {
        try {
            Stage closestage = (Stage) labelTotalEntries.getScene().getWindow();
            closestage.close();
            Stage stage;
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fromDashboard/ReturnedBooksTable.fxml"));
            Scene scene = new Scene(root, 1200, 600);
            stage.setResizable(false);
            stage.setTitle("Return Book");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LibraryDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            System.out.println("init of lib dash");
            totalBooks = String.valueOf(DBLibraryDAO.getAllRecords().size());
            totalIssuedBooks = String.valueOf(String.valueOf(DBLibraryDAO.getAllIssuedBooksRecords().size()));
            remainingBooks = String.valueOf((DBLibraryDAO.getAllRecords().size() - DBLibraryDAO.getAllIssuedBooksRecords().size()));
            labelTotalEntries.setStyle("-fx-text-fill: white;");
            labelnumberofbooksIssued.setStyle("-fx-text-fill: white;");
            booksRemainingLabel.setStyle("-fx-text-fill: white;");
            refreshDashBoard();

            /*  Timeline timeline = new Timeline(
                              new KeyFrame(Duration.seconds(0.5), (ActionEvent actionEvent) -> {
                                  // Call refreshDashBoard method for every 2 sec.
                                  refreshDashBoard();
                                  
                              }));
                      timeline.setCycleCount(Animation.INDEFINITE);
                      timeline.play();*/
            String sql1 = "delete from tableReturnedBooks";
            String sql2 = "insert into tableReturnedBooks SELECT totalNumberofBooks.bookId, totalNumberofBooks.bookSubject,totalNumberofBooks.bookBranch , totalNumberofBooks.bookTitle, totalNumberofBooks.bookAccNo, totalNumberofBooks.bookAuthor , totalNumberofBooks.bookPublication, totalNumberofBooks.bookPrice,  totalNumberofBooks.bookYear,  totalNumberofBooks.bookEditionYear,  totalNumberofBooks.bookSupplier, totalNumberofBooks.billNo,  totalNumberofBooks.billDate FROM totalNumberofBooks LEFT JOIN tableIssuedBooks ON totalNumberofBooks.bookId = tableIssuedBooks.bookId where tableIssuedBooks.bookId is null;";
            DBUtil.dbexcuteQuery(sql1);
            DBUtil.dbexcuteQuery(sql2);
            DBLibraryDAO.getAllRecords();
            DBLibraryDAO.getAllIssuedBooksRecords();
            DBLibraryDAO.getAllReturnedBooksRecords();

        } catch (Exception ex) {
            Logger.getLogger(LibraryDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void refreshDashBoard() {
        try {

            labelTotalEntries.setText(totalBooks);
            labelnumberofbooksIssued.setText(totalIssuedBooks);
            booksRemainingLabel.setText(remainingBooks);

        } catch (Exception ex) {
            Logger.getLogger(LibraryDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
