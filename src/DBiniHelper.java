/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yunchao
 */
public class DBiniHelper {
    public static String parse_book_id(String string_id){
        if(string_id.length() < 10 ){
            for(int i = 0; i < 10 - string_id.length(); ){
                string_id = "0" + string_id;
        
            }
        }
        System.out.println(string_id);
        return string_id;
        
    }
    
    public static String[] parse_author(String authors){
        String[] author = authors.split(" and ");
        return author;
    }
    
}
