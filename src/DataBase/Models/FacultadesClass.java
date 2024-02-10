/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Models;

import DataBase.Tables.FacultadesTable;
import DataBase.Tables.TableProtocol;
import Extensions.NumberUtils;
import java.util.HashMap;
import java.util.Optional;

/**
 *
 * @author Benny
 */
public class FacultadesClass implements ModelClassProtocol, ItemListClassProtocol {
    int id = 0;
    String keyId = "idFacultades";
    String name = "";
    String keyName = "name";
    String abbreviation = "";
    String keyAbbreviation = "abbreviation";

    public FacultadesClass(){}
    
    public FacultadesClass(HashMap<String, Object> map){
        id = Optional.ofNullable(NumberUtils.getIntFrom(map.get(keyId).toString())).orElse(0);
        name = Optional.ofNullable(map.get(keyName).toString()).orElse("");
        abbreviation = Optional.ofNullable(map.get(keyAbbreviation).toString()).orElse("");
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
        return new FacultadesTable();
    }

    @Override
    public String getValuesQuery() {
        return "(`" + id + "`, `" + name + "`)";
    }
    
    public String getKeyAbbreviation(){
        return keyAbbreviation;
    }
    
    public String getAbbreviation(){
        return abbreviation;
    }
    
    public int getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
}
