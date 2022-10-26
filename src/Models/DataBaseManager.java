package Models;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Benny
 */
public class DataBaseManager {
    static private final String DB_URL = "jdbc:mysql://localhost:3306/checador_fime";
    static private final String USER = "root";
    static private final String PASS = "Fime1671335";
    // DB KEYS
    static public String personas_table = "PERSONAS";
    static public String personas_id = "idPersona";
    static public String horarios_table = "HORARIOS";
    static public String horarios_id = "idHorario";
    static public String carreras_table = "CARRERAS";
    static public String carreras_id = "idCarrera";
    static public String facultades_table = "FACULTADES";
    static public String facultades_id = "idFacultad";
    static public String niveles_table = "NIVELES";
    static public String niveles_id = "idNiveles";
    static public String usuarios_table = "USUARIOS";
    static public String usuarios_id = "idUsuarios";
    
    public void executeQuery(String query){
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            System.out.println("DataBaseManager execute:");
            System.out.println(query);
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            Logger.getLogger(DefaultData.class.getName()).log(Level.SEVERE, null, e);
        } 
    }
    
    public int getCountOf(String table){
        String query = "SELECT COUNT(*) FROM "+ table +";";
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("DataBaseManager execute:");
            System.out.println(query);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            Logger.getLogger(DefaultData.class.getName()).log(Level.SEVERE, null, e);
        } 
        return 0;
    }
    
}
