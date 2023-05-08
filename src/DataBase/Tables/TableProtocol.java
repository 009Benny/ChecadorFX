/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Tables;

import Models.DataBaseManager;

/**
 *
 * @author Benny
 */
public interface TableProtocol {
    String getTableName();
    String getIdKey();
    
    default String getAllItemsQuery(){
        String query = "SELECT * FROM `" + getTableName() + "`";
        return query;
    }
    
    default String getCreationTableQuery(){
        String query = "CREATE TABLE IF NOT EXISTS " + getTableName() + " " +
        "(" + getIdKey() + " INT not NULL, " +
        " name VARCHAR(255) not NULL, " +
        " PRIMARY KEY ( " + getIdKey() + " ))"; 
        return query;
    }
    
    default String getHeadersQuery(){
     String query = "(`" + getIdKey() + "`, `name`)";
        return query;
    }
}
