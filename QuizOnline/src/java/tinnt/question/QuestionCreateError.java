/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinnt.question;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class QuestionCreateError implements Serializable{
    private String quesIDLenghtErr;
    private String quesIDExist;
    private String quesContwentLenghtErr;
    private String ansALengthErr;
    private String ansBLenghtErr;
    private String ansCLenghtErr;
    private String ansDLenghtErr;
    private String ansCorrectNotMatch;

    public String getAnsCorrectNotMatch() {
        return ansCorrectNotMatch;
    }

    public void setAnsCorrectNotMatch(String ansCorrectNotMatch) {
        this.ansCorrectNotMatch = ansCorrectNotMatch;
    }

    public String getQuesIDLenghtErr() {
        return quesIDLenghtErr;
    }

    public void setQuesIDLenghtErr(String quesIDLenghtErr) {
        this.quesIDLenghtErr = quesIDLenghtErr;
    }

    public String getQuesIDExist() {
        return quesIDExist;
    }

    public void setQuesIDExist(String quesIDExist) {
        this.quesIDExist = quesIDExist;
    }

    public String getQuesContwentLenghtErr() {
        return quesContwentLenghtErr;
    }

    public void setQuesContwentLenghtErr(String quesContwentLenghtErr) {
        this.quesContwentLenghtErr = quesContwentLenghtErr;
    }

    public String getAnsALengthErr() {
        return ansALengthErr;
    }

    public void setAnsALengthErr(String ansALengthErr) {
        this.ansALengthErr = ansALengthErr;
    }

    public String getAnsBLenghtErr() {
        return ansBLenghtErr;
    }

    public void setAnsBLenghtErr(String ansBLenghtErr) {
        this.ansBLenghtErr = ansBLenghtErr;
    }

    public String getAnsCLenghtErr() {
        return ansCLenghtErr;
    }

    public void setAnsCLenghtErr(String ansCLenghtErr) {
        this.ansCLenghtErr = ansCLenghtErr;
    }

    public String getAnsDLenghtErr() {
        return ansDLenghtErr;
    }

    public void setAnsDLenghtErr(String ansDLenghtErr) {
        this.ansDLenghtErr = ansDLenghtErr;
    }
    
    
}
