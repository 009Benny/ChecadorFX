/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Tables;

import DataBase.Models.HorariosClass;

/**
 *
 * @author Benny
 */
public class HorariosTable implements TableProtocol {
    String tableName = "HORARIOS";
    String idKey = "idHorarios";

    @Override
    public String getCreationTableQuery(){
        String query = "CREATE TABLE IF NOT EXISTS " + getTableName() + " " +
        "(" + getIdKey() + " INT not NULL, " +
        " name VARCHAR(255) not NULL, " +
        " startDate VARCHAR(255), " +
        " endDate VARCHAR(255), " +
        " lunes VARCHAR(255), " +
        " martes VARCHAR(255), " +
        " miercoles VARCHAR(255), " +
        " jueves VARCHAR(255), " +
        " viernes VARCHAR(255), " +
        " sabado VARCHAR(255), " +
        " domingo VARCHAR(255), " +
        " tolerancia INT not NULL default 0, " +
        " PRIMARY KEY ( " + getIdKey() + " ))"; 
        return query;
    }
    
    @Override
    public String getHeadersQuery(){
        HorariosClass horario = new HorariosClass();
        return "(`" + getIdKey() + "`,"
                + " `" + horario.getNameKey() + "`,"
                + " `" + horario.getKeyStartDate() + "`,"
                + " `" + horario.getKeyEndDate() + "`,"
                + " `" + horario.getKeyLunes() + "`,"
                + " `" + horario.getKeyMartes() + "`,"
                + " `" + horario.getKeyMiercoles() + "`,"
                + " `" + horario.getKeyJueves() + "`,"
                + " `" + horario.getKeyViernes() + "`,"
                + " `" + horario.getKeySabado() + "`,"
                + " `" + horario.getKeyDomingo() + "`,"
                + " `" + horario.getKeyTolerancia() + "`)";
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
