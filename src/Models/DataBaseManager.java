package Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Benny
 */
public class DataBaseManager {
    static private final String DB_URL = "jdbc:mysql://localhost:3306/checador_fime";
    static private final String USER = "root";
    static private final String PASS = "";
    // DB KEYS
    static public String registros_table = "REGISTROS";
    static public String registros_id = "idRegistro";
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
    

    public List<HashMap<String, Object>> getData(String table){
        String query = "SELECT * FROM "+ table +";";
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("DataBaseManager getData execute:");
            System.out.println(query);
            List<HashMap<String, Object>> list = castData(rs);
            stmt.close();
            return list;
        } catch (SQLException e) {
            Logger.getLogger(DefaultData.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public List<HashMap<String, Object>> getDataWithQuery(String query){
        System.out.println(query);
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("DataBaseManager getData execute:");
            System.out.println(query);
            List<HashMap<String, Object>> list = castData(rs);
            stmt.close();
            return list;
        } catch (SQLException e) {
            Logger.getLogger(DefaultData.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    
    
    private List<HashMap<String, Object>> castData(ResultSet data) throws SQLException{
        List<HashMap<String, Object>> list = new ArrayList();
        ResultSetMetaData meta = data.getMetaData();
        while(data.next()){
            HashMap<String, Object> map = new HashMap<>();
            for(int i=1;i<=meta.getColumnCount();i++){
                String key = meta.getColumnName(i);
                map.put(key, data.getObject(key));
            }
            list.add(map);
        }
        return list;
    }
    
    public void deleteItem(String table, String condition){;
        executeQuery("DELETE FROM `" + table + "` WHERE " + condition);
    }
    
    public void updateItem(String table, String itemUpdated, String condition){
        executeQuery("UPDATE " + table + " SET " + itemUpdated + " WHERE " + condition);
    }
    
    public void createItem(String table, String fields, String values){
        executeQuery("INSERT INTO " + table + " (" + fields + ") VALUES (" + values + ");");
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
