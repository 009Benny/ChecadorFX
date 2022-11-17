/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author bennyreyes
 */
public class RegistroClass {
    int idRegistro;
    Date checkDate;
    boolean status;
    int idPersona;
    String namePersona;

    public RegistroClass(int idRegistro, Date checkDate, boolean status, int idPersona) {
        this.idRegistro = idRegistro;
        this.checkDate = checkDate;
        this.status = status;
        this.idPersona = idPersona;
        this.namePersona = "";
    }
    
    public RegistroClass(HashMap<String, Object> map){
        this.idRegistro = Integer.parseInt(map.get(DataBaseManager.registros_id).toString());
        this.checkDate = new Date();
        this.status = false;
        //this.checkDate = map.get("checkDate");
        //this.status = map.get("status");
        this.idPersona = Integer.parseInt(map.get("id_Persona").toString());
        this.namePersona = map.get("nombre").toString();
    }
    
    static public String getQuerytoAllItems(){
        String tablePersonas = DataBaseManager.personas_table;
        String tableRegistros = DataBaseManager.registros_table;
        return "SELECT "+tableRegistros+"."
                +DataBaseManager.registros_id+", "
                +tableRegistros+".checkDate, "
                +tableRegistros+".status, "
                +tableRegistros+".id_Persona, "
                +tablePersonas+".nombre FROM "
                +tableRegistros+" "
                +"INNER JOIN "+tablePersonas+" ON "+tableRegistros+".id_Persona= "+tablePersonas+"."+DataBaseManager.personas_id+";";
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public String getStatus() {
        return (status) ? "Entrada" : "Salida";
    }

    public String getNamePersona() {
        return namePersona;
    }
    
}
