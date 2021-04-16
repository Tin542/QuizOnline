/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinnt.result;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class ResultDTO implements Serializable{
    private String resultID;
    private String email;
    private String name;
    private String subjectID;
    private float grade;
    private int noOfCorrect;
    private String  dateOfCreate;

    public ResultDTO(String resultID, String email, String name, String subjectID, float grade, int noOfCorrect, String dateOfCreate) {
        this.resultID = resultID;
        this.email = email;
        this.name = name;
        this.subjectID = subjectID;
        this.grade = grade;
        this.noOfCorrect = noOfCorrect;
        this.dateOfCreate = dateOfCreate;
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResultID() {
        return resultID;
    }

    public void setResultID(String resultID) {
        this.resultID = resultID;
    }

    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public int getNoOfCorrect() {
        return noOfCorrect;
    }

    public void setNoOfCorrect(int noOfCorrect) {
        this.noOfCorrect = noOfCorrect;
    }

    public String getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(String dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    
    
}
