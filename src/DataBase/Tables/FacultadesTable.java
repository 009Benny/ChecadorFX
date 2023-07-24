/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Tables;

import DataBase.Models.FacultadesClass;

/**
 *
 * @author Benny
 */
public class FacultadesTable implements TableProtocol {
    String tableName = "FACULTADES";
    String idKey = "idFacultades";

    @Override
    public String getTableName() {
        return this.tableName;
    }

    @Override
    public String getIdKey() {
        return this.idKey;
    }
    
    @Override
    public String getCreationTableQuery(){
        String query = "CREATE TABLE IF NOT EXISTS " + getTableName() + " " +
        "(" + getIdKey() + " INT not NULL, " +
        " name VARCHAR(255) not NULL, " +
        " abbreviation VARCHAR(255), " +
        " PRIMARY KEY ( " + getIdKey() + " ))"; 
        return query;
    }
    
    @Override
     public String getHeadersQuery(){
        FacultadesClass facultad = new FacultadesClass();
        String query = "(`" + getIdKey() + "`, `"+facultad.getNameKey()+"`, `"+facultad.getKeyAbbreviation()+"`)";
        return query;
    }
}
