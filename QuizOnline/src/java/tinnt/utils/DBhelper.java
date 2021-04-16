/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinnt.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author ADMIN
 */
public class DBhelper implements Serializable{
    public static Connection makeConnection() throws ClassNotFoundException,NamingException, SQLException{
         //1.get current context
      Context context = new InitialContext();
      //2.get server context
      Context tomcatContext = (Context) context.lookup("java:comp/env");
      //3.get data source
      DataSource ds = (DataSource) tomcatContext.lookup("DS02");
      //4. make connection
      Connection con = ds.getConnection();
      
      return con;
    }
}
