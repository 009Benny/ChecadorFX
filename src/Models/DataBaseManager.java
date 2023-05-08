package Models;

import Models.DefaultData;
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
    static private final String DB_NAME = "checador_fime";
    static private final String USER = "root";
    static private final String PASS = "";
    
    
    public String getUrlDataBase(){
       return "jdbc:mysql://localhost:3306/" + DataBaseManager.DB_NAME;
    }
    
    public static String getDataBaseName(){
        return DB_NAME;
    }
    
    static public void createDataBaseIfDoesntExist() throws SQLException{
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", USER, PASS);
        String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
        Statement statement = connection.createStatement();
        statement.execute(createDatabaseQuery);
    }
    
    public void executeQuery(String query){
        try(Connection conn = DriverManager.getConnection(getUrlDataBase(), USER, PASS);
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
        try(Connection conn = DriverManager.getConnection(getUrlDataBase(), USER, PASS);
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
        try(Connection conn = DriverManager.getConnection(getUrlDataBase(), USER, PASS);
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
        try(Connection conn = DriverManager.getConnection(getUrlDataBase(), USER, PASS);
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
