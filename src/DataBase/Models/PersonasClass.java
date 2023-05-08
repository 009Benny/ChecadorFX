/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Models;

import DataBase.Tables.CarrerasTable;
import DataBase.Tables.PersonasTable;
import DataBase.Tables.TableProtocol;
import java.util.HashMap;
import java.util.Optional;

/**
 *
 * @author Benny
 */
public class PersonasClass implements ModelClassProtocol {
    int id = 0;
    String keyId = "idPersonas";
    String name;
    String keyName = "name";
    String password;
    String keyPassword = "password";
    int semester;
    String keySemester = "semester";
    String phone;
    String keyPhone = "phone";
    String birthDate;
    String keyBirthDate = "birthDate";
    String email;
    String keyEmail = "email";
    String carrera;
    int idCarrera;
    String keyCarrera = "id_Carrera";
    String facultad;
    int idFacultad;
    String keyFacultad = "id_Facultad";
    String horario;
    int idHorario;
    String keyHorario = "id_Horario";
    int idServicio;
    String servicio;
    String keyServicio = "id_Servicio";
    Boolean isValid;
    String lineFailure;
    
    // INIT DEFAULT FORMAT
    public PersonasClass(){}
    
    public PersonasClass(String csvLine){
        String[] split = csvLine.split(",");
        System.out.println("Tamano de csvLine: " + split.length);
        isValid = split.length == 10;
        if (isValid){
            id = Optional.ofNullable(Integer.valueOf(split[1])).orElse(0);
            name = Optional.ofNullable(split[3]).orElse("");
            password = "password";
            semester = Optional.ofNullable(Integer.valueOf(split[8])).orElse(1);
            birthDate = Optional.ofNullable(split[10]).orElse("");
            
            String carrera = Optional.ofNullable(split[12]).orElse("");
            CarrerasTable carreras = new CarrerasTable();
            idCarrera =  carreras.getIdWith(carrera);
            
            idFacultad = 1;
            idHorario = 1;
            idServicio = 1;
            
        }else{
            lineFailure = csvLine;
        }
    }
    
    public PersonasClass(HashMap<String, Object> map){
        id = Integer.parseInt(map.get(keyId).toString());
        password = map.get(keyPassword).toString();
        name = map.get(keyName).toString();
        semester = Integer.parseInt(map.get(keySemester).toString());
        phone =  map.get(keyPhone).toString();
        birthDate =  map.get(keyBirthDate).toString();
        email =  map.get(keyEmail).toString();
        facultad =  map.get(keyFacultad).toString();
        map.get(keyName).toString();
        isValid = true;
    }
    
    // REVISAR SI SE UTILIZARA
    static public PersonasClass getPersonaBy(String matricula){
//        String query = PersonasClass.getQuerytoAllItems().replace(";", "") + " WHERE " + keyId + " = " + matricula;
//        DataBaseManager db = new DataBaseManager();
//        List<HashMap<String, Object>> data = db.getDataWithQuery(query);
//        if(data.size() > 0){
//            return new PersonasClass(data.get(0));
//        }
//        return null;
        return null;
    }
    
    static public String getQuerytoAllItems(){
//        String tablePersonas = DataBaseManager.personas_table;
//        String tableCarreras = DataBaseManager.carreras_table;
//        String tableFacultades = DataBaseManager.facultades_table;
//        String tableDeportes = DataBaseManager.deportes_table;
//        String tableHorarios = DataBaseManager.horarios_table;
//        return "SELECT "+tablePersonas+"."
//                +DataBaseManager.personas_id+", "
//                +tablePersonas+".nombre, "
//                +tablePersonas+".apPaterno, "
//                +tablePersonas+".apMaterno, "
//                +tablePersonas+".perCorreo, "
//                +tablePersonas+".insCorreo, "
//                +tablePersonas+".celular, "
//                +tableCarreras+".carrera, "
//                +tableFacultades+".facultad, "
//                +tableDeportes+".deporte, "
//                +tableHorarios+".horario FROM "
//                +tablePersonas+" "
//                +"INNER JOIN "+tableCarreras+" ON "+tablePersonas+".id_Carrera = "+tableCarreras+"."+DataBaseManager.carreras_id+" "
//                +"INNER JOIN "+tableFacultades+" ON "+tablePersonas+".id_Facultad = "+tableFacultades+"."+DataBaseManager.facultades_id+" "
//                +"INNER JOIN "+tableDeportes+" ON "+tablePersonas+".id_Deporte = "+tableDeportes+"."+DataBaseManager.deportes_id+" "
//                +"INNER JOIN "+tableHorarios+" ON "+tablePersonas+".id_Horario = "+tableHorarios+"."+DataBaseManager.horarios_id+";";
        return "";
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
        return new PersonasTable();
    }

    @Override
    public String getValuesQuery() {
        return "(`" + id + "`, `" + name + "`)";
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public String getKeyId() {
        return keyId;
    }

    public String getKeyName() {
        return keyName;
    }

    public String getKeyPassword() {
        return keyPassword;
    }

    public String getKeySemester() {
        return keySemester;
    }

    public String getKeyPhone() {
        return keyPhone;
    }

    public String getKeyBirthDate() {
        return keyBirthDate;
    }

    public String getKeyEmail() {
        return keyEmail;
    }

    public String getKeyCarrera() {
        return keyCarrera;
    }

    public String getKeyFacultad() {
        return keyFacultad;
    }

    public String getKeyHorario() {
        return keyHorario;
    }

    public String getKeyServicio() {
        return keyServicio;
    }
    
    

}