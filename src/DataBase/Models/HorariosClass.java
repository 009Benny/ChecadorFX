/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Models;

import DataBase.Tables.HorariosTable;
import DataBase.Tables.TableProtocol;
import Models.DayEnum;
import java.util.HashMap;

/**
 *
 * @author Benny
 */
public class HorariosClass implements ModelClassProtocol {
    int id = 0;
    String keyId = "idHorarios";
    String name;
    String keyName = "name";
    private String startDate;
    private String keyStartDate;
    private String endDate;
    private String keyEndDate;
    private String lunes;
    private String keyLunes;
    private String martes;
    private String keyMartes;
    private String miercoles;
    private String keyMiercoles;
    private String jueves;
    private String keyJueves;
    private String viernes;
    private String keyViernes;
    private String sabado;
    private String keySabado;
    private String domingo;
    private String keyDomingo;
    
    static public HorariosClass getEmptyObj(String name){
        HorariosTable horarios = new HorariosTable();
        return new HorariosClass(new HashMap<String, Object>() {{
            put(horarios.getIdKey(), 0);
            put("horario", name);
            put("startDate", "");
            put("endDate", "");
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
        id = Integer.parseInt(map.get(getTable().getIdKey()).toString());
        name = map.get("horario").toString();
        lunes = map.get("lunes").toString();
        martes = map.get("martes").toString();
        miercoles = map.get("miercoles").toString();
        jueves = map.get("jueves").toString();
        viernes = map.get("viernes").toString();
        sabado = map.get("sabado").toString();
        domingo = map.get("domingo").toString();
        startDate = map.get("startDate").toString();
        endDate = map.get("endDate").toString();
    }
    
    @Override
    public String getIdentifierKey() {
        return this.keyId;
    }

    @Override
    public String getNameKey() {
        return this.keyName;
    }

    @Override
    public TableProtocol getTable() {
        return new HorariosTable();
    }
    
    @Override
    public String getInsertHeader(){
        return 
               "('" + getIdentifierKey() + "'," +
               "'" + getNameKey() + "'," +
               "'" + keyStartDate + "'," +
               "'" + keyEndDate + "'," +
               "'" + keyLunes + "'," +
               "'" + keyMartes + "'," +
               "'" + keyMiercoles + "'," +
               "'" + keyJueves + "'," +
               "'" + keyViernes + "'," +
               "'" + keySabado + "'," +
               "'" + keyDomingo + "')";
    }

    @Override
    public String getValuesQuery() {
        return 
               "('" + id + "'," +
               "'" + name + "'," +
               "'" + startDate + "'," +
               "'" + endDate + "'," +
               "'" + lunes + "'," +
               "'" + martes + "'," +
               "'" + miercoles + "'," +
               "'" + jueves + "'," +
               "'" + viernes + "'," +
               "'" + sabado + "'," +
               "'" + domingo + "')";
    }
    
    // PENDIENTE DE BORRAR
//    public String getQuery(){
//       return 
//               "`"+ DataBaseManager.horarios_id + "` = " + idHorario + "," +
//               "`horario` = '" + name + "'," +
//               "`startDate` = STR_TO_DATE('"+ DateExtension.getStringDate(startDate, "dd/MM/yyyy") +"','%d/%m/%Y')," +
//               "`endDate` = STR_TO_DATE('"+ DateExtension.getStringDate(endDate, "dd/MM/yyyy") +"','%d/%m/%Y')," +
//               "`lunes` = '" + lunes + "'," +
//               "`martes` = '" + martes + "'," +
//               "`miercoles` = '" + miercoles + "'," +
//               "`jueves` = '" + jueves + "'," +
//               "`viernes` = '" + viernes + "'," +
//               "`sabado` = '" + sabado + "'," +
//               "`domingo` = '" + domingo + "'";
//    }
    
    public boolean haveDateForDay(int day){
        return (day >= 0 && day <= 6) ? !getValueOfDay(DayEnum.values()[day]).isEmpty() : false;
    }
    
    /// GETTERS AND SETTERS
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
        return id;
    }
    
    
    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
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
        this.id = idHorario;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
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