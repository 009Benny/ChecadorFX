/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author bennyreyes
 */
public enum DayEnum {
    monday("lunes", 1),
    tuesday("martes", 2),
    wednesday("miercoles", 3),
    thursday("jueves", 4),
    friday("viernes", 5),
    saturday("sabado", 6),
    sunday("domingo", 7);
    
    private String key;
    private int numDay;
    
    private DayEnum(String key, int numDay){
        this.key = key;
        this.numDay = numDay;
    }
    
    public String getKey(){
        return this.key;
    }
    
    public int getNumberDay(){
       return this.numDay;
    }
}
