/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansPackage;

/**
 *
 * @author Omskamate
 */
public class StudentItem {
   String enrollmentId;
   String studentName;
   String studentClass;
   
   public StudentItem(String enrollmentId, String studentName, String studentClass){
       this.enrollmentId=enrollmentId;
       this.studentName=studentName;
       this.studentClass=studentClass;
   }

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(String enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }
   
     
}
