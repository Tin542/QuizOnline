/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinnt.subject;

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
public class SubjectDAO implements Serializable{
    List<SubjectDTO> listSubject;

    public List<SubjectDTO> getListSubject() {
        return listSubject;
    }
    public void getAllSubject() 
            throws ClassNotFoundException, NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            String sql = "select subjectID, subjectName, time, noOfQuestion from tblSubject";
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while(rs.next()){
                String subID = rs.getString("subjectID");
                String subName = rs.getString("subjectName");
                int time = rs.getInt("time");
                int noOfQuestion = rs.getInt("noOfQuestion");
                SubjectDTO sDTO = new SubjectDTO(subID, subName, time, noOfQuestion);
                if(this.listSubject == null){
                    this.listSubject = new ArrayList<>();
                }
                this.listSubject.add(sDTO);
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
    public SubjectDTO getSubjectByID(String id) 
            throws SQLException, ClassNotFoundException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        SubjectDTO dto = null;
        try {
            String sql = "select subjectID, subjectName, time, noOfQuestion from tblSubject where subjectID = ?";
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            if(rs.next()){
                String subID = rs.getString("subjectID");
                String subName = rs.getString("subjectName");
                int time = rs.getInt("time");
                int noOfQuestion = rs.getInt("noOfQuestion");
                dto = new SubjectDTO(subID, subName, time, noOfQuestion);
                
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
        return dto;
    }
    
    public boolean insertSubject(String id, String name, int time, int noOfQues) 
            throws SQLException, ClassNotFoundException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        
        try {
            String sql = "insert into tblSubject (subjectID, subjectName, time, noOfQuestion) "
                    + "values (?, ?, ?, ?)";
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            stm.setString(2, name);
            stm.setInt(3, time);
            stm.setInt(4, noOfQues);
            int row = stm.executeUpdate();
            if(row > 0){
                result = true;
            }
        } finally {
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return result;
    }
}
