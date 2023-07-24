package Extensions;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Benny
 */
public class NumberUtils {
    
    public static int getIntFrom(String string){
        if(string.length() > 0 && StringExtension.onlyDigits(string)) {
           return Integer.valueOf(string);
        }
        return 0;
    }
    
}
