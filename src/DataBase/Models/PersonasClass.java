/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Models;

import DataBase.Tables.CarrerasTable;
import DataBase.Tables.FacultadesTable;
import DataBase.Tables.HorariosTable;
import DataBase.Tables.PersonasTable;
import DataBase.Tables.ServiciosTable;
import DataBase.Tables.TableProtocol;
import Models.DataBaseManager;
import java.util.HashMap;
import java.util.List;
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
        facultad = (String) map.getOrDefault("facultad", "");
        carrera = (String) map.getOrDefault("carrea", "");
        horario = (String) map.getOrDefault("horario", "");
        servicio = (String) map.getOrDefault("servicio", "");
        isValid = true;
    }
    
    static public PersonasClass getPersonaBy(String matricula){
        PersonasClass persona = new PersonasClass();
        String query = PersonasClass.getQuerytoAllItems().replace(";", "") + " WHERE " + persona.getKeyId() + " = " + matricula;
        DataBaseManager db = new DataBaseManager();
        List<HashMap<String, Object>> data = db.getDataWithQuery(query);
        if(data.size() > 0){
            return new PersonasClass(data.get(0));
        }
        return null;
    }
    
    static public String getQuerytoAllItems(){
        PersonasClass persona = new PersonasClass();
        PersonasTable personas = new PersonasTable();
        CarrerasTable carreras = new CarrerasTable();
        FacultadesTable facultades = new FacultadesTable();
        ServiciosTable servicios = new ServiciosTable();
        HorariosTable horarios = new HorariosTable();
        String tablePersonas = personas.getTableName();
        String tableCarreras = carreras.getTableName();
        String tableFacultades = facultades.getTableName();
        String tableServicios = servicios.getTableName();
        String tableHorarios = horarios.getTableName();
        return "SELECT "+tablePersonas+"."
                +personas.getIdKey()+", "
                +tablePersonas+"."+persona.getKeyPassword()+", "
                +tablePersonas+"."+persona.getNameKey()+", "
                +tablePersonas+"."+persona.getKeySemester()+", "
                +tablePersonas+"."+persona.getKeyPhone()+", "
                +tablePersonas+"."+persona.getKeyBirthDate()+", "
                +tablePersonas+"."+persona.getKeyEmail()+", "
                +tableFacultades+".name AS facultad, "
                +tableCarreras+".name AS carrera, "
                +tableHorarios+".name AS horario, "
                +tableServicios+".name AS servicio FROM "
                +tablePersonas+" "
                +"INNER JOIN "+tableCarreras+" ON "+tablePersonas+"." + persona.keyCarrera + " = "+tableCarreras+"."+carreras.getIdKey()+" "
                +"INNER JOIN "+tableFacultades+" ON "+tablePersonas+"." + persona.keyFacultad + " = "+tableFacultades+"."+facultades.getIdKey()+" "
                +"INNER JOIN "+tableServicios+" ON "+tablePersonas+"." + persona.keyServicio + " = "+tableServicios+"."+servicios.getIdKey()+" "
                +"INNER JOIN "+tableHorarios+" ON "+tablePersonas+"." + persona.keyHorario + " = "+tableHorarios+"."+horarios.getIdKey()+";";
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

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSemester() {
        return semester;
    }

    public String getPhone() {
        return phone;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
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

    public String getServicio() {
        return servicio;
    }
}