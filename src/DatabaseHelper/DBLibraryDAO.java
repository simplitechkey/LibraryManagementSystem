/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseHelper;

import BeansPackage.DatabaseSample;
import BeansPackage.IssuedBookObject;
import BeansPackage.ReturnedBookObject;
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
    
    public static void insertBook(int bookId, String bookSubject, String bookBranch, String bookTitle, int accNo, String bookAuthor, String bookPublication,String bookPrice, String bookYear, String bookEditionYear, String bookSupplier, String billNo, String billDate)
   //public static void insertBook(int bookId, String bookSubject, String bookBranch)
    {
        String sql="insert into totalNumberofBooks ( bookId, bookSubject,bookBranch , bookTitle, bookAccNo, bookAuthor, bookPublication, bookPrice,  bookYear,  bookEditionYear,  bookSupplier,  billNo,  billDate) values ('"+bookId+"','"+bookSubject+"','"+bookBranch+"','"+bookTitle+"','"+accNo+"','"+bookAuthor+"','"+bookPublication+"','"+bookPrice+"','"+bookYear+"','"+bookEditionYear+"','"+bookSupplier+"','"+billNo+"','"+billDate+"');";
       // String sql="insert into totalNumberofBooks ( bookId, bookSubject, bookBranch) values ('"+bookId+"','"+bookSubject+"','"+bookBranch+"');";
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
      ObservableList<DatabaseSample> allBooksList=DBLibraryDAO.getBookObjects(rs);
       
      return allBooksList;
      
     }catch(Exception e){
         throw e;
     }
       
}

    private static ObservableList<DatabaseSample> getBookObjects(ResultSet rs) {
        try {
                    
            ObservableList<DatabaseSample> bookList=FXCollections.observableArrayList();
           
            while(rs.next()){
            bookList.add(new DatabaseSample(rs.getInt("bookId"),rs.getString("bookSubject"),rs.getString("bookBranch"),rs.getString("bookTitle"),rs.getInt("bookAccNo"),rs.getString("bookAuthor"),rs.getString("bookPublication"),rs.getString("bookPrice"),rs.getString("bookYear"),rs.getString("bookEditionYear"),rs.getString("bookSupplier"),rs.getString("billNo"),rs.getString("billDate")));
            System.out.println("omkar"+rs.getString("bookBranch"));
            }
             return bookList;
        } catch (SQLException ex) {
            Logger.getLogger(DBLibraryDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    
    }
    public static ObservableList<IssuedBookObject> getAllIssuedBooksRecords() throws Exception{
    
         String sql="select * from tableIssuedBooks";
     try{
      ResultSet rs=DBUtil.dbExecute(sql);
      ObservableList<IssuedBookObject> bookList=FXCollections.observableArrayList();
        while(rs.next()){
            bookList.add(new IssuedBookObject(rs.getInt("bookId"),rs.getString("bookSubject"),rs.getString("bookBranch"),rs.getString("bookTitle"),rs.getInt("bookAccNo"),rs.getString("bookAuthor"),rs.getString("bookPublication"),rs.getString("bookPrice"),rs.getString("bookYear"),rs.getString("bookEditionYear"),rs.getString("bookSupplier"),rs.getString("billNo"),rs.getString("billDate")));
            System.out.println("omkar"+rs.getString("bookBranch"));
            }
             return bookList;
           
     }catch(Exception e){
         throw e;
     }
       
    }
     
     public static ObservableList<ReturnedBookObject> getAllReturnedBooksRecords() throws Exception{
    
         String sql="select * from tableReturnedBooks";
     try{
      ResultSet rs=DBUtil.dbExecute(sql);
      ObservableList<ReturnedBookObject> bookList=FXCollections.observableArrayList();
        while(rs.next()){
            bookList.add(new ReturnedBookObject(rs.getInt("bookId"),rs.getString("bookSubject"),rs.getString("bookBranch"),rs.getString("bookTitle"),rs.getInt("bookAccNo"),rs.getString("bookAuthor"),rs.getString("bookPublication"),rs.getString("bookPrice"),rs.getString("bookYear"),rs.getString("bookEditionYear"),rs.getString("bookSupplier"),rs.getString("billNo"),rs.getString("billDate")));
            System.out.println("omkar"+rs.getString("bookBranch"));
            }
             return bookList;
           
     }catch(Exception e){
         throw e;
     }
       
}
    public static ObservableList<DatabaseSample> searchBookById(int bookId) throws Exception{
        StringBuilder sbuf = new StringBuilder();
    Formatter fmt = new Formatter(sbuf);
    fmt.format("Select * from totalNumberofBooks where bookId = %d ;", bookId);
       
        try{
         ResultSet rs=DBUtil.dbExecute(sbuf.toString());
      ObservableList<DatabaseSample> searchedBooksList=DBLibraryDAO.getBookObjects(rs);
      return searchedBooksList;
    }catch(Exception e){ 
        e.printStackTrace();
        throw e;}
    
}
    public static ObservableList<ReturnedBookObject> searchReturnedBookBookById(int bookId) throws Exception{
        StringBuilder sbuf = new StringBuilder();
    Formatter fmt = new Formatter(sbuf);
    fmt.format("Select * from tableReturnedBooks where bookId = %d ;", bookId);
       
        try{
         ResultSet rs=DBUtil.dbExecute(sbuf.toString());
      ObservableList<ReturnedBookObject> searchedBooksList=FXCollections.observableArrayList();
      while(rs.next()){
         searchedBooksList.add(new ReturnedBookObject(rs.getInt("bookId"),rs.getString("bookSubject"),rs.getString("bookBranch"),rs.getString("bookTitle"),rs.getInt("bookAccNo"),rs.getString("bookAuthor"),rs.getString("bookPublication"),rs.getString("bookPrice"),rs.getString("bookYear"),rs.getString("bookEditionYear"),rs.getString("bookSupplier"),rs.getString("billNo"),rs.getString("billDate")));
      }
      return searchedBooksList;
    }catch(Exception e){ 
        e.printStackTrace();
        throw e;}
    
}
    
     public static ObservableList<IssuedBookObject> searchIssuedBookBookById(int bookId) throws Exception{
        StringBuilder sbuf = new StringBuilder();
    Formatter fmt = new Formatter(sbuf);
    fmt.format("Select * from tableIssuedBooks where bookId = %d ;", bookId);
       
        try{
         ResultSet rs=DBUtil.dbExecute(sbuf.toString());
      ObservableList<IssuedBookObject> searchedBooksList=FXCollections.observableArrayList();
      while(rs.next()){
         searchedBooksList.add(new IssuedBookObject(rs.getInt("bookId"),rs.getString("bookSubject"),rs.getString("bookBranch"),rs.getString("bookTitle"),rs.getInt("bookAccNo"),rs.getString("bookAuthor"),rs.getString("bookPublication"),rs.getString("bookPrice"),rs.getString("bookYear"),rs.getString("bookEditionYear"),rs.getString("bookSupplier"),rs.getString("billNo"),rs.getString("billDate")));
      }
      return searchedBooksList;
    }catch(Exception e){ 
        e.printStackTrace();
        throw e;}
    
}
    
    
    public static void issueBookfromTotalBooks(int bookId) throws Exception {
        
         StringBuilder sbuf = new StringBuilder();
    Formatter fmt = new Formatter(sbuf);
    fmt.format("insert into tableIssuedBooks ( bookId, bookSubject,bookBranch , bookTitle, bookAccNo, bookAuthor, bookPublication, bookPrice,  bookYear,  bookEditionYear,  bookSupplier,  billNo,  billDate) Select bookId, bookSubject,bookBranch , bookTitle, bookAccNo, bookAuthor, bookPublication, bookPrice,  bookYear,  bookEditionYear,  bookSupplier,  billNo,  billDate from totalNumberofBooks where bookId = %d ;", bookId);
       
        try{
            
        DBUtil.dbexcuteQuery(sbuf.toString());
        removeEntryfromReturnedIfAvailable(bookId);
        
    }catch(Exception e){ 
        e.printStackTrace();
        throw e;}
    
    }

    private static void removeEntryfromReturnedIfAvailable(int bookId) {
        try{
            
            StringBuilder sbuf = new StringBuilder();
    Formatter fmt = new Formatter(sbuf);
    fmt.format("Select * from tableReturnedBooks where bookId = %d ;", bookId);
       
        try{
         ResultSet rs=DBUtil.dbExecute(sbuf.toString());
       ObservableList<IssuedBookObject> bookList=FXCollections.observableArrayList();
        if(rs.next()){
            //bookList.add(new IssuedBookObject(rs.getInt("bookId"),rs.getString("bookSubject"),rs.getString("bookBranch"),rs.getString("bookTitle"),rs.getInt("bookAccNo"),rs.getString("bookAuthor"),rs.getString("bookPublication"),rs.getString("bookPrice"),rs.getString("bookYear"),rs.getString("bookEditionYear"),rs.getString("bookSupplier"),rs.getString("billNo"),rs.getString("billDate")));
              StringBuilder deleteStmnt = new StringBuilder();
    Formatter fmtdel = new Formatter(deleteStmnt);
    fmtdel.format("delete from tableReturnedBooks where bookId = %d ;", bookId);
            
            }
            
      
    }catch(Exception e){ 
        e.printStackTrace();
        throw e;}
    
            
        }catch(Exception ex){
            
        }
    }
    
    public static void returnBookfromIssuedBooks(int bookId) throws Exception {
        
         StringBuilder sbuf = new StringBuilder();
    Formatter fmt = new Formatter(sbuf);
    fmt.format("insert into tableReturnedBooks ( bookId, bookSubject,bookBranch , bookTitle, bookAccNo, bookAuthor, bookPublication, bookPrice,  bookYear,  bookEditionYear,  bookSupplier,  billNo,  billDate) Select bookId, bookSubject,bookBranch , bookTitle, bookAccNo, bookAuthor, bookPublication, bookPrice,  bookYear,  bookEditionYear,  bookSupplier,  billNo,  billDate from tableIssuedBooks where bookId = %d ;", bookId);
       
        try{
            
        DBUtil.dbexcuteQuery(sbuf.toString());
        removeEntryfromIssuedIfAvailable(bookId);
        
    }catch(Exception e){ 
        throw e;}
    
    }

    private static void removeEntryfromIssuedIfAvailable(int bookId) {
          
        try{
            
            StringBuilder sbuf = new StringBuilder();
    Formatter fmt = new Formatter(sbuf);
    fmt.format("Select * from tableIssuedBooks where bookId = %d ;", bookId);
       
        try{
         ResultSet rs=DBUtil.dbExecute(sbuf.toString());
       ObservableList<IssuedBookObject> bookList=FXCollections.observableArrayList();
        if(rs.next()){
            //bookList.add(new IssuedBookObject(rs.getInt("bookId"),rs.getString("bookSubject"),rs.getString("bookBranch"),rs.getString("bookTitle"),rs.getInt("bookAccNo"),rs.getString("bookAuthor"),rs.getString("bookPublication"),rs.getString("bookPrice"),rs.getString("bookYear"),rs.getString("bookEditionYear"),rs.getString("bookSupplier"),rs.getString("billNo"),rs.getString("billDate")));
              StringBuilder deleteStmnt = new StringBuilder();
    Formatter fmtdel = new Formatter(deleteStmnt);
    fmtdel.format("delete from tableIssuedBooks where bookId = %d ;", bookId);
            
            }
            
      
    }catch(Exception e){ 
        throw e;}
    
            
        }catch(Exception ex){
            
        }
    }
    
}