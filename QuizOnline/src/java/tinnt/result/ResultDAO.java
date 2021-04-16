/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinnt.result;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import tinnt.utils.DBhelper;

/**
 *
 * @author ADMIN
 */
public class ResultDAO implements Serializable{
    
    private List<ResultDTO> listResult;

    public List<ResultDTO> getListResult() {
        return listResult;
    }
    
    public boolean insertResult(String id, String email, String name,  String subJD, float grade, int noOfCorrecr, String createDate) 
            throws SQLException, ClassNotFoundException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            String sql = "insert into tblResult (resultID, email, name, subjectID, noOfCorrect, grade, dateOfCreate) "
                    + "values(?, ?, ?, ?, ?, ?, ?)";
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            stm.setString(2, email);
            stm.setString(3, name);
            stm.setString(4, subJD);
            stm.setInt(5, noOfCorrecr);
            stm.setFloat(6, grade);
            stm.setString(7, createDate);
            int row = stm.executeUpdate();
            if(row > 0){
                result = true;
            }
        } finally{
            if (stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return result;
    }
    
    public String getLastResultID(String email) 
            throws ClassNotFoundException, NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String id = null;
        try {
            String sql = "select resultID "
                    + "from tblResult "
                    + "where dateOfCreate = (select MAX(dateOfCreate) from tblResult where email = ?)";
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if(rs.next()){
                id = rs.getString("resultID");
            }
        }finally{
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
        return id;
    }
    
    public int pagingAllHistoryForAdmin() 
            throws ClassNotFoundException, NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm =null;
        ResultSet rs = null;
        int countPage = 0;
        try {
            String sql = "select count(resultID)as row "
                    + "from tblResult ";
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
    
    public void getAllHistoryForAdmin(int index) 
            throws SQLException, ClassNotFoundException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        this.listResult = new ArrayList<>();
        
        try {
            String sql = "select resultID, email, name, subjectID, noOfCorrect, grade, dateOfCreate "
                    + "from tblResult "
                    + "order by dateOfCreate "
                    + "OFFSET ? ROWS  FETCH NEXT 5 ROWS ONLY";
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, (index - 1) * 5);
            rs = stm.executeQuery();
            while(rs.next()){
                String id = rs.getString("resultID");
                String email = rs.getString("email");
                String name = rs.getString("name");
                String subjectID = rs.getString("subjectID");
                int noOfCorrect = rs.getInt("noOfCorrect");
                float grade = rs.getFloat("grade");
                Timestamp doc = rs.getTimestamp("dateOfCreate");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                
                ResultDTO rDTO = new ResultDTO(id, email, name, subjectID, grade, noOfCorrect, sdf.format(doc));
                this.listResult.add(rDTO);
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
    
    public void getAllHistoryForStudent(String emailStudent, int index) 
            throws SQLException, ClassNotFoundException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        this.listResult = new ArrayList<>();
        
        try {
            String sql = "select resultID, email, name, subjectID, noOfCorrect, grade, dateOfCreate "
                    + "from tblResult "
                    + "where email = ? "
                    + "order by dateOfCreate "
                    + "OFFSET ? ROWS  FETCH NEXT 5 ROWS ONLY";
            System.out.println(sql);
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, emailStudent);
            stm.setInt(2, (index - 1) * 5);
            rs = stm.executeQuery();
            while(rs.next()){
                String id = rs.getString("resultID");
                String email = rs.getString("email");
                String name = rs.getString("name");
                String subjectID = rs.getString("subjectID");
                int noOfCorrect = rs.getInt("noOfCorrect");
                float grade = rs.getFloat("grade");
                Timestamp doc = rs.getTimestamp("dateOfCreate");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                
                ResultDTO rDTO = new ResultDTO(id, email, name, subjectID, grade, noOfCorrect, sdf.format(doc));
                this.listResult.add(rDTO);
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
    
    public int pagingAllHistoryForStudent(String email) 
            throws ClassNotFoundException, NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm =null;
        ResultSet rs = null;
        int countPage = 0;
        try {
            String sql = "select count(resultID)as row "
                    + "from tblResult "
                    + "where email = ? ";
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
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
    
    public int pagingSearchByEmailAndSubName(String email, String subName) 
            throws ClassNotFoundException, NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm =null;
        ResultSet rs = null;
        int countPage = 0;
        try {
            String sql = "select count(resultID)as row "
                    + "from tblResult r, tblSubject s "
                    + "where r.subjectID = s.subjectID "
                    + "and r.email = ? and s.subjectName = ?";
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, subName);
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
    
    public int pagingSearchBySubName(String subName) 
            throws ClassNotFoundException, NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm =null;
        ResultSet rs = null;
        int countPage = 0;
        try {
            String sql = "select count(resultID)as row "
                    + "from tblResult r, tblSubject s "
                    + "where r.subjectID = s.subjectID "
                    + "and s.subjectName = ?";
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, subName);
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
    
    public void searchByEmailAndSubName(int index, String email, String subName) 
            throws ClassNotFoundException, NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        this.listResult = new ArrayList<>();
        
        try {
            String sql = "select resultID, email, name, r.subjectID, noOfCorrect, grade, dateOfCreate "
                    + "from tblResult r, tblSubject s "
                    + "where r.subjectID = s.subjectID "
                    + "and r.email = ? and s.subjectName = ? "
                    + "order by dateOfCreate "
                    + "OFFSET ? ROWS  FETCH NEXT 5 ROWS ONLY";
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, subName);
            stm.setInt(3, (index - 1) * 5);
            rs = stm.executeQuery();
            while(rs.next()){
                String id = rs.getString("resultID");
                String mail = rs.getString("email");
                String name = rs.getString("name");
                String subID = rs.getString("subjectID");
                int noOfCorrect = rs.getInt("noOfCorrect");
                float grade = rs.getFloat("grade");
                Timestamp doc = rs.getTimestamp("dateOfCreate");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                
                ResultDTO rDTO = new ResultDTO(id, mail, name, subID, grade, noOfCorrect, sdf.format(doc));
                this.listResult.add(rDTO);
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
    
    public void searchBySubName(int index, String subName) 
            throws ClassNotFoundException, NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        this.listResult = new ArrayList<>();
        
        try {
            String sql = "select resultID, email, name, r.subjectID, noOfCorrect, grade, dateOfCreate "
                    + "from tblResult r, tblSubject s "
                    + "where r.subjectID = s.subjectID "
                    + "and s.subjectName = ? "
                    + "order by dateOfCreate "
                    + "OFFSET ? ROWS  FETCH NEXT 5 ROWS ONLY";
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, subName);
            stm.setInt(2, (index - 1) * 5);
            rs = stm.executeQuery();
            while(rs.next()){
                String id = rs.getString("resultID");
                String mail = rs.getString("email");
                String sname = rs.getString("name");
                String subID = rs.getString("subjectID");
                int noOfCorrect = rs.getInt("noOfCorrect");
                float grade = rs.getFloat("grade");
                Timestamp doc = rs.getTimestamp("dateOfCreate");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                
                ResultDTO rDTO = new ResultDTO(id, mail, sname, subID, grade, noOfCorrect, sdf.format(doc));
                this.listResult.add(rDTO);
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
}
