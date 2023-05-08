/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Models;

import DataBase.Tables.TableProtocol;

/**
 *
 * @author Benny
 */
public interface ModelClassProtocol {
    String getIdentifierKey();
    String getNameKey();
    TableProtocol getTable();
    String getValuesQuery();
    
    default String getInsertHeader(){
        String header = "(`" + getTable().getIdKey() + "`, `" + getNameKey() + "`)";
        return header;
    }
    
}
