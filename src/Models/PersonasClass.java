/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author bennyreyes
 */
public class PersonasClass {
    int idPersona;
    String nombre;
    String apPaterno;
    String apMaterno;
    String perEmail;
    String instEmail;
    String phone;
    String carrera;
    String facultad;
    String horario;
    
    public PersonasClass(HashMap<String, Object> map){
        System.out.println("DATA");
        System.out.print(map);
        idPersona = Integer.parseInt(map.get(DataBaseManager.personas_id).toString());
        nombre = map.get("nombre").toString();
        apPaterno = map.get("apPaterno").toString();
        apMaterno = map.get("apMaterno").toString();
        perEmail = map.get("perCorreo").toString();
        instEmail = map.get("insCorreo").toString();
        phone = map.get("celular").toString();
        carrera = map.get("carrera").toString();
        facultad = map.get("facultad").toString();
        horario = map.get("horario").toString();
    }
    
    static public String getQuerytoAllItems(){
        String tablePersonas = DataBaseManager.personas_table;
        String tableCarreras = DataBaseManager.carreras_table;
        String tableFacultades = DataBaseManager.facultades_table;
        String tableHorarios = DataBaseManager.horarios_table;
        return "SELECT "+tablePersonas+"."
                +DataBaseManager.personas_id+", "
                +tablePersonas+".nombre, "
                +tablePersonas+".apPaterno, "
                +tablePersonas+".apMaterno, "
                +tablePersonas+".perCorreo, "
                +tablePersonas+".insCorreo, "
                +tablePersonas+".celular, "
                +tableCarreras+".carrera, "
                +tableFacultades+".facultad, "
                +tableHorarios+".horario FROM "
                +tablePersonas+" "
                +"INNER JOIN "+tableCarreras+" ON "+tablePersonas+".id_Carrera = "+tableCarreras+"."+DataBaseManager.carreras_id+" "
                +"INNER JOIN "+tableFacultades+" ON "+tablePersonas+".id_Facultad = "+tableFacultades+"."+DataBaseManager.facultades_id+" "
                +"INNER JOIN "+tableHorarios+" ON "+tablePersonas+".id_Horario = "+tableHorarios+"."+DataBaseManager.horarios_id+";";
    }

    public int getIdPersona() {
        return idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public String getPerEmail() {
        return perEmail;
    }

    public String getInstEmail() {
        return instEmail;
    }

    public String getPhone() {
        return phone;
    }

    public String getCarrera() {
        return carrera;
    }

    public String getFacultad() {
        return facultad;
    }

    public String getHorario() {
        return horario;
    }
    
}
