/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Extensions;

/**
 *
 * @author Benny
 */
public class StringExtension {
    
    public static int kPASSWORD_LENGTH = 6;
    
    public static boolean onlyDigits(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < '0'
                || str.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }
    
}
