/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Tables;

/**
 *
 * @author Benny
 */
public class CarrerasTable implements TableProtocol {
    String tableName = "CARRERAS";
    String idKey = "idCarreras";

    
    public int getIdWith(String abbreviation){
        // TODO: Agregar buscador en base de datos
        return 1;
    }
    
    @Override
    public String getTableName() {
        return this.tableName;
    }

    @Override
    public String getIdKey() {
        return this.idKey;
    }
}