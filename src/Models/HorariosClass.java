/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Extensions.DateExtension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
    
    static public HorariosClass getEmptyObj(String name){
        return new HorariosClass(new HashMap<String, Object>() {{
            put(DataBaseManager.horarios_id, 0);
            put("horario", name);
            put("startDate", new Date());
            put("endDate", new Date());
            put("lunes", "");
            put("martes", "");
            put("miercoles", "");
            put("jueves", "");
            put("viernes", "");
            put("sabado", "");
            put("domingo", "");
        }});
    }

    public HorariosClass(HashMap<String, Object> map){
        idHorario = Integer.parseInt(map.get(DataBaseManager.horarios_id).toString());
        name = map.get("horario").toString();
        lunes = map.get("lunes").toString();
        martes = map.get("martes").toString();
        miercoles = map.get("miercoles").toString();
        jueves = map.get("jueves").toString();
        viernes = map.get("viernes").toString();
        sabado = map.get("sabado").toString();
        domingo = map.get("domingo").toString();
        //startDate = new Date();
        //endDate = new Date();
        try{
            startDate = new SimpleDateFormat("dd/MM/yyyy").parse(map.get("startDate").toString());
            endDate = new SimpleDateFormat("dd/MM/yyyy").parse(map.get("startDate").toString());
        }catch(ParseException e){
            startDate = new Date();
            endDate = new Date();
            System.out.println(e.getMessage());
        }
        
    }
    
    public boolean haveDateForDay(int day){
        return (day >= 0 && day <= 6) ? !getValueOfDay(DayEnum.values()[day]).isEmpty() : false;
    }
    
    public void setValueOfDay(String value, DayEnum day){
        switch(day){
            case monday:
                lunes = value;
                break;
            case tuesday:
                martes = value;
                break;
            case wednesday:
                miercoles = value;
                break;
            case thursday:
                jueves = value;
                break;
            case friday:
                viernes = value;
                break;
            case saturday:
                sabado = value;
                break;
            case sunday:
                domingo = value;
                break;
            default:
                System.out.println("DIA NO VALIDO");
        }
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
    
    public String getQuery(){
       return 
               "`"+ DataBaseManager.horarios_id + "` = " + idHorario + "," +
               "`horario` = '" + name + "'," +
               "`startDate` = STR_TO_DATE('"+ DateExtension.getStringDate(startDate, "dd/MM/yyyy") +"','%d/%m/%Y')," +
               "`endDate` = STR_TO_DATE('"+ DateExtension.getStringDate(endDate, "dd/MM/yyyy") +"','%d/%m/%Y')," +
               "`lunes` = '" + lunes + "'," +
               "`martes` = '" + martes + "'," +
               "`miercoles` = '" + miercoles + "'," +
               "`jueves` = '" + jueves + "'," +
               "`viernes` = '" + viernes + "'," +
               "`sabado` = '" + sabado + "'," +
               "`domingo` = '" + domingo + "'";
    }
    
    public String getQueryValues(){
        return 
               "'" + idHorario + "'," +
               "'" + name + "'," +
               "STR_TO_DATE('"+ DateExtension.getStringDate(startDate, "dd/MM/yyyy") +"','%d/%m/%Y')," +
               "STR_TO_DATE('"+ DateExtension.getStringDate(endDate, "dd/MM/yyyy") +"','%d/%m/%Y')," +
               "'" + lunes + "'," +
               "'" + martes + "'," +
               "'" + miercoles + "'," +
               "'" + jueves + "'," +
               "'" + viernes + "'," +
               "'" + sabado + "'," +
               "'" + domingo + "'";
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

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setLunes(String lunes) {
        this.lunes = lunes;
    }

    public void setMartes(String martes) {
        this.martes = martes;
    }

    public void setMiercoles(String miercoles) {
        this.miercoles = miercoles;
    }

    public void setJueves(String jueves) {
        this.jueves = jueves;
    }

    public void setViernes(String viernes) {
        this.viernes = viernes;
    }

    public void setSabado(String sabado) {
        this.sabado = sabado;
    }

    public void setDomingo(String domingo) {
        this.domingo = domingo;
    }
    
}
