/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddBookEntry;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Omskamate
 */
public class AddBookController implements Initializable {

     @FXML
    private JFXTextField bookBarcodeId;

    @FXML
    private JFXTextField bookname;

    @FXML
    private JFXTextField branch;

    @FXML
    private JFXTextField authorName;

    @FXML
    private JFXTextField subject;

    @FXML
    private JFXTextField price;

    @FXML
    private JFXTextField editionYear;

    @FXML
    private JFXTextField supplier;

    @FXML
    private JFXTextField year;

    @FXML
    private JFXTextField billdate;

    @FXML
    private JFXTextField publication;

    @FXML
    private JFXTextField billnumber;

    @FXML
    private JFXTextField accnumber;
    
    @FXML
    private JFXButton addrecord;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
