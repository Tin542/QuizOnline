/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinnt.answer;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class AnswerDTO implements Serializable{
    private String answer_id;
    private String answer_content;
    private int type;
    private String question_id;

    public AnswerDTO(String answer_id, String answer_content, int type, String question_id) {
        this.answer_id = answer_id;
        this.answer_content = answer_content;
        this.type = type;
        this.question_id = question_id;
    }

    public String getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(String answer_id) {
        this.answer_id = answer_id;
    }

    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }

    public int isType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }
    
    
}
