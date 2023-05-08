/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Tables;

/**
 *
 * @author Benny
 */
public class NivelesTable implements TableProtocol {
    String tableName = "NIVELES";
    String idKey = "idNiveles";

    @Override
    public String getTableName() {
        return this.tableName;
    }

    @Override
    public String getIdKey() {
        return this.idKey;
    }
}