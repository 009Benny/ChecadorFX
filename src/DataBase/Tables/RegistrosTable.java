/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Tables;

import DataBase.Models.RegistrosClass;

/**
 *
 * @author Benny
 */
public class RegistrosTable implements TableProtocol {
    String tableName = "REGISTROS";
    String idKey = "idRegistros";
    String fkPersona = "fkPersona";
    
    @Override
    public String getCreationTableQuery(){
        PersonasTable personas = new PersonasTable();
        RegistrosClass registro = new RegistrosClass();
        String query = "CREATE TABLE IF NOT EXISTS " + getTableName() + " " +
        "(" + getIdKey() + " INT not NULL, " +
        " " + registro.getKeyCheckDate() + " VARCHAR(255) not NULL, " +
        " " + registro.getKeyStatus() + " Boolean not NULL, " +
        " " + registro.getKeyIdPersona() + " Int not NULL, " +
        " CONSTRAINT " + getFkPersona() + " FOREIGN KEY (" + registro.getKeyIdPersona() + ") REFERENCES " + personas.getTableName() + "(" + personas.getIdKey() + ")," +
        " PRIMARY KEY ( " + getIdKey() + " ))"; 
        return query;
    }
    
    @Override
    public String getTableName() {
       return this.tableName;
    }

    @Override
    public String getIdKey() {
        return this.idKey;
    }
    
    private String getFkPersona(){
        return this.fkPersona;
    }
    
}
