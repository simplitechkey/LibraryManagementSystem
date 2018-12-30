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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class IssuedBookObject {
    String bookId="";
    String bookSubject="";
    String bookBranch="";
    String bookTitle="";
    String accountNumber="";
    String bookAuthor="";
    String bookPublication="";
    String bookPrice="";
    String bookYear="";
    String bookEditionYear="";
    String bookSupplier="";
    String billNo="";
    String billDate="";


  /*  public DatabaseSample(String bookId, String bookSubject, String bookBranch) {
        
        this.bookId = bookId;
        this.bookSubject = bookSubject;
        this.bookBranch = bookBranch;
       
    }*/
    
 public IssuedBookObject(String bookId, String bookSubject, String bookBranch, String bookTitle, String accountNumber, String bookAuthor, String bookPublication,String bookPrice, String bookYear, String bookEditionYear, String bookSupplier, String billNo, String billDate) {
        
        this.bookId = bookId;
        this.bookSubject = bookSubject;
        this.bookBranch = bookBranch;
        this.bookTitle = bookTitle;
        this.accountNumber = accountNumber;
        this.bookAuthor = bookAuthor;
        this.bookPublication = bookPublication;
        this.bookPrice=bookPrice;
        this.bookYear = bookYear;
        this.bookEditionYear = bookEditionYear;
        this.bookSupplier = bookSupplier;
        this.billNo = billNo;
        this.billDate = billDate;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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
    
    public String getBookPrice(){
        return bookPrice;
    }
    public void setBookPrice(String bookPrice){
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

    public String getBillNumber() {
        return billNo;
    }

    public void setBillNumber(String billNo) {
        this.billNo = billNo;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }
}