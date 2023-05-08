/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Models;

import DataBase.Tables.FacultadesTable;
import DataBase.Tables.TableProtocol;

/**
 *
 * @author Benny
 */
public class FacultadesClass implements ModelClassProtocol {
    int id = 0;
    String keyId = "idFacultades";
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
        return new FacultadesTable();
    }

    @Override
    public String getValuesQuery() {
        return "(`" + id + "`, `" + name + "`)";
    }
    
}
