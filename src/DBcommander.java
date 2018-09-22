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
public class DBcommander {
    
    
    public static void command(String command, Connection conn){
        ResultSet rs = null;
        
        try{
            
            // Set the current database, if not already set in the getConnection
            // Execute a SQL statement
            Statement stmt = conn.createStatement();
            
            stmt.execute(command);

            // Execute a SQL query using SQL as a String object
            
            
            // Iterate through the result set using ResultSet class's next() method
            // End while(rs.next())

            // Always close the recordset and connection.
            
         
            System.out.println("Command succeeds!!");
            
        }
        catch(Exception e){
            System.out.println("DB-Error in commands: " + e.getMessage());
        }
        
    }
    
    static void newln() {
        System.out.println();
    }
    
}
