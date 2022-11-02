/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author bennyreyes
 */
public class HorariosClass {
    int idHorario;
    String name;
    private Date startDate;
    private Date endDate;
    private String lunes;
    private String martes;
    private String miercoles;
    private String jueves;
    private String viernes;
    private String sabado;
    private String domingo;

    public HorariosClass(HashMap<String, Object> map){
        idHorario = Integer.parseInt(map.get(DataBaseManager.horarios_id).toString());
        name = map.get("nombre").toString();
        lunes = map.get("lunes").toString();
        martes = map.get("martes").toString();
        miercoles = map.get("miercoles").toString();
        jueves = map.get("jueves").toString();
        viernes = map.get("viernes").toString();
        sabado = map.get("sabado").toString();
        domingo = map.get("domingo").toString();
        startDate = new Date();
        endDate = new Date();
        //startDate = set.getDate("startDate");
        //endDate = set.getDate("startDate");
    }
    
    /**
     * @return the name
     */
    public String getName(){
        return name;
    }
    
    /**
     * @return the idHorario
     */
    public int getIdHorario(){
        return idHorario;
    }
    
    
    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @return the lunes
     */
    public String getLunes() {
        return lunes;
    }

    /**
     * @return the martes
     */
    public String getMartes() {
        return martes;
    }

    /**
     * @return the miercoles
     */
    public String getMiercoles() {
        return miercoles;
    }

    /**
     * @return the jueves
     */
    public String getJueves() {
        return jueves;
    }

    /**
     * @return the viernes
     */
    public String getViernes() {
        return viernes;
    }

    /**
     * @return the sabado
     */
    public String getSabado() {
        return sabado;
    }

    /**
     * @return the domingo
     */
    public String getDomingo() {
        return domingo;
    }
    
    public boolean haveDateForDay(int day){
        return (day >= 0 && day <= 6) ? !getValueOfDay(DayEnum.values()[day]).isEmpty() : false;
    }
    
    public String getValueOfDay(DayEnum day){
        switch(day){
            case monday:
                return lunes;
            case tuesday:
                return martes;
            case wednesday:
                return miercoles;
            case thursday:
                return jueves;
            case friday:
                return viernes;
            case saturday:
                return sabado;
            case sunday:
                return domingo;
            default:
                return "";
        }
    }
}
