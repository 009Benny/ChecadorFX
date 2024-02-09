/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Models;

import Managers.DataBaseManager;
import DataBase.Tables.HorariosTable;
import DataBase.Tables.TableProtocol;
import Extensions.DateExtension;
import Models.DayEnum;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    private String keyStartDate = "startDate";
    private String endDate;
    private String keyEndDate = "endDate";
    private String lunes;
    private String keyLunes = "lunes";
    private String martes;
    private String keyMartes = "martes";
    private String miercoles;
    private String keyMiercoles = "miercoles";
    private String jueves;
    private String keyJueves = "jueves";
    private String viernes;
    private String keyViernes = "viernes";
    private String sabado;
    private String keySabado = "sabado";
    private String domingo;
    private String keyDomingo = "domingo";
    private int tolerancia;
    private String keyTolerancia = "tolerancia";
    
    public HorariosClass(){}
    
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
            put("tolerancia", 0);
        }});
    }
    
    public HorariosClass(HashMap<String, Object> map){
        id = Integer.parseInt(map.get(getTable().getIdKey()).toString());
        name = (String) map.getOrDefault(keyName, "");
        lunes = (String) map.getOrDefault(keyLunes, "");;
        martes = (String) map.getOrDefault(keyMartes, "");;
        miercoles = (String) map.getOrDefault(keyMiercoles, "");;
        jueves = (String) map.getOrDefault(keyJueves, "");
        viernes = (String) map.getOrDefault(keyViernes, "");
        sabado = (String) map.getOrDefault(keySabado, "");
        domingo = (String) map.getOrDefault(keyDomingo, "");
        startDate = (String) map.getOrDefault(keyStartDate, "");;
        endDate = (String) map.getOrDefault(keyEndDate, "");
        tolerancia = Integer.parseInt(map.get(keyTolerancia).toString());
    }
    
    public static HorariosClass getHorarioById(String idHorario){
        String query = HorariosClass.getQuerytoAllItemsById(idHorario);
        DataBaseManager db = new DataBaseManager();
        List<HashMap<String, Object>> data = db.getDataWithQuery(query);
        if(data.size() == 1){
            return new HorariosClass(data.get(0));
        }
        return null;
    }
    
    public boolean isOnValidTime(LocalDateTime now){
        int day = DateExtension.getNumberDayOf(now);
        String nowHoursString = DateExtension.getStringFrom(now, "HH:mm");
        LocalDateTime dateStart = this.getLocalDateofDay(day, 0);
        LocalDateTime dateEnd = this.getLocalDateofDay(day, 1);
        //long diference = DateExtension.getDiferenceBetween(now, dateStart);
        return (now.compareTo(dateStart) > 0 && now.compareTo(dateEnd) < 0);
    }
    
    static public String getQuerytoAllItemsById(String idHorario){
        HorariosClass horario = new HorariosClass();
        return HorariosClass.getQuerytoAllItems().replace(";","") + " WHERE " + horario.getTable().getTableName() + "." + horario.getKeyId() + " = " + idHorario;
    }
    
    static public String getQuerytoAllItems(){
        HorariosTable horarios = new HorariosTable();
        return "SELECT * FROM `"+ horarios.getTableName() +"`;";
    }
    
    static public Boolean isInValidSchedule(Date date){
        
        return false;
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
               "(" + getIdentifierKey() + "," +
               "" + getNameKey() + "," +
               "" + keyStartDate + "," +
               "" + keyEndDate + "," +
               "" + keyLunes + "," +
               "" + keyMartes + "," +
               "" + keyMiercoles + "," +
               "" + keyJueves + "," +
               "" + keyViernes + "," +
               "" + keySabado + "," +
               "" + keyDomingo + "," +
               "" + keyTolerancia + ")";
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
               "'" + domingo + "'," +
               "'" + tolerancia + "')";
    }
    
    public String getUpdateQuery(){
        HorariosTable horarios = new HorariosTable();
        return 
            "`"+ getKeyId() + "` = " + getIdHorario() + "," +
            "`"+ getKeyName() +"` = '" + name + "'," +
            "`" + keyStartDate + "` = '"+startDate+"'," +
            "`" + keyEndDate + "` = '"+endDate+"'," +
            "`" + keyLunes + "` = '" + lunes + "'," +
            "`" + keyMartes + "` = '" + martes + "'," +
            "`" + keyMiercoles + "` = '" + miercoles + "'," +
            "`" + keyJueves + "` = '" + jueves + "'," +
            "`" + keyViernes + "` = '" + viernes + "'," +
            "`" + keySabado + "` = '" + sabado + "'," +
            "`" + keyDomingo + "` = '" + domingo + "'," +
            "`" + keyTolerancia + "` = '" + tolerancia + "'";
    }
    
    static public String getQuerytoAllItemsByText(String text){
        HorariosTable horariosTable = new HorariosTable();
        HorariosClass horarios = new HorariosClass();
        return "SELECT * FROM " + horariosTable.getTableName() + " WHERE " + horarios.getKeyName() + " LIKE '" + text + "%'";
    }
    
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
    private LocalDateTime getLocalDateofDay(int num, int index) {
        String date = "";
        switch (num) {
            case 2:
                date = lunes;
                break;
            case 3:
                date = martes;
                break;
            case 4:
                date = miercoles;
                break;
            case 5:
                date = jueves;
                break;
            case 6:
                date = viernes;
                break;
            case 7:
                date = sabado;
                break;
            case 1:
                date = domingo;
                break;
            default:
                return null;
        }
        // CLEAN DATE
        String[] split = date.split("-");
        if (split.length > index){
            System.out.println("Hours: " + split[index]);
            return DateExtension.traducirHora(split[index]);
        }
        return null;
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
    
    /**
     * @return the tolerancia
     */
    public int getTolerancia() {
        return tolerancia;
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
    
    public void setTolerancia(int tolerancia) {
        this.tolerancia = tolerancia;
    }

    public String getKeyId() {
        return keyId;
    }

    public String getKeyName() {
        return keyName;
    }

    public String getKeyStartDate() {
        return keyStartDate;
    }

    public String getKeyEndDate() {
        return keyEndDate;
    }

    public String getKeyLunes() {
        return keyLunes;
    }

    public String getKeyMartes() {
        return keyMartes;
    }

    public String getKeyMiercoles() {
        return keyMiercoles;
    }

    public String getKeyJueves() {
        return keyJueves;
    }

    public String getKeyViernes() {
        return keyViernes;
    }

    public String getKeySabado() {
        return keySabado;
    }

    public String getKeyDomingo() {
        return keyDomingo;
    }
    
    public String getKeyTolerancia() {
        return keyTolerancia;
    }
    
}