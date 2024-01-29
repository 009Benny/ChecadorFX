/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Models;

import DataBase.Tables.NivelesTable;
import DataBase.Tables.TableProtocol;
import java.util.HashMap;

/**
 *
 * @author Benny
 */
public final class NivelesClass implements ModelClassProtocol {
    int id = 0;
    String keyId = "idNiveles";
    String name = "";
    String keyName = "name";

    /**
     *
     * @param map
     */
    public NivelesClass(HashMap<String, Object> map){
        this.id = (int) map.getOrDefault(getTable().getIdKey(), 0);
        this.name = (String) map.getOrDefault(keyName, "");
    }
    
    static public String getQuerytoAllItems(){
        NivelesTable niveles = new NivelesTable();
        return "SELECT * FROM `"+ niveles.getTableName() +"`";
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
        return new NivelesTable();
    }

    @Override
    public String getValuesQuery() {
        return "(`" + id + "`, `" + name + "`)";
    }

    public String getName() {
        return name;
    }
    
}
