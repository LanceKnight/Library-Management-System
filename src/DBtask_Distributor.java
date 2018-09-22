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
import java.util.*;
import java.util.Date;
import java.text.*;
public class DBtask_Distributor {
    static ResultSet rs;
    static ResultSet rs2;
    static String book_id;
    static String title;
    static String authors;
    static String branch_id;
    static String loan_id;
    static String loan_id_fines;
    static int no_copies;
    static ResultSet rs_no_borrow;
    static int no_borrow = 0;
    static int no_ava;
    static String card_no;
    static String reader_fname;
    static String reader_lname;
    static String reader_name;
    static String reader_address;
    static String reader_city;
    static String reader_state;
    static String reader_phone;
    static String book_info = "book_authors join book on book_authors.book_id = book.book_id join book_copies on book_copies.book_id = book.book_id";
    static int tuple_no = 0;
    static double fines = 0;
    static String fine_amt = "0.00";
    static boolean paid_con = false;
    static String paid = "N/A";
    
    static boolean query_first = false;
    
    public static void distribute(String text, int k, Connection conn){
       
        switch( k ){
            case 0://search fines by name
                search_fines_by_name(text, conn);
                break;
            
            case 1:// search loan by book_id
                search_loan_by_book_id(text, conn);
                System.out.println("Task_Distributor: Search loan by book_id");
              
                //rs = DBquerier.query("select * from book_info where author_name = '" + text +"';", conn);
                break;
            case 2:// search fines by card no
                System.out.println("Task_Distributor: Search fines by card no");
                search_fines_by_card_no(text, conn);
                break;
            case 3://search loan by loan id
                search_loan_by_loan_id(text, conn);
                System.out.println("search loan by loan id");
                break;
            case 4:// search loan by name
                search_loan_by_name(text, conn);
                System.out.println("search loan by name");
                break;
                
            case 5:// search loan by card id
                search_loan_by_card_no(text, conn);
                System.out.println("search loan by card id");
                break;
            case 6://check in book by table
                System.out.println("Distributor:Check in book");
                check_in2();
                //check_in(text, conn);
                break;
            case 7://check out book
                System.out.println("Distributor:Check out book");
                check_out2(text, conn);
                break;
            case 8://search reader by name
                search_reader_by_name(text, conn);
                break;
            case 9://search reader by card_id
                search_reader_by_card(text, conn);
                break;
            case 10://add new reader
                add_new_borrower(text, conn);
                break;
            case 11://initilize data
                DBinitiator.initiate(DBTest.conn); 
                break;
            case 12:// refresh fines
                refresh();
                break;
            case 13:// search book
                search(text, conn);
                System.out.println("search books");
                break;
            case 14:// pay the fine    
                pay_fine();
                break;
            case 15://pay all fines
                pay_all_fine();
                break;
            default:
                System.out.println("Tasks command not found");
        }
    }
    
     private static String search_by_title(String query_con, String text, Connection conn){
        query_con = "book_authors.title like '%" + text + "%'";
        query_first = true;
        return query_con;
    }
    
    private static void search_by_author(String query_con, String text, Connection conn){
        
    }
    
    private static void search_by_book_id( String query_con, String text, Connection conn){
        
    }
    
    private static void search_reader_by_name(String text, Connection conn){
        search_reader(text, "borrower.fname like '%" + text + "%' or borrower.lname", conn);
    }
    
    private static void search_reader_by_card(String text, Connection conn){
        search_reader(text, "borrower.card_no", conn);
    }
    
    private static void check_in(String content, Connection conn){
       
        java.sql.Date date_in = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        DBcommander.command("update book_loans set date_in = '" + date_in + "' where loan_id = '" + content +"';", conn);
        DBTest.show_success();
    }
    
