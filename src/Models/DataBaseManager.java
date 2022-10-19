package Models;

import java.sql.*;

/**
 *
 * @author Benny
 */
public class DataBaseManager {
    static private final String DB_URL = "jdbc:mysql://localhost:3306/checador_fime";
    static private final String USER = "root";
    static private final String PASS = "Fime1671335";

    public void checkTables(){
        createCarrerasTable();
        createHorariosTable();
        createNivelesTable();
        createFacultadesTable();
        createUsersTable();
    }

    // PRIVATE METHODS

    private void executeQuery(String query){
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            System.out.println("DataBaseManager execute:");
            System.out.println(query);
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    private final void createUsersTable(){
        String query = "CREATE TABLE IF NOT EXISTS USUARIOS " +
        "(idUsuarios INT not NULL, " +
        " Nombre VARCHAR(255) not NULL, " +
        " ApPaterno VARCHAR(255) not NULL, " +
        " ApMaterno VARCHAR(255) not NULL, " +
        " PerCorreo VARCHAR(255), " +
        " InsCorreo VARCHAR(255), " +
        " Celular VARCHAR(12), " +
        " id_Carrera INT, " +
        " id_Facultad INT not NULL, " +
        " id_Horario INT not NULL, " +
        " id_Nivel INT not NULL, " +
        " CONSTRAINT fkCarrera FOREIGN KEY (id_Carrera) REFERENCES CARRERAS(idCarrera)," +
        " CONSTRAINT fkFacultad FOREIGN KEY (id_Facultad) REFERENCES FACULTADES(idFacultad)," +
        " CONSTRAINT fkHorario FOREIGN KEY (id_Horario) REFERENCES HORARIOS(idHorario)," +
        " CONSTRAINT fkNivel FOREIGN KEY (id_Nivel) REFERENCES NIVELES(idNivel)," +
        " PRIMARY KEY ( idUsuarios ))"; 
        executeQuery(query);
    }

    private final void createHorariosTable(){
        String query = "CREATE TABLE IF NOT EXISTS HORARIOS " +
        "(idHorario INT not NULL, " +
        " nombre VARCHAR(255) not NULL, " +
        " StartDate DATE, " +
        " EndDate DATE, " +
        " lunes VARCHAR(255), " +
        " martes VARCHAR(255), " +
        " miercoles VARCHAR(255), " +
        " jueves VARCHAR(255), " +
        " viernes VARCHAR(255), " +
        " sabado VARCHAR(255), " +
        " domingo VARCHAR(255), " +
        " PRIMARY KEY ( idHorario ))"; 
        executeQuery(query);
    }

    private final void createNivelesTable(){
        String query = "CREATE TABLE IF NOT EXISTS NIVELES " +
        "(idNivel INT not NULL, " +
        " nombre VARCHAR(255) not NULL, " +
        " PRIMARY KEY ( idNivel ))"; 
        executeQuery(query);
    }

    private final void createFacultadesTable(){
        String query = "CREATE TABLE IF NOT EXISTS FACULTADES " +
        "(idFacultad INT not NULL, " +
        " nombre VARCHAR(255) not NULL, " +
        " PRIMARY KEY ( idFacultad ))"; 
        executeQuery(query);
    }

    private final void createCarrerasTable(){
        String query = "CREATE TABLE IF NOT EXISTS CARRERAS " +
        "(idCarrera INT not NULL, " +
        " nombre VARCHAR(255) not NULL, " +
        " PRIMARY KEY ( idCarrera ))"; 
        executeQuery(query);
    }

}
