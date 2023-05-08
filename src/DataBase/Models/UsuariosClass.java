/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Models;

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
    String keyIdNivel = "id_Nivel";

    public UsuariosClass(){}
    
    public UsuariosClass(HashMap<String, Object> map){
        this.id = Integer.parseInt(map.get(getTable().getIdKey()).toString());
        this.name = map.get(getNameKey()).toString();
        this.password = map.get(keyPassword).toString();
        this.idNivel = Integer.parseInt(map.get(keyIdNivel).toString());
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
        return new UsuariosTable();
    }

    @Override
    public String getValuesQuery() {
        return "(`" + id + "`, `" + name + "`)";
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

}