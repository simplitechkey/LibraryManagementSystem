/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddBookEntry;

import DatabaseHelper.DBLibraryDAO;
import DatabaseHelper.DBUtil;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.IntegerValidator;
import java.io.IOException;
//import java.awt.Insets;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * FXML Controller class
 *
 * @author Omskamate
 */
public class AddBookController implements Initializable {

    @FXML
    private JFXTextField bookIdField;

    @FXML
    private Text userText;

    @FXML
    private JFXTextField bookSubjectField;

    @FXML
    private JFXTextField bookBranchField;

    @FXML
    private JFXTextField bookTitleField;

    @FXML
    private JFXTextField bookAccNoField;

    @FXML
    private JFXTextField bookAuthorField;

    @FXML
    private JFXTextField bookPublicationField;

    @FXML
    private JFXTextField bookPriceField;

    @FXML
    private JFXTextField bookYearField;

    @FXML
    private JFXTextField bookEditionField;

    @FXML
    private JFXTextField bookSupplierField;

    @FXML
    private JFXTextField bookNoField;

    @FXML
    private JFXDatePicker bookbillDateField;

    ArrayList<JFXTextField> compulsoryText;

    @FXML
    void addRecord(ActionEvent event) {
        if (noFieldEmpty()) {
            
            if (!notDuplicateEntry()) {
                DBLibraryDAO.insertBook(bookIdField.getText(), bookSubjectField.getText(), bookBranchField.getText(), bookTitleField.getText(), bookAccNoField.getText(), bookAuthorField.getText(), bookPublicationField.getText(), bookPriceField.getText(), bookYearField.getText(), bookEditionField.getText(), bookSupplierField.getText(), bookNoField.getText(), bookbillDateField.getValue().toString());
                DialogBox.DialogBox.showDialog(DialogBox.DialogBox.record_added);
                
                for (int i = 0; i < compulsoryText.size(); i++) {
              
                    compulsoryText.get(i).setText("");
                }
            } else {
                
                DialogBox.DialogBox.showDialog(DialogBox.DialogBox.duplicate_Entry);
                
            }
        } else {
            DialogBox.DialogBox.showDialog(DialogBox.DialogBox.field_empty);

        }
    }

    @FXML
    void backAction(ActionEvent event) {
        try {

            Stage stagetemp = (Stage) bookNoField.getScene().getWindow();
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
    public void initialize(URL url, ResourceBundle rb) {
        compulsoryText = new ArrayList<>(Arrays.asList(bookIdField, bookAccNoField, bookAuthorField, bookBranchField, bookEditionField, bookNoField, bookPriceField, bookPublicationField, bookSubjectField, bookSupplierField, bookTitleField, bookYearField));
        userText.setText(DBLibraryDAO.userName);

    }

    private boolean noFieldEmpty() {
        for (int i = 0; i < compulsoryText.size(); i++) {
            return !(compulsoryText.get(i).getText().trim().isEmpty() || compulsoryText.get(i).getText().equals("") || bookbillDateField.getEditor().getText().isEmpty());

        }
        return false;
    }

    private boolean notDuplicateEntry() {
        boolean duplicateEntry = false;

        try {       
            
            String sql="SELECT  bookId FROM totalNumberofBooks WHERE bookId = '"+bookIdField.getText()+"'";
            ResultSet rs = DBUtil.dbExecute(sql);
            duplicateEntry = rs.next();

        } catch (Exception ex) {
            Logger.getLogger(AddBookController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return duplicateEntry;
    }
}
