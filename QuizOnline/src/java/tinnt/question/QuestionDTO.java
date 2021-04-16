/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinnt.question;

import java.io.Serializable;
import java.util.List;
import tinnt.answer.AnswerDTO;

/**
 *
 * @author ADMIN
 */
public class QuestionDTO implements Serializable{
    private String id; 
    private String question_content;
    private String answer_content;
    private String answer_correct;
    private String status;
    private String createDate;
    private String subjectID;
    
    private List<AnswerDTO> listAnswer;
    private String studentAnswer;

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public List<AnswerDTO> getListAnswer() {
        return listAnswer;
    }

    public void setListAnswer(List<AnswerDTO> listAnswer) {
        this.listAnswer = listAnswer;
    }
    

    public QuestionDTO(String id, String question_content, String answer_content, String answer_correct, String status, String createDate, String subjectID) {
        this.id = id;
        this.question_content = question_content;
        this.answer_content = answer_content;
        this.answer_correct = answer_correct;
        this.status = status;
        this.createDate = createDate;
        this.subjectID = subjectID;
    }

    public QuestionDTO(String id, String question_content, String answer_content, String answer_correct) {
        this.id = id;
        this.question_content = question_content;
        this.answer_content = answer_content;
        this.answer_correct = answer_correct;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }

    public String getAnswer_correct() {
        return answer_correct;
    }

    public void setAnswer_correct(String answer_correct) {
        this.answer_correct = answer_correct;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    

    
}
