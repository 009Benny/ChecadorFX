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
import DataBase.DataBaseManager;
import Extensions.StringExtension;
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
    boolean havePhoto;
    String keyHavePhoto = "havePhoto";
    int status;
    String keyStatus = "status";
    String expirationDate;
    String keyExpirationDate = "expirationDate";
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
        System.out.println("Tamano de csvLine: " + split.length); // DEBEN SER 13
        isValid = split.length == 13;
        if (isValid){
            id = Optional.ofNullable(Integer.valueOf(split[0])).orElse(0);
            password = Optional.ofNullable(split[1]).orElse("password");
            name = Optional.ofNullable(split[2]).orElse("");
            semester = Optional.ofNullable(Integer.valueOf(split[3])).orElse(1);
            phone = Optional.ofNullable(split[4]).orElse("");
            birthDate = Optional.ofNullable(split[5]).orElse("");
            email = Optional.ofNullable(split[6]).orElse("");
            String auxPhoto = Optional.ofNullable(split[7]).orElse("");
            havePhoto = auxPhoto.equals("1");
            expirationDate = Optional.ofNullable(split[8]).orElse("");
            idFacultad = Optional.ofNullable(Integer.valueOf(split[9])).orElse(0);
            idCarrera = Optional.ofNullable(Integer.valueOf(split[10])).orElse(0);
            idHorario = Optional.ofNullable(Integer.valueOf(split[11])).orElse(0);
            idServicio = Optional.ofNullable(Integer.valueOf(split[12])).orElse(0);
            
            status = 0;
            if (id <= 0 || name.length() <= 0 || semester < 0 || semester > 10){
                lineFailure = csvLine;
            }
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
        havePhoto = (boolean) map.get(keyHavePhoto);
        status = Integer.parseInt(map.get(keyStatus).toString());
        expirationDate = map.get(keyExpirationDate).toString();;
        facultad = (String) map.getOrDefault("facultad", "");
        carrera = (String) map.getOrDefault("carrea", "");
        horario = (String) map.getOrDefault("horario", "");
        servicio = (String) map.getOrDefault("servicio", "");
        isValid = true;
    }
    
    static public PersonasClass getPersonaBy(String matricula){
        String query = PersonasClass.getQuerytoAllItemsByMatricula(matricula).replace("LIKE", "=");
        DataBaseManager db = new DataBaseManager();
        List<HashMap<String, Object>> data = db.getDataWithQuery(query);
        if(data.size() > 0){
            return new PersonasClass(data.get(0));
        }
        return null;
    }
    
    static public String getQuerytoAllItemsByMatricula(String matricula){
        PersonasClass persona = new PersonasClass();
        PersonasTable tablePersonas = new PersonasTable();
        return PersonasClass.getQuerytoAllItems().replace(";", "") + " WHERE " + tablePersonas.getTableName() + "." + persona.getKeyId() + " LIKE " + matricula;
    }
    
    
    static public String getQuerytoAllItemsByText(String text){
        PersonasClass persona = new PersonasClass();
        PersonasTable tablePersonas = new PersonasTable();
        return PersonasClass.getQuerytoAllItems().replace(";", "") + " WHERE " + tablePersonas.getTableName() + "." + persona.getNameKey() + " LIKE " + text;
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
                +tablePersonas+"."+persona.getKeyHavePhoto()+", "
                +tablePersonas+"."+persona.getKeyStatus()+", "
                +tablePersonas+"."+persona.getKeyExpirationDate()+", "
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
    public String getInsertHeader(){
        String header = "(`" + getTable().getIdKey() + "`,"
                + " `" + keyPassword + "`,"
                + " `" + getNameKey() + "`,"
                + " `" + keySemester + "`,"
                + " `" + keyPhone + "`,"
                + " `" + keyBirthDate + "`,"
                + " `" + keyEmail + "`,"
                + " `" + keyHavePhoto + "`,"
                + " `" + keyStatus + "`,"
                + " `" + keyExpirationDate + "`,"
                + " `" + keyFacultad + "`,"
                + " `" + keyCarrera + "`,"
                + " `" + keyHorario + "`,"
                + " `" + keyServicio + "`)";
        return header;
    }
    
    @Override
    public String getValuesQuery() {
        String photo = (havePhoto)?"1":"0";
        return 
               "('" + id + "'," +
               "'" + password + "'," +
               "'" + name + "'," +
               "'" + semester + "'," +
               "'" + phone + "'," +
               "'" + birthDate + "'," +
               "'" + email + "'," +
               "" + photo + "," +
               "'" + status + "'," +
               "'" + expirationDate + "'," +
               "'" + idFacultad + "'," +
               "'" + idCarrera + "'," +
               "'" + idHorario + "'," +
               "'" + idServicio + "')";
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
    
    public String getKeyHavePhoto(){
        return keyHavePhoto;
    }

    public String getKeyStatus(){
        return keyStatus;
    }
    
    public String getKeyExpirationDate(){
        return keyExpirationDate;
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

    public String getLineFailure() {
        return lineFailure;
    }
    
}