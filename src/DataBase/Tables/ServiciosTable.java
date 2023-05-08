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
public class ServiciosTable implements TableProtocol {
    String tableName = "SERVICIOS";
    String idKey = "idServicios";

    public int getIdOfServiceWith(String name){
        // TODO: Pendiente buscar id en base de datos
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
