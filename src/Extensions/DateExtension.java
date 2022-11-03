/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Extensions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author bennyreyes
 */
public class DateExtension extends Date {
    
    static public String getStringDate(Date date, String format){
        DateFormat formatter = new SimpleDateFormat(format);
        String today = formatter.format(date);
        return today;
    }
    
    static public boolean isValidDate(String text){
        return text.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})");
    }
}
