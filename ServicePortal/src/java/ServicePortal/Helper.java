/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ServicePortal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author cb-raghu
 */
public class Helper {
    
    public static Connection getConnection() throws SQLException,ClassNotFoundException{      
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ServicePortal", "root", "");       
        return con;
    }
    
    public static Login getLoginDeatils(String emailId) throws SQLException,ClassNotFoundException{
        Connection con =  Helper.getConnection();
        PreparedStatement ps = con.prepareStatement("Select * from login where email = ?");
        ps.setString(1, emailId);        
        ResultSet rs =  ps.executeQuery();
        Login login = null;
        if(rs != null && rs.next())
        {
            login = new Login(rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"), "", rs.getString("address"));
        }  
        
        return login;
    }
}


