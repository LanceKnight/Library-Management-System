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

public class Books_Authors {
    public static void initiate(Connection conn) {
        try{
            File file = new File("initialData/books_authors.csv");
            Scanner input = new Scanner(file);
            System.out.println("Does it exist?"+ file.exists());
            input.nextLine();
                         
            while(input.hasNextLine()){
                String line = input.nextLine();
                String[] split=line.split("\t");
                String book_id = split[0];
                String authors = split[1];
                String title = split[2];
             
                String[] author = authors.split(",");
                
                String[] name;
                String fname ="";
                String lname = "";
                String minit="";
                if( authors.equals("Clarke, Greg")){
                    DBcommander.command("insert into book_authors values('" + book_id + "', '" + "Clark Greg" + "', '1" +"');", conn);
                }
                
                else if(author.length == 1){
                    name = author[0].split(" ");
                    fname = name[0];
                    if(name.length == 3){
                        minit = name[1];
                        lname = name[2];
                    }
                    else if (name.length ==2){
                        minit = "";
                        lname = name[1];
                    }
                    else{
                        fname = "none";
                        lname = "none";
                        minit = "none";
                    }
                    if(author[0].equals("Various") || author[0].equals("The Beatles")){
                        DBcommander.command("insert into book_authors values('" + book_id + "', '" + author[0] + "', '2" +"');", conn);
                    }else{
                        DBcommander.command("insert into book_authors values('" + book_id + "', '" + author[0] + "', '1" +"');", conn);
                    }
                    
                }
                else{//multiple authors
                    
                    for(int i = 0; i < author.length; i ++){
                        name = author[i].split(" ");
                        if(i == 0){// first author in mutiple authors
                            if(name.length == 3){//first author in mutiple authors with middle name
                                fname = name[0];
                                minit = name[1];
                                lname = name[2];
                            }
                            else if(name.length ==2){//first author in mutiple authors without middle name
                                fname = name[0];
                                minit = "";
                                lname = name[1];
                            }
                            else{
                                fname = "none";
                                minit = "none";
                                lname = "none";
                            }
                            DBcommander.command("insert into book_authors values('" + book_id + "','" + author[0] + "','1'" + ");", conn);
                        }
                        else{//Other author in multiple authors
                            if(name.length == 4){// Other author in multiple authors with middle name
                                fname = name[1];
                                minit = name[2];
                                lname = name[3];
                            }
                            else if(name.length ==3){// Other author in multiple authors without middle name
                                fname = name[1];
                                minit = "";
                                lname = name[2];
                            }
                            else{
                                fname = "none";
                                minit = "none";
                                lname = "none";
                            }
                            DBcommander.command("insert into book_authors values('" + book_id + "', '" + author[i] + "', '1'" + ");",conn);
                        
                        }
                    }
                }
                
                DBcommander.command("update book set title = \"" + title + "\" where book_id = \"" + book_id +"\";", conn);
                   
                
            }
            input.close();
        }
        catch(Exception e){
            System.out.println("DB-Error in opening books_authors.csv: " + e.getMessage());
            
        }       
         
    }       
}
