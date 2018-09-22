import java.sql.*;
import javax.swing.*;

public class DBTest {
        public static Connection conn = DBconnector.connect("root","609114");
        static Main_UI GUI = new Main_UI();
        
        /**
         * @param args
         */
        
        public static void main(String[] args) {
                //Connection conn = DBconnector.connect("root","609114");
                GUI.setVisible(true);
                //Book_Loans.initiate(conn);
              // Main_UI.showUI(conn);
                
                //ResultSet rs = DBquerier.query("select * from library.book_authors where author_name = 'William Rife';", conn);
                //DBinitiator.initiate("book_copies");
                //Books_Authors.initiate(conn);
                //Borrowers.initiate(conn);
                //Book_Copies.initiate(conn);
                //TableBook.initiate(conn);
                
                
        }  
        
        
        public static void setDisplay(String content, int row, int col){
            GUI.setDisplay(content, row, col);
        }
      
        
        public static void r_setDisplay(String content, int row, int col){
            GUI.setDisplay(content, row, col);
        }
        
        public static void clearTable(){
            GUI.clearTable();
        }
        
        public static void clear_r_display(){
            GUI.clear_r_display();
        }
        
        public static void clear_f_display(){
            GUI.clear_f_display();
        }
        
        public static void set_card_no(String text){
            GUI.set_card_id(text);
        }
        
        public static void addRow(Object[] rowData){
            GUI.addRow(rowData);
        }
        public static void b_off_check_out(){
            GUI.b_off_check_out();
            System.out.println("off");
        }
        
        public static Object b_getValueAt(int row, int col){
            return GUI.b_getValueAt(row, col);
        }
        
        public static int b_getRow(){
            return GUI.b_getRow();
        }
        
        public static void r_addRow(Object[] rowData){
            GUI.r_addRow(rowData);
        }
        
        public static void f_addRow(Object[] rowData){
            GUI.f_addRow(rowData);
        }
        
        public static int f_getRow(){
        int row = GUI.f_getRow();
            return row;
        }
    
        public static Object f_getValueAt(int row, int col){
            Object value = GUI.f_getValueAt(row, col);
            return value;
        }
        
        
        
        public static void f_refresh(){
            GUI.f_refresh();
        }
        
        public static void f_on_pay(){
            GUI.f_on_pay();
        }
        
        public static void f_off_pay(){
            GUI.f_off_pay();
        }
        
        public static void clear_l_display(){
            GUI.clear_l_display();
        }
        
        public static void l_addRow(Object[] rowData){
            GUI.l_addRow(rowData);
        }
        
        public static int l_getRow(){
        int row = GUI.l_getRow();
            return row;
        }
    
        public static Object l_getValueAt(int row, int col){
            Object value = GUI.l_getValueAt(row, col);
            return value;
        }
        public static void l_refresh(){
            GUI.l_refresh();
        }
        public static void show_no_result(){
            No_Result no_result = new No_Result(GUI, false);
            no_result.setVisible(true);
        }
        
        public static void show_no_input(){
            No_Input no_input = new No_Input(GUI, false);
            no_input.setVisible(true);
        }
        
        public static void show_success(){
            Success suc = new Success(GUI, false);
            suc.setVisible(true);
        }
        
        public static void show_max(){
            Max_borrow suc = new Max_borrow(GUI, false);
            suc.setVisible(true);
        }
         
        public static void show_user_exist(){
            User_Exist suc = new User_Exist(GUI, false);
            suc.setVisible(true);
        }
        public static void show_user_not_exist(){
            User_Not_Exist suc = new User_Not_Exist(GUI, false);
            suc.setVisible(true);
        }
        
        
}