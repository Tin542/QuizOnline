/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinnt.question;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import tinnt.utils.DBhelper;

/**
 *
 * @author ADMIN
 */
public class QuestionDAO implements Serializable{
    private List<QuestionDTO> listQuestion;

    public List<QuestionDTO> getListQuestion() {
        return listQuestion;
    }

    public int pagingAllQuestion() 
            throws ClassNotFoundException, NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm =null;
        ResultSet rs = null;
        int countPage = 0;
        try {
            String sql = "select count (question_id) as row "
                    + "from tblQuestion q, tblStatus s "
                    + "where q.statusID = s.statusID ";
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while(rs.next()){
                int total = rs.getInt("row");
                countPage = total / 5;
                if(total % 5 != 0){
                    countPage++;
                }
            }
        }finally {
            if (rs != null){
                rs.close();
            }
            if (stm != null){
                stm.close();
            }
            if (con != null){
                con.close();
            }
        }
        
        return countPage;
    } 
    
    public void getAllQuestion(int index) 
            throws SQLException, ClassNotFoundException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        this.listQuestion = new ArrayList<>();
        
        try {
            String sql = "select question_id, question_content, answer_content, answer_correct, createDate, s.statusName, subjectID "
                    + "from tblQuestion q, tblStatus s "
                    + "where q.statusID = s.statusID "
                    + "order by question_content "
                    + "OFFSET ? ROWS  FETCH NEXT 5 ROWS ONLY";
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, (index - 1) * 5);
            rs = stm.executeQuery();
            while(rs.next()){
                String id = rs.getString("question_id");
                String qContent = rs.getString("question_content");
                String aContent = rs.getString("answer_content");
                String aCorrect = rs.getString("answer_correct");
                Timestamp date = rs.getTimestamp("createDate");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String status = rs.getString("statusName");
                String subID = rs.getString("subjectID");
                QuestionDTO qDTO = new QuestionDTO(id, qContent, aContent, aCorrect, status, sdf.format(date), subID);
                this.listQuestion.add(qDTO);
            }
            
        }finally {
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
    }
    
    public int pagingFindByFilter(String subjectID, String name, String statusName) 
            throws ClassNotFoundException, NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm =null;
        ResultSet rs = null;
        int countPage = 0;
        
        if(subjectID.equals("")){
            subjectID = null;
        }
        if(statusName.equals("")){
            statusName = null;
        }
        if(name.equals("")){
            name = null;
        }else{
            name = "%"+name+"%";
        }
        
        try {
            String sql = "select count (question_id) as row "
                    + "from tblQuestion q, tblStatus s "
                    + "where q.statusID = s.statusID "
                    + "and q.subjectID = ISNULL(? , q.subjectID) "
                    + "and s.statusName = ISNULL(? , s.statusName) "
                    + "and q.question_content like ISNULL(? , q.question_content) ";
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, subjectID);
            stm.setString(2, statusName);
            stm.setString(3, name);
            rs = stm.executeQuery();
            while(rs.next()){
                int total = rs.getInt("row");
                countPage = total / 5;
                if(total % 5 != 0){
                    countPage++;
                }
            }
        }finally {
            if (rs != null){
                rs.close();
            }
            if (stm != null){
                stm.close();
            }
            if (con != null){
                con.close();
            }
        }
        
        return countPage;
    }
    
    public void findByFilter(int index, String subjectID, String name, String status) 
            throws SQLException, ClassNotFoundException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        this.listQuestion = new ArrayList<>();
        
        if(subjectID.equals("")){
            subjectID = null;
        }
        if(status.equals("")){
            status = null;
        }
        if(name.equals("")){
            name = null;
        }else{
            name = "%"+name+"%";
        }
        
        try {
            String sql = "select question_id, question_content, answer_content, answer_correct, createDate, s.statusName, subjectID "
                    + "from tblQuestion q, tblStatus s "
                    + "where q.statusID = s.statusID "
                    + "and q.subjectID = ISNULL(? , q.subjectID) "
                    + "and q.question_content like ISNULL(? , q.question_content) "
                    + "and s.statusName = ISNULL(? , s.statusName) "
                    + "order by question_content "
                    + "OFFSET ? ROWS  FETCH NEXT 5 ROWS ONLY";
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, subjectID);
            stm.setString(2, name);
            stm.setString(3, status);
            stm.setInt(4, (index - 1) * 5);
            rs = stm.executeQuery();
            while(rs.next()){
                String id = rs.getString("question_id");
                String qContent = rs.getString("question_content");
                String aContent = rs.getString("answer_content");
                String aCorrect = rs.getString("answer_correct");
                Timestamp date = rs.getTimestamp("createDate");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String statuss = rs.getString("statusName");
                String subID = rs.getString("subjectID");
                QuestionDTO qDTO = new QuestionDTO(id, qContent, aContent, aCorrect, statuss, sdf.format(date), subID);
                this.listQuestion.add(qDTO);
            }
            
        }finally {
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
    }
    
    public boolean deleteQuestion(String id) 
            throws SQLException, ClassNotFoundException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        
        try {
            String sql = "update tblQuestion set statusID = 'S003' where question_id = ?";
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            int row = stm.executeUpdate();
            if(row > 0){
                result = true;
            }
        } finally {
            if (stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return result;
    }
    
    public boolean editQuestion(String id, String qContent, String aContent, String correctAns, Date createDate, String subID, String status) 
            throws ClassNotFoundException, NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        
        try {
            String sql = "update tblQuestion "
                    + "set question_content = ?, answer_content = ?, answer_correct = ?, createDate = ?, subjectID = ?, statusID = ? "
                    + "where question_id = ?";
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, qContent);
            stm.setString(2, aContent);
            stm.setString(3, correctAns);
            stm.setTimestamp(4, new Timestamp(createDate.getTime()));
            stm.setString(5, subID);
            stm.setString(6, status);
            stm.setString(7, id);
            
            int row = stm.executeUpdate();
            if(row > 0){
                result = true;
            }
        } finally {
            if (stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return result;
    }
    
    public boolean createQuestion(String id, String qContent,String aContent, String correctAns, Date createDate, String subID, String status) 
            throws ClassNotFoundException, NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        
        try {
            String sql = "insert into tblQuestion(question_id, question_content, answer_content, answer_correct, createDate, subjectID, statusID) "
                    + "values(?,?,?,?,?,?,?)";
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            stm.setString(2, qContent);
            stm.setString(3, aContent);
            stm.setString(4, correctAns);
            stm.setTimestamp(5, new Timestamp(createDate.getTime()));
            stm.setString(6, subID);
            stm.setString(7, status);
            
            int row = stm.executeUpdate();
            if(row > 0){
                result = true;
            }
        } finally {
            if (stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return result;
    }
    
    public void getQuestionForQuiz(int noOfQuest, String subjectID) 
            throws SQLException, ClassNotFoundException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        this.listQuestion = new ArrayList<>();
        
        try {
            String sql = "select top "+noOfQuest+" question_id, question_content, answer_content, answer_correct, statusID "
                    + "from tblQuestion "
                    + "where subjectID = ? and statusID = 'S001' "
                    + "order by NEWID() ";
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, subjectID);
            rs = stm.executeQuery();
            while(rs.next()){
                String id = rs.getString("question_id");
                String qContent = rs.getString("question_content");
                String aCorrect = rs.getString("answer_correct");
                
                QuestionDTO qDTO = new QuestionDTO(id, qContent, qContent, aCorrect);
                listQuestion.add(qDTO);
            }
        } finally {
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
    }
    
    public QuestionDTO getQuestionByID(String id) 
            throws SQLException, ClassNotFoundException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        QuestionDTO qDTO = null;
        
        try {
            String sql = "select question_id, question_content, answer_content, answer_correct, createDate, s.statusName, subjectID "
                    + "from tblQuestion q, tblStatus s "
                    + "where q.statusID = s.statusID "
                    + "and question_id = ? "
                    + "order by question_content ";
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            if(rs.next()){
                String qid = rs.getString("question_id");
                String qContent = rs.getString("question_content");
                String aContent = rs.getString("answer_content");
                String aCorrect = rs.getString("answer_correct");
                Timestamp date = rs.getTimestamp("createDate");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String status = rs.getString("statusName");
                String subID = rs.getString("subjectID");
                qDTO = new QuestionDTO(qid, qContent, aContent, aCorrect, status, sdf.format(date), subID);
            }
            
        }finally {
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return qDTO;
    }
}
