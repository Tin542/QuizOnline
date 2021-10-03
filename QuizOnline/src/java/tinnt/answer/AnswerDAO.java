/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinnt.answer;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import tinnt.utils.DBhelper;

/**
 *
 * @author ADMIN
 */
public class AnswerDAO implements Serializable{
    private List<AnswerDTO> listAnswer;

    public List<AnswerDTO> getListAnswer() {
        return listAnswer;
    }
    
    public void getAnswerByQuesID(String QuesID) 
            throws ClassNotFoundException, NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        this.listAnswer = new ArrayList<>();
        
        try {
            String sql = "select answer_id, answer_content, type, question_id "
                    + "from tblAnswer "
                    + "where question_id = ?";
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, QuesID);
            rs = stm.executeQuery();
            while(rs.next()){
                String id = rs.getString("answer_id");
                String content = rs.getString("answer_content");
                int type = rs.getInt("type");
                String quesID = rs.getString("question_id");
                AnswerDTO aDTO = new AnswerDTO(quesID, content, type, quesID);
                this.listAnswer.add(aDTO);
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

    public boolean insertAnswer(String answerID, String ansContent, int type, String questionID) 
            throws ClassNotFoundException, NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        
        try {
            String sql = "insert into tblAnswer (answer_id, answer_content, question_id, type) "
                    + "values(?, ?, ?, ?)";
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, answerID);
            stm.setString(2, ansContent);
            stm.setInt(4, type);
            stm.setString(3, questionID);
            
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
    
    public boolean editAnswer(String ansContent, int type, String ansID) 
            throws ClassNotFoundException, NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        
        try {
            String sql = "update tblAnswer set answer_content = ?, type = ? where answer_id = ?";
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, ansContent);
            stm.setInt(2, type);
            stm.setString(3, ansID);
            
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

    // cai nay thang vuong moi them de test
}
