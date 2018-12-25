/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansPackage;

/**
 *
 * @author omkarkamate
 */


public class DatabaseSample {
    int bookId=0;
    String bookSubject="";
    String bookBranch="";
    String bookTitle;
    int accNo;
    String bookAuthor;
    String bookPublication;
    float bookPrice;
    String bookYear;
    String bookEditionYear;
    String bookSupplier;
    String billNo;
    String billDate;


    public DatabaseSample(int bookId, String bookSubject, String bookBranch) {
        
        this.bookId = bookId;
        this.bookSubject = bookSubject;
        this.bookBranch = bookBranch;
       
    }
    
 /*public DatabaseSample(int bookId, String bookSubject, String branchOfBook, String bookTitle, int accNo, String bookAuthor, String bookPublication,float bookPrice, String bookYear, String bookEditionYear, String bookSupplier, String billNo, String billDate) {
        
        this.bookId = bookId;
        this.bookSubject = bookSubject;
        this.branchOfBook = branchOfBook;
        this.bookTitle = bookTitle;
        this.accNo = accNo;
        this.bookAuthor = bookAuthor;
        this.bookPublication = bookPublication;
        this.bookYear = bookYear;
        this.bookEditionYear = bookEditionYear;
        this.bookSupplier = bookSupplier;
        this.billNo = billNo;
        this.billDate = billDate;
    }*/

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookSubject() {
        return bookSubject;
    }

    public void setBookSubject(String bookSubject) {
        this.bookSubject = bookSubject;
    }

    public String getBookBranch() {
        return bookBranch;
    }

    public void setBookBranch(String bookBranch) {
        this.bookBranch = bookBranch;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getAccNo() {
        return accNo;
    }

    public void setAccNo(int accNo) {
        this.accNo = accNo;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPublication() {
        return bookPublication;
    }

    public void setBookPublication(String bookPublication) {
        this.bookPublication = bookPublication;
    }
    
    public float getBookPrice(){
        return bookPrice;
    }
    public void setBookPrice(float bookPrice){
        this.bookPrice=bookPrice;
    }

    public String getBookYear() {
        return bookYear;
    }

    public void setBookYear(String bookYear) {
        this.bookYear = bookYear;
    }

    public String getBookEditionYear() {
        return bookEditionYear;
    }

    public void setBookEditionYear(String bookEditionYear) {
        this.bookEditionYear = bookEditionYear;
    }

    public String getBookSupplier() {
        return bookSupplier;
    }

    public void setBookSupplier(String bookSupplier) {
        this.bookSupplier = bookSupplier;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }
}