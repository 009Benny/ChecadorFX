/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Tables;

import DataBase.Models.UsuariosClass;

/**
 *
 * @author Benny
 */
public class UsuariosTable implements TableProtocol {
    String tableName = "USUARIOS";
    String idKey = "idUsuarios";
    String fkNivel = "fkNivel";
    
    @Override
    public String getCreationTableQuery(){
        UsuariosClass usuario = new UsuariosClass();
        NivelesTable niveles = new NivelesTable();
        String query = "CREATE TABLE IF NOT EXISTS " + getTableName() + " " +
        "(" + getIdKey() + " INT not NULL, " +
        " " + usuario.getNameKey() + " VARCHAR(255) not NULL, " +
        " " + usuario.getKeyPassword() + " VARCHAR(255) not NULL, " +
        " " + usuario.getKeyIdNivel() + " Int not NULL, " +
        " CONSTRAINT " + fkNivel + " FOREIGN KEY (" + usuario.getKeyIdNivel() + ") REFERENCES NIVELES(" + niveles.getIdKey() + ")," +
        " PRIMARY KEY ( " + getIdKey() + " ))"; 
        return query;
    }
    
    @Override
    public String getHeadersQuery(){
        UsuariosClass usuario = new UsuariosClass();
        return "(`" + getIdKey() + "`, `" + usuario.getNameKey() + "`, `" + usuario.getKeyPassword() + "`, `" + usuario.getKeyIdNivel() + "`)";
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