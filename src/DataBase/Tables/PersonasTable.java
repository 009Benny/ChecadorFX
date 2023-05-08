/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Tables;

import DataBase.Models.PersonasClass;

/**
 *
 * @author Benny
 */
public class PersonasTable implements TableProtocol {
    String tableName = "PERSONAS";
    String idKey = "idPersonas";
    String fkCarrera = "fkCarrera";
    String fkFacultad = "fkFacultad";
    String fkHorario = "fkHorario";
    String fkServicio = "fkServicio";
    
    @Override
    public String getCreationTableQuery(){
        FacultadesTable facultades = new FacultadesTable();
        ServiciosTable servicios = new ServiciosTable();
        CarrerasTable carreras = new CarrerasTable();
        HorariosTable horarios = new HorariosTable();
        PersonasClass persona = new PersonasClass();
        String query = "CREATE TABLE IF NOT EXISTS " + getTableName() + " " +
        "(" + getIdKey() + " INT not NULL, " +
        " " + persona.getKeyPassword() + " VARCHAR(255) not NULL, " +
        " " + persona.getKeyName() + " VARCHAR(255) not NULL, " +
        " " + persona.getKeySemester() + " INT, " +
        " " + persona.getKeyPhone() + " VARCHAR(255), " +
        " " + persona.getKeyBirthDate() + " VARCHAR(255), " +
        " " + persona.getKeyEmail() + " VARCHAR(255), " +
        " " + persona.getKeyFacultad() + " INT not NULL, " +
        " " + persona.getKeyCarrera() + " INT, " +
        " " + persona.getKeyHorario() + " INT not NULL, " +
        " " + persona.getKeyServicio() + " INT, " +
        " CONSTRAINT " + fkCarrera + " FOREIGN KEY (" + persona.getKeyCarrera() + ") REFERENCES " + carreras.getTableName() + "(" + carreras.getIdKey() + ")," +
        " CONSTRAINT " + fkFacultad + " FOREIGN KEY (" + persona.getKeyFacultad() + ") REFERENCES " + facultades.getTableName() + "(" + facultades.getIdKey() + ")," +
        " CONSTRAINT " + fkHorario + " FOREIGN KEY (" + persona.getKeyHorario() + ") REFERENCES " + horarios.getTableName() + "(" + horarios.getIdKey() + ")," +
        " CONSTRAINT " + fkServicio + " FOREIGN KEY (" + persona.getKeyServicio() + ") REFERENCES " + servicios.getTableName() + "(" + servicios.getIdKey() + ")," +
        " PRIMARY KEY ( " + getIdKey() + " ))";
        return query;
    }
    
    @Override
    public String getTableName() {
       return this.tableName;
    }

    @Override
    public String getIdKey() {
        return this.idKey;
    }
    
}