    private static void check_in2(){
        int row = DBTest.f_getRow();
        String title;
        Object loan_id_val = DBTest.f_getValueAt(row, 0);
        Object branch_id_val = DBTest.f_getValueAt(row, 3);
        Object title_val = DBTest.f_getValueAt(row, 2);
        loan_id = (String) loan_id_val;
        branch_id = (String) branch_id_val;
        title = (String) title_val;
        
     
        java.sql.Date date_in = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        DBcommander.command("update book_loans set date_in = '" + date_in + "' where loan_id = '" + loan_id +"';", DBTest.conn);
       
        DBTest.show_success();
        DBTest.f_refresh();
    }
    
    private static void check_out(String content, Connection conn){
        
        String info[] = content.split(" ");
        String book_id = info[0];
        String branch_id = info[1];
        String card_id = info[2];
        
        java.sql.Date date_out = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Now use today date.
        c.add(Calendar.DATE, 15); // Adding 5 days
        java.sql.Date date_due = new java.sql.Date(c.getTime().getTime());
        DBcommander.command("insert into book_loans(book_id, branch_id, card_no, date_out, due_date) values('" + book_id + "', '" + branch_id + "', '" + card_id + "','"+ date_out + "','" + date_due + "');", conn);
        
        rs = DBquerier.query("SELECT LAST_INSERT_ID()" , conn);
        String loan_id = "0";
        try{
        if(rs.next()){
            loan_id = rs.getString("LAST_INSERT_ID()");
        }
        }
        catch(Exception e){
        }
        DBTest.show_success();
        //DBTest.c_setDisplay("Borrow success! Below is the information for the loan: " + loan_id);
        
    }
     private static void check_out2(String content, Connection conn){
        
        
        int row = DBTest.b_getRow();
        String book_id = (String)DBTest.b_getValueAt(row, 0);
        String branch_id = (String)DBTest.b_getValueAt(row, 3);
        String card_id = content;
        
        java.sql.Date date_out = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Now use today date.
        c.add(Calendar.DATE, 15); // Adding 5 days
        System.out.println("select * from borrower where card_no = '" + content + "';");
        rs2 = DBquerier.query("select * from borrower where card_no = '" + content + "';", DBTest.conn);
        try{    
            if(!rs2.next()){
                    DBTest.show_user_not_exist();
                }
            else{
                if(check_if_can_borrow(content)){

                    java.sql.Date date_due = new java.sql.Date(c.getTime().getTime());
                    System.out.println("insert into book_loans(book_id, branch_id, card_no, date_out, due_date) values('" + book_id + "', '" + branch_id + "', '" + card_id + "','"+ date_out + "','" + date_due + "');");
                    DBcommander.command("insert into book_loans(book_id, branch_id, card_no, date_out, due_date) values('" + book_id + "', '" + branch_id + "', '" + card_id + "','"+ date_out + "','" + date_due + "');", conn);

                    rs = DBquerier.query("SELECT LAST_INSERT_ID()" , conn);
                    String loan_id = "0";
                    try{
                        if(rs.next()){
                            loan_id = rs.getString("LAST_INSERT_ID()");
                        }
                    }
                    catch(Exception e){
                    }
                    DBTest.show_success();
                    DBTest.b_off_check_out();
                    //DBTest.c_setDisplay("Borrow success! Below is the information for the loan: " + loan_id);
                    DBTest.clearTable();
                }
                else{
                    DBTest.show_max();
                }
            }
        }
        catch(Exception e){
        
        }
        
    }
    private static boolean check_if_can_borrow(String card_no){
        boolean can_borrow = true;
            try{
                int num;
     
                    //System.out.println("select count(*) as num from book_loans where card_no = '" + card_no + "' and date_in is null;");
                    rs = DBquerier.query("select count(*) as num from book_loans where card_no = '" + card_no + "' and date_in is null;", DBTest.conn);
                    rs.next();
                    num = rs.getInt("num");
                    System.out.println("here");
                    //System.out.println("the user has borrowed:" + num);
                    if(num >= 3){
                        return false;

                    }
                    else{
                        return true;
                    }
                
            }
            catch(Exception e){
                System.out.println("if can borrow:"+e.getMessage());
            }
            
        return can_borrow;
    }
     
