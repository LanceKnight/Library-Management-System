
import java.io.File;
import java.sql.Connection;
import java.util.Scanner;
import java.text.*;
import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yunchao
 */
public class Book_Loans {
    public static void initiate(Connection conn) {
        try{
            
            File file = new File("initialData/book_loans_data_F14.csv");
            Scanner input = new Scanner(file);
            System.out.println("Does it exist?"+ file.exists());
            input.nextLine();
            
            while(input.hasNext()){
                String loan_id = input.next();
                String book_id = input.next();
                String branch_id = input.next();
                String card_no = input.next();
                String s_date_out = input.next();
                String s_due_date = input.next();
                String s_date_in = input.next();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsed_out = null;
                java.util.Date parsed_due = null;
                java.util.Date parsed_in = null;
                java.sql.Date date_out = null;   
                java.sql.Date due_date = null;
                java.sql.Date date_in = null;
                java.util.Date today = Calendar.getInstance().getTime();
                double fine=0;
                try {
                    parsed_out = sdf.parse(s_date_out);                
                    parsed_due = sdf.parse(s_due_date);
                    date_out = new java.sql.Date(parsed_out.getTime());   
                    due_date = new java.sql.Date(parsed_due.getTime());
                    
                    if(!s_date_in.equals("NULL")){// book not return yet.
                       // System.out.println(loan_id + ": not null");
                        parsed_in = sdf.parse(s_date_in);
                         
                        date_in = new java.sql.Date(parsed_in.getTime());
                        //System.out.println("insert into book_loans values('" + loan_id + "','" + book_id + "','" + branch_id + "','" + card_no + "','" + date_out +"','" + due_date + "','" + date_in + "'); ");
                        DBcommander.command("insert into book_loans values('" + loan_id + "','" + book_id + "','" + branch_id + "','" + card_no + "','" + date_out +"','" + due_date + "','" + date_in + "'); ", conn);
                        
                        //System.out.println();
                        
                    }
                    else{
                        //System.out.println(loan_id + ": null");
                        //System.out.println("insert into book_loans values('" + loan_id + "','" + book_id + "','" + branch_id + "','" + card_no + "','" + date_out +"','" + due_date +  "'); ");
                        DBcommander.command("insert into book_loans values('" + loan_id + "','" + book_id + "','" + branch_id + "','" + card_no + "','" + date_out +"','" + due_date +  "'," + "null); ", conn);
                    }
                    System.out.println("loan id:" + loan_id + ",fine:" + fine);
                    fine = getFine(due_date, date_in, today);
                    if(fine != 0){
                        DBcommander.command("insert into fines values('" + loan_id + "','" + fine + "', false);",conn);
                    }
                } catch (ParseException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
                
                
                //System.out.println("loan_id: " + loan_id + ", book_id: " + book_id + ",branch_id: " + branch_id + ",card_no:" + card_no + ",date_out:" + date_out + ",due_date:"+ due_date + ",date_in:"+ date_in);
                
                    //System.out.println("book_id: " + book_id +"' branch_id:" + branch_id + " no_of_copies: " + no_of_copies);
                //DBcommander.command("insert into book values('" + book_id + "', 'title');" , conn);
                //DBcommander.command("insert into book_loans values('" + loan_id + "','" + book_id + "','" + branch_id + "','" + card_no + "','" + date_out +"','" + due_date + "','" + date_in + "'); ", conn);
                //DBcommander.command("insert into book_authors values('" + book_id + "', 'author_name', 'fname', 'minit','lname');" , conn);
                
                }
            
            
            //ResultSet rs = DBquerier.query("SELECT * FROM employee;", conn);
            //String ssn = rs.getString("ssn");
            //System.out.println(ssn);
            input.close();
        }
        catch(Exception e){
            System.out.println("DB-Error in opening book_loans_data_F14.csv: " + e.getMessage());
        }
    }        
    
    public static double getFine(java.sql.Date due_date, java.sql.Date date_in, java.util.Date today){
        
        double fine = 0;
        long dueday_in_diff = 0;
        long dueday_today_diff = (due_date.getTime() - today.getTime())/ (24 * 60 * 60 * 1000);

            System.out.println("diff:" + dueday_today_diff);

            if(dueday_today_diff < 0){//check due book
                if(date_in == null){//book not returned yet

                    fine = -(double)dueday_today_diff * 0.25;
                    //DBcommander.command("", conn);
                    System.out.println("fine:" + fine);
                }
                else{
                    dueday_in_diff = (due_date.getTime() - date_in.getTime())/ (24 * 60 * 60 * 1000);
                    System.out.println("book has been returned");
                    if(dueday_in_diff < 0){// turn in date is later than due_date
                        fine = -(double)dueday_in_diff * 0.25;
                        System.out.println("fine:" + fine);
                    }
                }
            }
        return fine;
    }
}
