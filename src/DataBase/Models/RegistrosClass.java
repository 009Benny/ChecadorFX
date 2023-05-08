/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Models;

import DataBase.Tables.PersonasTable;
import DataBase.Tables.RegistrosTable;
import DataBase.Tables.TableProtocol;
import Models.DataBaseManager;
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
    
    public RegistrosClass(){}

    public RegistrosClass(int idRegistro, String checkDate, boolean status, int idPersona) {
        this.id = idRegistro;
        this.checkDate = checkDate;
        this.status = status;
        this.idPersona = idPersona;
    }
    
    public RegistrosClass(HashMap<String, Object> map){
        this.id = Integer.parseInt(map.get(keyId).toString());
        this.checkDate = map.get(keyCheckDate).toString();
        this.status = ((Boolean)map.get(keyStatus)).booleanValue();
        this.idPersona = Integer.parseInt(map.get(keyIdPersona).toString());
    }
    
    static public RegistrosClass getLastRegistroBy(String matricula){
        String query = RegistrosClass.getQuerytoAllItems().replace(";", "") + " WHERE id_Persona = " + matricula+ " AND checkDate = CURDATE()";
        DataBaseManager db = new DataBaseManager();
        List<HashMap<String, Object>> data = db.getDataWithQuery(query);
        int count = data.size();
        if(count > 0){
            return new RegistrosClass(data.get(count - 1));
        }
        return null;
    }
    
    static public String getQuerytoAllItems(){
        RegistrosTable registros = new RegistrosTable();
        PersonasTable personas = new PersonasTable();
        String tablePersonas = personas.getTableName();
        String tableRegistros = registros.getTableName();
        return "SELECT "+tableRegistros+"."
                +registros.getIdKey()+", "
                +tableRegistros+".checkDate, "
                +tableRegistros+".status, "
                +tableRegistros+".id_Persona, "
                +tablePersonas+".nombre FROM "
                +tableRegistros+" "
                +"INNER JOIN "+tablePersonas+" ON "+tableRegistros+".id_Persona= "+tablePersonas+"."+personas.getIdKey()+";";
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
    public String getValuesQuery() {
        return "(`" + id + "`, `" + checkDate + "`, `" + status + "`, `" + idPersona + "`)";
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
    
    

}