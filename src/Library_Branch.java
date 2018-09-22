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
import java.sql.Connection;
import java.util.Scanner;

public class Library_Branch {
  public static void initiate(Connection conn) {
        try{
            DBcommander.command("use library;", conn);
            File file = new File("initialData/library_branch.csv");
            Scanner input = new Scanner(file);
            System.out.println("Does it exist?"+ file.exists());
            input.nextLine();
             
             
            while(input.hasNextLine()){
                String line = input.nextLine();
                String[] split=line.split("\t");
                String branch_id = split[0];
                //System.out.println(book_id);
                String branch_name = split[1];
                String address = split[2];
                //System.out.println(split.length);
                System.out.println("book_id: " + branch_id +" brach_name:" + branch_name + " address: " + address);
                System.out.println("Cool");
                DBcommander.command("insert into library_branch values('" + branch_id + "', '" + branch_name +"', '"+ address + "');",conn);
                //DBcommander.command("update book set title = '" + title + "' where book_id = '" + book_id +"';", conn);
            }
            input.close();
        }
        catch(Exception e){
            System.out.println("DB-Error in opening library_branch.csv: " + e.getMessage());
        }       
         
    } 
}
