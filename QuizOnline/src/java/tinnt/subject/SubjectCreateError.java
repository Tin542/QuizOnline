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
public class SubjectCreateError implements Serializable{
    String subIdlengthErr;
    String subIDExist;
    String subNameLengthErr;

    public String getSubIdlengthErr() {
        return subIdlengthErr;
    }

    public void setSubIdlengthErr(String subIdlengthErr) {
        this.subIdlengthErr = subIdlengthErr;
    }

    public String getSubIDExist() {
        return subIDExist;
    }

    public void setSubIDExist(String subIDExist) {
        this.subIDExist = subIDExist;
    }

   

    public String getSubNameLengthErr() {
        return subNameLengthErr;
    }

    public void setSubNameLengthErr(String subNameLengthErr) {
        this.subNameLengthErr = subNameLengthErr;
    }
    
}
