/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Extensions.DateExtension;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
        String date = map.get("checkDate").toString();
        if (date.length() > 0){
            System.out.println("FECHAAAAAA" + date);
            Date newDate = DateExtension.getDateByString(date, "yyyy-MM-dd");
            this.checkDate = (newDate != null) ? newDate : new Date();
        }else{
            this.checkDate = new Date();
        }
        this.status = ((Boolean)map.get("status")).booleanValue();
        this.idPersona = Integer.parseInt(map.get("id_Persona").toString());
        this.namePersona = map.get("nombre").toString();
    }
    
    static public RegistroClass getLastRegistroBy(String matricula){
        String query = RegistroClass.getQuerytoAllItems().replace(";", "") + " WHERE id_Persona = " + matricula+ " AND checkDate = CURDATE()";
        DataBaseManager db = new DataBaseManager();
        List<HashMap<String, Object>> data = db.getDataWithQuery(query);
        int count = data.size();
        if(count > 0){
            return new RegistroClass(data.get(count - 1));
        }
        return null;
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
    
    static public String getQueryFields(){
        return "`idRegistro`, `checkDate`, `status`, `id_Persona`";
    }
    
    public String getQueryValues(){
        String newStatus = status ? "1":"0";
        return "'"+idRegistro+"', '"+DateExtension.getStringDate(checkDate, "YYYYY-MM-dd")+"', '"+newStatus+"', '"+idPersona+"'";
    }
    

    public Date getCheckDate() {
        return checkDate;
    }

    public String getStatus() {
        return (status) ? "Salida" : "Entrada";
    }
    
    public boolean isSalida(){
        return status;
    }

    public String getNamePersona() {
        return namePersona;
    }
    
}
