/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Models;

import DataBase.Tables.NivelesTable;
import DataBase.Tables.TableProtocol;

/**
 *
 * @author Benny
 */
public class NivelesClass implements ModelClassProtocol {
    int id = 0;
    String keyId = "idNiveles";
    String name = "";
    String keyName = "name";

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
    
}
