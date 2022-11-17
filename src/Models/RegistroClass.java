/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;

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
    
}
