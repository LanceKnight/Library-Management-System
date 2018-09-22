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

public class Borrowers {
    public static void initiate(Connection conn) {
        try{
            DBcommander.command("use library;", conn);
            File file = new File("initialData/borrowers.csv");
            Scanner input = new Scanner(file);
            System.out.println("Does it exist?"+ file.exists());
            
            
            System.out.println("1. title: "+ input.nextLine());
            /*
            System.out.println("2. card_no: "+ input.next());
            System.out.println("3. fname: "+ input.next());
            System.out.println("4. lname: "+ input.next());
            
            System.out.println("5. address: " + input.next() + " " + input.next());
            System.out.println("6. city: "  + input.next());
            System.out.println("7. state: "+ input.next());
            System.out.println("8. phone: "+ input.next() + "" + input.next());*/
            while(input.hasNext()){
                String tuple = input.nextLine();
                String[] info = tuple.split("\t");
                int card_no = Integer.parseInt(info[0]);
                String fname = info[1];
                String lname = info[2];
                String address = info[3];
                String city = info[4];
                String state = info[5];
                String phone = info[6];
                
                String full_address = address + ", " + city + ", " + state + ", ";
                //System.out.println("card_no: " + card_no +"' fname:" + fname + " lname: "+ lname + " addresss: " + full_address + " city:" + city + " state:" + state + " phone:" + phone);
                DBcommander.command("insert into borrower values('" + card_no + "', '" + fname + "','" + lname + "','" + address + "','" + phone + "');" , conn);
            }
            
            
            //ResultSet rs = DBquerier.query("SELECT * FROM employee;", conn);
            //String ssn = rs.getString("ssn");
            //System.out.println(ssn);
            input.close();
        }
        catch(Exception e){
            System.out.println("DB-Error in opening borrowers.csv: " + e.getMessage());
        }
    }
}
