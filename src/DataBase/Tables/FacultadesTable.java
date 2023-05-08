/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Tables;

/**
 *
 * @author Benny
 */
public class FacultadesTable implements TableProtocol {
    String tableName = "FACULTADES";
    String idKey = "idFacultades";

    @Override
    public String getTableName() {
        return this.tableName;
    }

    @Override
    public String getIdKey() {
        return this.idKey;
    }
}
