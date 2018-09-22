/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yunchao
 */
import java.io.*;
import java.util.*;
import java.sql.*;
public class DBinitiator1 {
   
     public static void initiate(String table, Connection conn) {
         switch( table ){
            case "book_copies":
                Book_Copies.initiate(conn);
                break;
            case "books_authors":
                Books_Authors.initiate(conn);
                break;
            case "library_branch":
                Library_Branch.initiate(conn);
                break;
            case "borrowers":
                Borrowers.initiate(conn);
                break;
            default:
                System.out.print("Cannot find the table");
         }
     }
}

