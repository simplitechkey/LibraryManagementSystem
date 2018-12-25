/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseHelper;

import BeansPackage.DatabaseSample;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author omkarkamate
 */
public class DBLibraryDAO {
    
    //public static void insertBook(int bookId, String bookSubject, String branchOfBook, String bookTitle, int accNo, String bookAuthor, String bookPublication,float bookPrice, String bookYear, String bookEditionYear, String bookSupplier, String billNo, String billDate)
    public static void insertBook(int bookId, String bookSubject, String bookBranch)
    {
        //String sql="insert into LibraryDb ( bookId, bookSubject, branchOfBook, bookTitle, accNo, bookAuthor, bookPublication, bookPrice,  bookYear,  bookEditionYear,  bookSupplier,  billNo,  billDate) values ('"+bookId+"','"+bookSubject+"','"+branchOfBook+"','"+bookTitle+"','"+accNo+"','"+bookAuthor+"','"+bookPublication+"','"+bookPrice+"','"+bookYear+"','"+bookEditionYear+"','"+bookSupplier+"','"+billNo+"','"+billDate+"')";
        String sql="insert into totalNumberofBooks ( bookId, bookSubject, bookBranch) values ('"+bookId+"','"+bookSubject+"','"+bookBranch+"');";
        try{
            DBUtil.dbexcuteQuery(sql);
        }catch(Exception e){
            
        }
    }
    
    public static void updateBook(int bookId, String bookSubject)
    {
        //String sql="insert into LibraryDb ( bookId, bookSubject, branchOfBook, bookTitle, accNo, bookAuthor, bookPublication, bookPrice,  bookYear,  bookEditionYear,  bookSupplier,  billNo,  billDate) values ('"+bookId+"','"+bookSubject+"','"+branchOfBook+"','"+bookTitle+"','"+accNo+"','"+bookAuthor+"','"+bookPublication+"','"+bookPrice+"','"+bookYear+"','"+bookEditionYear+"','"+bookSupplier+"','"+billNo+"','"+billDate+"')";
        String sql="update totalNumberofBooks set bookSubject = '"+bookSubject+"'where bookId='"+bookId+"'";
        try{
            DBUtil.dbexcuteQuery(sql);
        }catch(Exception e){
        }
    }
    
    public static void deleteBookbyID(int bookId)
    {
        //String sql="insert into LibraryDb ( bookId, bookSubject, branchOfBook, bookTitle, accNo, bookAuthor, bookPublication, bookPrice,  bookYear,  bookEditionYear,  bookSupplier,  billNo,  billDate) values ('"+bookId+"','"+bookSubject+"','"+branchOfBook+"','"+bookTitle+"','"+accNo+"','"+bookAuthor+"','"+bookPublication+"','"+bookPrice+"','"+bookYear+"','"+bookEditionYear+"','"+bookSupplier+"','"+billNo+"','"+billDate+"')";
  
  StringBuilder sbuf = new StringBuilder();
Formatter fmt = new Formatter(sbuf);
fmt.format("delete from totalNumberofBooks where bookId = %d ;", bookId);
  try{
            DBUtil.dbexcuteQuery(sbuf.toString());
        }catch(Exception e){
            
        }
    }

public static ObservableList<DatabaseSample> getAllRecords() throws Exception{
    
         String sql="select * from totalNumberofBooks";
     try{
      ResultSet rs=DBUtil.dbExecute(sql);
      ObservableList<DatabaseSample> allBooksList=FXCollections.observableArrayList();
      while(rs.next()){
            allBooksList.add(new DatabaseSample(rs.getInt("bookId"),rs.getString("bookSubject"),rs.getString("bookBranch")));
            System.out.println("omkar234"+rs.getString("bookBranch"));
            }
      return allBooksList;
      
     }catch(Exception e){
         e.printStackTrace();
         throw e;
     }
       
}

    private static ObservableList<DatabaseSample> getBookObjects(ResultSet rs) {
        try {
                    
            ObservableList<DatabaseSample> bookList=FXCollections.observableArrayList();
           
            while(rs.next()){
            bookList.add(new DatabaseSample(rs.getInt("bookId"),rs.getString("bookSubject"),rs.getString("bookBranch")));
            System.out.println("omkar"+rs.getString("bookBranch"));
            }
             return bookList;
        } catch (SQLException ex) {
            Logger.getLogger(DBLibraryDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    
    }
}
    
