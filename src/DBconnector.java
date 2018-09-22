/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yunchao
 */
import java.sql.*;

public class DBconnector {
    static Connection conn = null;
    // Initialize variables for fields by data type  
    
    public static Connection connect(String user, String pw){
                
        try {
            // Create a connection to the local MySQL server
 
            // Create a connection to the local MySQL server, with the NO database selected.
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", user, pw);
    
            // Create a SQL statement object and execute the query.
            Statement stmt = conn.createStatement();
            DBcommander.command("use library", conn);
        } 
        catch(SQLException ex) {
            System.out.println("DB:Error in connection: " + ex.getMessage());
        }
        return conn;
    }        
}
