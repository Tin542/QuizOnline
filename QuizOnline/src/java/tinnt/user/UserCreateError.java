/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinnt.user;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class UserCreateError implements Serializable{
    private String emailWrongFormatErr;
    private String confirmNotMatched;
    private String fullNameLengthErr;
    private String emailLengthErr;
    private String passwordLengthErr;
    private String emailIsExisted;
    private String emptyInputErr;

    public String getEmailWrongFormatErr() {
        return emailWrongFormatErr;
    }

    public void setEmailWrongFormatErr(String emailWrongFormatErr) {
        this.emailWrongFormatErr = emailWrongFormatErr;
    }

    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    public void setConfirmNotMatched(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }

    public String getFullNameLengthErr() {
        return fullNameLengthErr;
    }

    public void setFullNameLengthErr(String fullNameLengthErr) {
        this.fullNameLengthErr = fullNameLengthErr;
    }

    public String getEmailLengthErr() {
        return emailLengthErr;
    }

    public void setEmailLengthErr(String emailLengthErr) {
        this.emailLengthErr = emailLengthErr;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public String getEmailIsExisted() {
        return emailIsExisted;
    }

    public void setEmailIsExisted(String emailIsExisted) {
        this.emailIsExisted = emailIsExisted;
    }

    public String getEmptyInputErr() {
        return emptyInputErr;
    }

    public void setEmptyInputErr(String emptyInputErr) {
        this.emptyInputErr = emptyInputErr;
    }
    
    
}
