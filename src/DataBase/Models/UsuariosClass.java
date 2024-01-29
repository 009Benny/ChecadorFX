/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Models;

import DataBase.Tables.NivelesTable;
import DataBase.Tables.TableProtocol;
import DataBase.Tables.UsuariosTable;
import java.util.HashMap;

/**
 *
 * @author Benny
 */
public class UsuariosClass implements ModelClassProtocol {
    int id = 0;
    String keyId = "idUsuarios";
    String name = "";
    String keyName = "name";
    String password;
    String keyPassword = "password";
    int idNivel;
    String nivel;
    String keyIdNivel = "id_Nivel";

    public UsuariosClass(){}
    
    public UsuariosClass(int id,String name, String password, NivelesClass nivel){
        this.id = id;
        this.name = name;
        this.password = password;
        this.idNivel = nivel.id;
        this.nivel = nivel.name;
    }
    
    public UsuariosClass(HashMap<String, Object> map){
        this.id = (int) map.getOrDefault(getTable().getIdKey(), 0);
        this.name = (String) map.getOrDefault(keyName, "");
        this.password = (String) map.getOrDefault(keyPassword, "");
        this.idNivel = (int) map.getOrDefault(keyIdNivel, 0);
        this.nivel = (String) map.getOrDefault("nivel", "");
    }
    
    static public String getQuerytoAllItems(){
        UsuariosTable usuarios = new UsuariosTable();
        UsuariosClass usuario = new UsuariosClass();
        NivelesTable niveles = new NivelesTable();
        String tableUsuarios = usuarios.getTableName();
        String tableNiveles = niveles.getTableName();
        return "SELECT "+tableUsuarios+"."
                + usuario.getIdentifierKey()+ ", "
                +tableUsuarios+"."+usuario.getNameKey()+", "
                +tableNiveles+".name AS nivel FROM "
                +tableUsuarios+" "
                +"INNER JOIN "+tableNiveles+" ON "+tableUsuarios+"." + usuario.keyIdNivel + " = "+tableNiveles+"."+niveles.getIdKey() +";";
    }
    
    @Override
    public String getInsertHeader(){
        String header = "(`" + getTable().getIdKey() + "`,"
                + " `" + getNameKey() + "`,"
                + " `" + getKeyPassword() + "`,"
                + " `" + getKeyIdNivel() + "`)";
        return header;
    }
    
    //(`idUsuarios`, `name`, `password`, `id_Nivel`)
    
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
        return new UsuariosTable();
    }

    @Override
    public String getValuesQuery() {
        return "(" + id + ", '" + name + "', '" + password + "', " + idNivel + ")";
    }

    // GETTERS && SETTERS

    public int getIdUsuario() {
        return id;
    }

    public String getNombre() {
        return name;
    }

    public int getIdNivel() {
        return idNivel;
    }

    public String getKeyPassword() {
        return keyPassword;
    }

    public String getKeyIdNivel() {
        return keyIdNivel;
    }

    public String getNivel() {
        return nivel;
    }
}