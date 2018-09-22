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
public class DBinitiator {
    public static void initiate(Connection conn){
        Library_Branch.initiate(conn);
        Book_Copies.initiate(conn);
        Books_Authors.initiate(conn); 
        Borrowers.initiate(conn);
        Book_Loans.initiate(conn);
        /*
        DBinitiator.initiate("book_copies", conn);
        DBinitiator.initiate("books_authors", conn);
        DBinitiator.initiate("library_branch", conn);
        DBinitiator.initiate("borrowers", conn);*/
        System.out.println("Initialization done!");
        DBTest.show_success();
    }
}
