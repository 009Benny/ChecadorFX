/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Extensions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    static public Date getDateByString(String string, String format){
        try {
            Date casted = new SimpleDateFormat(format).parse(string);
            return casted;
        } catch (ParseException ex) {
            Logger.getLogger(DateExtension.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    static public boolean isValidDate(String text){
        return text.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})");
    }
    
    static public LocalDateTime getNow(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return now;
    }
    
    static public String getStringFromLocalDate(LocalDate date, String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }
    
}