    private static void add_new_borrower(String text, Connection conn){
        //add(String col, String content, String table, Connection conn)
        String info[] = text.split("\t");
        String reader_fname = info[0];
        String reader_lname = info[1];
        String reader_address = info[2];
        String reader_phone = info[3];
        if(!check_user_exist(reader_fname, reader_lname, reader_address)){
            add("fname, lname, address, phone", "'" + reader_fname + "', '" + reader_lname + "', '" + reader_address + "','" + reader_phone + "'", "borrower", conn);
            rs = DBquerier.query("SELECT LAST_INSERT_ID();", conn);
            try{
                rs.next();
                card_no = rs.getString("LAST_INSERT_ID()");
                System.out.println("auto-generated card id:" + card_no);
                DBTest.set_card_no( card_no );
                DBTest.show_success();
            }
            catch(Exception e){

            }
        }
        else{
            DBTest.show_user_exist();
        }
    }
    
    private static boolean check_user_exist(String fname, String lname, String address){
        rs = DBquerier.query("select * from borrower where fname = '" + fname + "' and lname = '"+ lname +"'and address ='" + address + "';" , DBTest.conn);
        try{
            if(rs.next()==false){//not exist
               
                return false;
            }
            else{
                return true;
            }
        }
        
        catch(Exception e){
        
        }
        return false;
    }
    
    private static void search_loan_by_loan_id(String text, Connection conn){
        search_loan( text, "book_loans.loan_id", conn);
    }
    
    private static void search_loan_by_name(String text, Connection conn){
        search_loan( text, "borrower.fname like '%" + text + "%' or borrower.lname", conn);
    }
    
     private static void search_loan_by_card_no(String text, Connection conn){
        search_loan( text, "book_loans.card_no", conn);
    }
    
    private static void search_loan_by_book_id(String text, Connection conn){
        search_loan( text, "book_loans.book_id", conn);
    } 
    
    private static void search_fines_by_name(String text, Connection conn){
        search_fines(text, "fname like '%" + text + "%' or lname", conn);
    }
    private static void search_fines_by_card_no(String text, Connection conn){
        search_fines(text, "book_loans.card_no", conn);
    }
    
    private static void pay_fine(){
        int row = DBTest.f_getRow();
        Object loan_id_val = DBTest.f_getValueAt(row, 0);
        Object paid_val = DBTest.f_getValueAt(row, 8);
        String loan_id = (String) loan_id_val;
        paid = (String)paid_val;
        if(paid.equals("N/A")){
            DBTest.f_off_pay();
            System.out.println("pay_fine:paid N/A");
        }
        else if(paid.equals("Yes")){
            DBTest.f_off_pay();
            System.out.println("pay_fine:paid Yes");
        }
        else{
               System.out.println("pay_fine:paid no. begin to pay");
               DBcommander.command("update fines set paid = true where loan_id = '" + loan_id + "';", DBTest.conn);
        }
        DBTest.show_success();
        DBTest.f_refresh();
        
        //rs = DBquerier.query("select * from book_loans", DBTest.conn);
    } 
    
    private static void pay_all_fine(){
        int row = DBTest.l_getRow();
        Object card_no_val = DBTest.l_getValueAt(row, 0);
        //Object paid_val = DBTest.f_getValueAt(row, 8);
        String card_no = (String) card_no_val;
        //paid = (String)paid_val;
        /*if(paid.equals("N/A")){
            DBTest.f_off_pay();
            System.out.println("pay_fine:paid N/A");
        }
        else if(paid.equals("Yes")){
            DBTest.f_off_pay();
            System.out.println("pay_fine:paid Yes");
        }
        else{*/
               System.out.println("update fines set paid = true where loan_id in (select loan_id from book_loans where card_no = '" + card_no +"');");
               DBcommander.command("update fines set paid = true where loan_id in (select loan_id from book_loans where card_no = '" + card_no +"');", DBTest.conn);
        //}    
        //DBTest.f_refresh();
               DBTest.show_success();
        //rs = DBquerier.query("select * from book_loans", DBTest.conn);
    } 
     
