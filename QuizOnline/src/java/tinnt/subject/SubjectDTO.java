/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinnt.subject;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class SubjectDTO implements Serializable{
    private String subjectID;
    private String subjectName;
    private int timeRemain;
    private int noOfQuestion;

    public SubjectDTO() {
    }

    public SubjectDTO(String subjectID, String subjectName, int timeRemain, int noOfQuestion) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.timeRemain = timeRemain;
        this.noOfQuestion = noOfQuestion;
    }

    public int getNoOfQuestion() {
        return noOfQuestion;
    }

    public void setNoOfQuestion(int noOfQuestion) {
        this.noOfQuestion = noOfQuestion;
    }

    public int getTimeRemain() {
        return timeRemain;
    }

    public void setTimeRemain(int timeRemain) {
        this.timeRemain = timeRemain;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    
}
