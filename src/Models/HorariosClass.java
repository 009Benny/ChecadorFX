/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author bennyreyes
 */
public class HorariosClass {
    int idHorario;
    String name;
    Date startDate;
    Date endDate;
    String lunes;
    String martes;
    String miercoles;
    String jueves;
    String viernes;
    String sabado;
    String domingo;

    public HorariosClass(ResultSet set) throws SQLException{
        System.out.println("Horarios creation || inicio");
        idHorario = set.getInt(DataBaseManager.horarios_id);
        name = set.getString("nombre");
        startDate = set.getDate("startDate");
        endDate = set.getDate("startDate");
        lunes = set.getString("lunes");
        System.out.println("Horarios creation || al medio");
        martes = set.getString("martes");
        miercoles = set.getString("miercoles");
        jueves = set.getString("jueves");
        viernes = set.getString("viernes");
        sabado = set.getString("sabado");
        domingo = set.getString("domingo");
        System.out.println("Horarios creation || termino");
    }
    
    public String getName(){
        return name;
    }
    
    public int getId(){
        return idHorario;
    }
}