    private static void refresh(){
        rs = DBquerier.query("select * from book_loans", DBTest.conn);
        
        String loan_id;
        java.sql.Date date_out;
        java.sql.Date due_date;
        java.sql.Date date_in;
        java.util.Date today = Calendar.getInstance().getTime();
        long dueday_today_diff;
        long dueday_in_diff;
        double fine;
        try{
            while(rs.next()){
                loan_id = rs.getString("loan_id");
                rs2 = DBquerier.query("select * from fines where loan_id = '" + loan_id +"';", DBTest.conn);
                
                System.out.println(loan_id);
                date_out = rs.getDate("date_out");
                due_date = rs.getDate("due_date");
                date_in = rs.getDate("date_in");
                System.out.println(date_in);
                fine = Book_Loans.getFine(due_date, date_in, today);
                if(fine != 0){
                    System.out.println("fine is not 0");
                    if(rs2.next() == true){
                        DBcommander.command("update fines set fine_amt = " + fine +" where loan_id = " + loan_id + ";", DBTest.conn);
                    }
                    else{
                        DBcommander.command("insert into fines values('" + loan_id +"','" + fine +"',false);", DBTest.conn);
                    }

                    
                }
                
                System.out.println();
            }
        }
        catch(Exception e){
            System.out.println("refresh: " + e.getMessage());
        }
        DBTest.show_success();
    } 
    
    
    //----------------------------------------------------------------------
    private static void search(String query_con, Connection conn){
        DBTest.clearTable();
        String condition = query_con;
        //if(!text.equals("")){
            System.out.println("Query condition:" + condition);
            System.out.println("your first query:" + "select * from " + book_info + " where " + condition +";");
            rs = DBquerier.query("select * from " + book_info + " where " + condition +";", conn);
            //
            try{
                if(rs.next() == false){
                    DBTest.show_no_result();
                    
                }
                else{
                    do{
                        book_id = rs.getString("book_id");
                        title = rs.getString("title");
                        authors = rs.getString("author_name");
                        branch_id = rs.getString("branch_id");
                        no_copies = rs.getInt("no_of_copies");
                        System.out.println("your query:" + "select COUNT(*) as num from book_loans join book_authors on book_loans.book_id = book_authors.book_id group by book_authors.book_id having book_id = '" + book_id +"' and branch_id = '" + branch_id + "';");
                        rs_no_borrow = DBquerier.query("select book_loans.book_id, branch_id, COUNT(*) as num from book_loans where book_loans.book_id = '" + book_id + "' and book_loans.branch_id = '" +  branch_id +"' and date_in is null group by book_loans.branch_id, book_loans.book_id;", conn);
                        if(rs_no_borrow.next()){
                            no_borrow = rs_no_borrow.getInt("num");
                        }
                        else{
                            no_borrow = 0;
                        }
                        
                        no_ava = no_copies - no_borrow;
                        Object[] rowData = {book_id, title, authors, branch_id, no_copies,no_ava};
                        DBTest.addRow(rowData);
                        /*
                        DBTest.setDisplay(book_id, tuple_no, 0);
                        DBTest.setDisplay(title, tuple_no, 1);
                        DBTest.setDisplay(authors, tuple_no, 2);
                        DBTest.setDisplay(branch_id, tuple_no, 3);
                        DBTest.setDisplay( Integer.toString(no_copies), tuple_no, 4);
                        DBTest.setDisplay( Integer.toString(no_ava) , tuple_no, 5);
                                */
                                //"book_id: " + book_id + " title: " + title + " author(s): " + authors + " branch_id: " + branch_id + " Number of copies: " + no_copies + " Number Available: " + no_ava);
                        tuple_no++;
                    }while(rs.next());
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        //}
    }
    
    
    private static void add(String col, String content, String table, Connection conn){
        System.out.println("insert into " + table + "(" + col + ") values(" + content + ");");
        DBcommander.command("insert into " + table + "(" + col + ") values(" + content + ");", conn);
    }
    
    private static void search_reader(String text, String condition, Connection conn){
        DBTest.clear_r_display();
        if(!text.equals("")){
       
            rs = DBquerier.query("select * from borrower where " + condition + " like '%" + text +"%';", conn);
            //rs_no_borrow = DBquerier.query("select COUNT(*) as num from book_loans join book_authors on book_loans.book_id = book_authors.book_id where author_name = '" + text + "' group by book_authors.book_id;", conn);
            try{
                if(rs.next() == false){
                    DBTest.show_no_result();
                    
                }
                else{
                    do{;
                        card_no = rs.getString("card_no");
                        reader_fname = rs.getString("fname");
                        reader_lname = rs.getString("lname");
                        reader_address = rs.getString("address");

                        reader_phone = rs.getString("phone");
                        /*
                        while(rs_no_borrow.next()){
                            no_borrow = rs.getInt("num");
                        }*/
                        //no_ava = no_copies - no_borrow;
                        Object[] rowData = {card_no, reader_fname, reader_lname, reader_address,  reader_phone};
                        DBTest.r_addRow(rowData);
                        /*
                        DBTest.setDisplay(book_id, tuple_no, 0);
                        DBTest.setDisplay(title, tuple_no, 1);
                        DBTest.setDisplay(authors, tuple_no, 2);
                        DBTest.setDisplay(branch_id, tuple_no, 3);
                        DBTest.setDisplay( Integer.toString(no_copies), tuple_no, 4);
                        DBTest.setDisplay( Integer.toString(no_ava) , tuple_no, 5);
                                */
                                //"book_id: " + book_id + " title: " + title + " author(s): " + authors + " branch_id: " + branch_id + " Number of copies: " + no_copies + " Number Available: " + no_ava);
                        tuple_no++;
                    }while(rs.next());
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    private static void search_loan(String text, String condition, Connection conn){
        System.out.println("Your query:" + "select * from book_loans join borrower on book_loans.card_no = borrower.card_no join book_authors on book_authors.book_id = book_loans.book_id where " + condition + " like '%" + text +"%';");
        DBTest.clear_f_display();
        if(!text.equals("")){
            rs = DBquerier.query("select * from book_loans join borrower on book_loans.card_no = borrower.card_no join book on book.book_id = book_loans.book_id left join fines on fines.loan_id = book_loans.loan_id where " + condition + " like '%" + text +"%';", conn);
            //rs_no_borrow = DBquerier.query("select COUNT(*) as num from book_loans join book_authors on book_loans.book_id = book_authors.book_id where author_name = '" + text + "' group by book_authors.book_id;", conn);
            try{
                if(rs.next() == false){
                    DBTest.show_no_result();
                    
                }
                else{
                    do{
                        String date_out;
                        String due_date;
                        String date_in;
                        loan_id = rs.getString("loan_id");
                        reader_fname = rs.getString("fname");
                        reader_lname = rs.getString("lname");
                        reader_name = reader_fname + " " + reader_lname;
                        title = rs.getString("title");
                        
                        branch_id = rs.getString("branch_id");
                        date_out = rs.getString("date_out");
                        due_date = rs.getString("due_date");
                        date_in = rs.getString("date_in");
                        fines = rs.getDouble("fine_amt");
                        paid_con = rs.getBoolean("paid");
                        NumberFormat formatter = new DecimalFormat("#0.00");   
                        fine_amt = formatter.format(fines);
                        System.out.println(fines);
                        if( fines != 0){
                            if( paid_con == true ){
                                paid = "Yes";
                            }
                            else if(paid_con == false){
                                paid = "No";
                            }
                        }
                        else{
                            paid ="N/A";
                        }
                        //no_copies = rs.getInt("no_of_copies");
                        /*
                        while(rs_no_borrow.next()){
                            no_borrow = rs.getInt("num");
                        }*/
                        //no_ava = no_copies - no_borrow;
                        Object[] rowData = {loan_id, reader_name, title, branch_id, date_out, due_date, date_in, fine_amt , paid};
                        DBTest.f_addRow(rowData);
                        /*
                        DBTest.setDisplay(book_id, tuple_no, 0);
                        DBTest.setDisplay(title, tuple_no, 1);
                        DBTest.setDisplay(authors, tuple_no, 2);
                        DBTest.setDisplay(branch_id, tuple_no, 3);
                        DBTest.setDisplay( Integer.toString(no_copies), tuple_no, 4);
                        DBTest.setDisplay( Integer.toString(no_ava) , tuple_no, 5);
                                */
                                //"book_id: " + book_id + " title: " + title + " author(s): " + authors + " branch_id: " + branch_id + " Number of copies: " + no_copies + " Number Available: " + no_ava);
                        tuple_no++;
                    }while(rs.next());
                }
            }catch(Exception e){
                System.out.println("search_loan:" + e.getMessage());
            }
        }
    }
    
       private static void search_fines(String text, String condition, Connection conn){
        System.out.println("Your query:" + "select SUM(fine_amt) as sum, fname, lname, paid from book_loans join borrower on book_loans.card_no = borrower.card_no join fines on fines.loan_id = book_loans.loan_id where " + condition + " like '%" + text + "%' group by book_loans.card_no;");
        DBTest.clear_l_display();
        if(!text.equals("")){
            rs = DBquerier.query("select SUM(fine_amt) as sum, fname, lname, paid, book_loans.card_no from book_loans join borrower on book_loans.card_no = borrower.card_no join fines on fines.loan_id = book_loans.loan_id where " + condition + " like '%" + text + "%' and paid = false group by book_loans.card_no;", conn);
            //rs_no_borrow = DBquerier.query("select COUNT(*) as num from book_loans join book_authors on book_loans.book_id = book_authors.book_id where author_name = '" + text + "' group by book_authors.book_id;", conn);
            try{
                if(rs.next() == false){
                    DBTest.show_no_result();
                    
                }
                else{
                    do{
                        String card_no = rs.getString("card_no");
                        String name = rs.getString("fname") + " " + rs.getString("lname");
                        Double fines = rs.getDouble("sum");
                        
                        Boolean paid_con = rs.getBoolean("paid");
                        /*
                        loan_id = rs.getString("loan_id");
                        reader_fname = rs.getString("fname");
                        reader_lname = rs.getString("lname");
                        reader_name = reader_fname + " " + reader_lname;
                        title = rs.getString("title");
                        
                        branch_id = rs.getString("branch_id");
                        date_out = rs.getString("date_out");
                        due_date = rs.getString("due_date");
                        date_in = rs.getString("date_in");
                        fines = rs.getDouble("fine_amt");
                        paid_con = rs.getBoolean("paid");
                        */
                        NumberFormat formatter = new DecimalFormat("#0.00");   
                        fine_amt = formatter.format(fines);
                        System.out.println(fines);
                        if( fines != 0){
                            if( paid_con == true ){
                                paid = "Yes";
                            }
                            else if(paid_con == false){
                                paid = "No";
                            }
                        }
                        else{
                            paid ="N/A";
                        }
                        //no_copies = rs.getInt("no_of_copies");
                        /*
                        while(rs_no_borrow.next()){
                            no_borrow = rs.getInt("num");
                        }*/
                        //no_ava = no_copies - no_borrow;
                        Object[] rowData = {card_no,name, fine_amt , paid};
                        DBTest.l_addRow(rowData);
                        /*
                        DBTest.setDisplay(book_id, tuple_no, 0);
                        DBTest.setDisplay(title, tuple_no, 1);
                        DBTest.setDisplay(authors, tuple_no, 2);
                        DBTest.setDisplay(branch_id, tuple_no, 3);
                        DBTest.setDisplay( Integer.toString(no_copies), tuple_no, 4);
                        DBTest.setDisplay( Integer.toString(no_ava) , tuple_no, 5);
                                */
                                //"book_id: " + book_id + " title: " + title + " author(s): " + authors + " branch_id: " + branch_id + " Number of copies: " + no_copies + " Number Available: " + no_ava);
                        tuple_no++;
                    }while(rs.next());
                }
            }catch(Exception e){
                System.out.println("search_loan:" + e.getMessage());
            }
        }
    }

    
}
