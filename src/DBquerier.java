
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yunchao
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBquerier {
    public static ResultSet query(String command, Connection conn){
        ResultSet rs = null;
        String ssn;
        String firstName;
        String lastName;
        String address;
        int dno;
        int linect = 0;
        try{
            
            // Set the current database, if not already set in the getConnection
            // Execute a SQL 
            
            Statement stmt = conn.createStatement();
            stmt.execute("use library;");
            // Execute a SQL query using SQL as a String object
            rs = stmt.executeQuery(command);
            
            // Iterate through the result set using ResultSet class's next() method
            // End while(rs.next())

            // Always close the recordset and connection.

            System.out.println("Query succeeds!!");
            
        }
        catch(Exception e){
            System.out.println("Querier: " + e.getMessage());
        }
        return rs;
    }


 
}
