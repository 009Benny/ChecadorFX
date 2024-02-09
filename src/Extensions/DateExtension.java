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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
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
    
    public static int getDayNumberDayOf(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }
    
    public static void valideTimeZone(){
        System.out.println("Current time zone: " + TimeZone.getDefault());
        
        TimeZone.setDefault(TimeZone.getTimeZone("America/Monterrey"));
        
        System.out.println("Now time zone: " + TimeZone.getDefault());
    }
    
    public static LocalDateTime getLocalDateTime(String text, String format) {
        if (format.contains("yyyy") && format.contains("MM") && format.contains("dd")){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return LocalDateTime.parse(text, formatter);
        }else{
            // Fecha estándar (puedes ajustarla según tus necesidades)
            LocalDateTime fechaBase = LocalDateTime.now().toLocalDate().atStartOfDay();
            LocalTime hora = LocalTime.parse(text, DateTimeFormatter.ofPattern("HH:mm"));
            // Combinar la fecha y hora
            return fechaBase.withHour(hora.getHour()).withMinute(hora.getMinute());
        }
    }
    
    public static LocalDateTime traducirHora(String horaString) {
       // Fecha estándar (puedes ajustarla según tus necesidades)
        LocalDateTime fechaBase = LocalDateTime.now().toLocalDate().atStartOfDay();

        // Obtener la hora del String
        LocalTime hora = LocalTime.parse(horaString, DateTimeFormatter.ofPattern("HH:mm"));

        // Combinar la fecha y hora
        return fechaBase.withHour(hora.getHour()).withMinute(hora.getMinute());
    }
    
    public static int getNumberDayOf(LocalDateTime dateTime){
        // Usa el campo de la semana ISO para obtener el número de la semana
        return dateTime.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
    }
    
    public static String getStringFrom(LocalDateTime dateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return dateTime.format(formatter);
    }
    
    public static long getDiferenceBetween(LocalDateTime dta, LocalDateTime dtb) {
        return Math.abs(java.time.Duration.between(dta, dtb).getSeconds());
    }

    public static LocalDateTime getLocalDateTime(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
