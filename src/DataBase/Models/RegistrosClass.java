/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Models;

import DataBase.Tables.PersonasTable;
import DataBase.Tables.RegistrosTable;
import DataBase.Tables.TableProtocol;
import Extensions.DateExtension;
import Managers.DataBaseManager;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Benny
 */
public class RegistrosClass implements ModelClassProtocol {
    int id = 0;
    String keyId = "idRegistros";
    
    String checkDate;
    String keyCheckDate = "checkDate";
    
    boolean status;
    String keyStatus = "status";
    
    int idPersona;
    String keyIdPersona = "id_Persona";
    String namePersona;
    
    public RegistrosClass(){}

    public RegistrosClass(int idRegistro, String checkDate, boolean status, String matricula) {
        this.id = idRegistro;
        this.checkDate = checkDate;
        this.status = status;
        this.idPersona = Integer.parseInt(matricula);
    }
    
    public RegistrosClass(HashMap<String, Object> map){
        PersonasClass persona = new PersonasClass();
        this.id = Integer.parseInt(map.get(keyId).toString());
        this.checkDate = map.get(keyCheckDate).toString();
        this.status = ((Boolean)map.get(keyStatus)).booleanValue();
        this.idPersona = Integer.parseInt(map.get(keyIdPersona).toString());
        this.namePersona = (String) map.getOrDefault(persona.getKeyName(), "");
    }
    
    static public RegistrosClass getLastRegistroBy(String matricula){
        RegistrosClass registro = new RegistrosClass();
        String today = DateExtension.getStringDate(new Date(), "dd/MM/YYYY");
        String query = RegistrosClass.getQuerytoAllItems().replace(";", "") + " WHERE "+registro.keyIdPersona+" = " + matricula+ " AND checkDate LIKE '"+ today +"%'";
        DataBaseManager db = new DataBaseManager();
        List<HashMap<String, Object>> data = db.getDataWithQuery(query);
        int count = data.size();
        if(count > 0){
            return new RegistrosClass(data.get(count - 1));
        }
        return null;
    }
    
    static public String getQueryAllItemsWithDay(String day){
        RegistrosTable registros = new RegistrosTable();
        String tableRegistros = registros.getTableName();
        String validation = " WHERE " + tableRegistros + ".checkDate LIKE '" + day + "%'";
        return RegistrosClass.getQuerytoAllItems().replace(";", "") + validation;
    }
    
    static public String getQuerytoAllItems(){
        RegistrosTable registros = new RegistrosTable();
        PersonasTable personas = new PersonasTable();
        String tablePersonas = personas.getTableName();
        String tableRegistros = registros.getTableName();
        PersonasClass persona = new PersonasClass();
        return "SELECT "+tableRegistros+"."
                +registros.getIdKey()+", "
                +tableRegistros+".checkDate, "
                +tableRegistros+".status, "
                +tableRegistros+".id_Persona, "
                +tablePersonas+"."+persona.getKeyName()+" FROM "
                +tableRegistros+" "
                +"INNER JOIN "+tablePersonas+" ON "+tableRegistros+".id_Persona= "+tablePersonas+"."+personas.getIdKey()+";";
    }
    
    static public String getHeaderToCSV(){
        return "ID,Fecha,Status,Matricula";
    }
    
    public String getCSVLine(){
        return id + "," + checkDate + "," + status + "," + idPersona;
    }
    
    
    @Override
    public String getIdentifierKey() {
        return this.keyId;
    }

    @Override
    public String getNameKey() {
        return "";
    }
    
    @Override
    public TableProtocol getTable() {
        return new RegistrosTable();
    }

    @Override
    public String getInsertHeader(){
        return "(`" + getIdentifierKey() + "`, `" + getKeyCheckDate() + "`, `" + getKeyStatus() + "`, `" + getKeyIdPersona() + "`)";
    }
    
    @Override
    public String getValuesQuery() {
        return "(" + id + ", '" + checkDate + "', " + status + ", '" + idPersona + "')";
    }
    
    /// GETTERS AND SETTERS

    public String getKeyCheckDate() {
        return keyCheckDate;
    }

    public String getKeyStatus() {
        return keyStatus;
    }

    public String getKeyIdPersona() {
        return keyIdPersona;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public String getStatus() {
        return (status) ? "Salida" : "Entrada" ;
    }

    public String getNamePersona() {
        return namePersona;
    }
    
    public boolean isSalida(){
        return status;
    }
    
}