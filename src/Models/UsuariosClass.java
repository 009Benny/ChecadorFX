/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.HashMap;

/**
 *
 * @author bennyreyes
 */
public class UsuariosClass {
    int idUsuario;
    String nombre;
    String password;
    int idNivel;
    
    public UsuariosClass(HashMap<String, Object> map){
        this.idUsuario = Integer.parseInt(map.get(DataBaseManager.usuarios_id).toString());
        this.nombre = map.get("nombre").toString();
        this.password = map.get("password").toString();
        this.idNivel = Integer.parseInt(map.get("id_Nivel").toString());
    }
    
    static public String getQuerytoAllItems(){
        return "SELECT * FROM " + DataBaseManager.usuarios_table;
    }
    
    // GETTERS && SETTERS

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdNivel() {
        return idNivel;
    }
    
}
