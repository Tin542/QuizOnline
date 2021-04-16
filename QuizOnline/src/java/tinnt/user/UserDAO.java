/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinnt.user;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import tinnt.utils.DBhelper;

/**
 *
 * @author ADMIN
 */
public class UserDAO implements Serializable{
    
    public boolean createAccount (String email, String password, String name) 
            throws SQLException, ClassNotFoundException, NamingException{
        
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        
        try {
            String sql = "insert into tblUser(email, password, name, role, statusID) "
                    + "values(?,?,?,'Student','S001')";
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            stm.setString(3, name);
            
            int row = stm.executeUpdate();
            if(row > 0){
                result = true;
            }
            
        } finally {
            if (stm != null)
                stm.close();
            if (con != null)
                con.close();
        }
        return result;
    }
            
    public UserDTO checkLogin(String email, String password) 
            throws SQLException, ClassNotFoundException, NamingException{
        
        UserDTO user = null;
        Connection con  = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            String sql = "select u.email, u.password, u.name, u.role, s.statusName "
                    + "from tblUser u, tblStatus s "
                    + "where u.statusID = s.statusID "
                    + "and u.email = ? and u.password = ? and u.statusID = 'S001'";
            con = DBhelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if(rs.next()){
                String name = rs.getString("name");
                String role = rs.getString("role");
                String status = rs.getString("statusName");
                user = new UserDTO(email, password, name, role, status);
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
        return user;
    }
}
