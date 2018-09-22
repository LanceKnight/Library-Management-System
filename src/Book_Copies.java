/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yunchao
 */
import java.io.File;
import java.util.Scanner;
import java.sql.*;

public class Book_Copies {
    public static void initiate(Connection conn) {
        try{
            
            File file = new File("initialData/book_copies.csv");
            Scanner input = new Scanner(file);
            System.out.println("Does it exist?"+ file.exists());
            input.next();
            input.next();
            input.next();
            while(input.hasNext()){
                String book_id = input.next();
                String branch_id = input.next();
                int no_of_copies = Integer.parseInt(input.next());
                //System.out.println("book_id: " + book_id +"' branch_id:" + branch_id + " no_of_copies: " + no_of_copies);
                DBcommander.command("insert into book values('" + book_id + "', 'title');" , conn);
                DBcommander.command("insert into book_copies values('" + book_id + "','" + branch_id + "','" + no_of_copies + "'); ", conn);
                //DBcommander.command("insert into book_authors values('" + book_id + "', 'author_name', 'fname', 'minit','lname');" , conn);
            }
            
            
            //ResultSet rs = DBquerier.query("SELECT * FROM employee;", conn);
            //String ssn = rs.getString("ssn");
            //System.out.println(ssn);
            input.close();
        }
        catch(Exception e){
            System.out.println("DB-Error in opening book_copies.csv: " + e.getMessage());
        }
    }    
}
