/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Models;

import DataBase.Tables.ServiciosTable;
import DataBase.Tables.TableProtocol;
import Extensions.NumberUtils;
import java.util.HashMap;
import java.util.Optional;

/**
 *
 * @author Benny
 */
public class ServiciosClass implements ModelClassProtocol, ItemListClassProtocol {
    int id = 0;
    String keyId = "idServicios";
    String name = "";
    String keyName = "name";
    
    public ServiciosClass(HashMap<String, Object> map){
        this.id = NumberUtils.getIntFrom(Optional.ofNullable(map.get(keyId).toString()).orElse("0"));
        this.name = Optional.ofNullable(map.get(keyName).toString()).orElse("");
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
        return new ServiciosTable();
    }

    @Override
    public String getValuesQuery() {
        return "(`" + id + "`, `" + name + "`)";
    }
    
    public int getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }

}
