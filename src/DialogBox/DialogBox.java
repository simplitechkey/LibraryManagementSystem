/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DialogBox;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author omkarkamate
 */
public class DialogBox {

    public static final int dialog_text_null = 1;
    public static final int dialog_issue_successful = 2;
    public static final int dialog_return_successful = 3;
    public static final int delete_successful = 4;
    public static final int record_added = 5;
    public static final int user_pass_blank = 6;
    public static final int no_values_found = 7;
    public static final int field_empty = 8;
    public static final int duplicate_Entry = 9;
    public static final int user_not_exists=10;
    public static final int pattern_not_matched=11;
    public static final int pass_not_matched=12;
    public static final int change_pass_success=13;

    public static void showDialog(int i) {
        switch (i) {
            case dialog_text_null:
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Null query found");
                alert.setContentText("Field Cannot be null");
                alert.showAndWait();
                break;

            case dialog_issue_successful:
                Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                alert3.setHeaderText("Book Issued Successfully");
                alert3.setContentText("Book successfully issued");
                alert3.showAndWait();
                break;

            case dialog_return_successful:
                Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                alert4.setHeaderText("Book Returned Successfully");
                alert4.setContentText("Book Returned Successfully");
                alert4.showAndWait();
                break;

            case delete_successful:
                Alert alert5 = new Alert(Alert.AlertType.INFORMATION);
                alert5.setHeaderText("Book Record Successfully Deleted");
                alert5.setContentText("Book Record Successfully Deleted");
                alert5.showAndWait();
                break;

            case record_added:
                Alert alert6 = new Alert(Alert.AlertType.INFORMATION);
                alert6.setHeaderText("Book Record Added Successfully");
                alert6.setContentText("Book Record Added Successfully");
               alert6.showAndWait();
               break;

            case user_pass_blank:
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setContentText("Username and Password cannot be blank");
                alert2.showAndWait();
                break;

            case no_values_found:
                Alert alertnoval = new Alert(Alert.AlertType.ERROR);
                alertnoval.setContentText("No Values found.Enter Another Query");
                alertnoval.showAndWait();
                break;

            case field_empty:
                Alert emptyAlert = new Alert(Alert.AlertType.ERROR);
                emptyAlert.setContentText("One or More Field Are Empty");
                emptyAlert.showAndWait();
                break;

            case duplicate_Entry:
                Alert duplicateAlert = new Alert(Alert.AlertType.ERROR);
                duplicateAlert.setHeaderText("Insertion Error");
                duplicateAlert.setContentText("Duplicate Entry Found. Book ID already present in databse");
                duplicateAlert.showAndWait();
                break;
             
            case user_not_exists:
                Alert existAlert = new Alert(Alert.AlertType.ERROR);
                existAlert.setHeaderText("Wrong Username");
                existAlert.setContentText("Username Does not Exist..!");
                existAlert.showAndWait();
                break;
                
            case pattern_not_matched:
                 Alert passAlert = new Alert(Alert.AlertType.ERROR);
                passAlert.setHeaderText("Password not Strong Enough");
                passAlert.setContentText("1)Password should contain atleast 5 characters \n 2)Password should contain atleast 1 uppecase letter \n 3)Password should contain atleast 1 lowercase character \n ");
                passAlert.showAndWait();
                break;
                
            case pass_not_matched:
                Alert notMatchAlert = new Alert(Alert.AlertType.ERROR);
                notMatchAlert.setHeaderText("Confirm Password");
                notMatchAlert.setContentText("New Password and Confirm Password not same..!");
                notMatchAlert.showAndWait();
                break;
                
             case change_pass_success:
                Alert changeAlert = new Alert(Alert.AlertType.INFORMATION);
                changeAlert.setHeaderText("Password Changed");
                changeAlert.setContentText("New Password changed Successfully");
                changeAlert.showAndWait();
                break;   
                
                

            default:
                break;

        }
    }
}
