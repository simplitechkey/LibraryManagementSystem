/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bvjiniolibrarymanagement;

import DatabaseHelper.DBLibraryDAO;
import DatabaseHelper.DBUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Omskamate
 */
public class BvjinioLibraryManagement extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
         String sql1="delete from tableReturnedBooks";
                String sql2="insert into tableReturnedBooks SELECT totalNumberofBooks.bookId, totalNumberofBooks.bookSubject,totalNumberofBooks.bookBranch , totalNumberofBooks.bookTitle, totalNumberofBooks.bookAccNo, totalNumberofBooks.bookAuthor , totalNumberofBooks.bookPublication, totalNumberofBooks.bookPrice,  totalNumberofBooks.bookYear,  totalNumberofBooks.bookEditionYear,  totalNumberofBooks.bookSupplier, totalNumberofBooks.billNo,  totalNumberofBooks.billDate FROM totalNumberofBooks LEFT JOIN tableIssuedBooks ON totalNumberofBooks.bookId = tableIssuedBooks.bookId where tableIssuedBooks.bookId is null;";
        DBUtil.dbexcuteQuery(sql1);
                DBUtil.dbexcuteQuery(sql2);       
      DBLibraryDAO.getAllRecords();
      DBLibraryDAO.getAllIssuedBooksRecords();
      DBLibraryDAO.getAllReturnedBooksRecords();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
