package Managers;

import DataBase.Configuration.DataBaseConfiguration;
import DataBase.Models.FacultadesClass;
import DataBase.Tables.FacultadesTable;
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
    private String JDBC_URL = "jdbc:mysql://localhost:3306/";
    String dbName = "";
    String dbUser = "";
    String dbPass = "";
    
    /**
     * Constructs a new DataBaseManager instance and initializes the database
     * configuration from the singleton DataBaseConfiguration instance.
     */
    public DataBaseManager(){
        DataBaseConfiguration configuration = DataBaseConfiguration.getInstance();
        this.dbName = configuration.getName();
        this.dbUser = configuration.getUser();
        this.dbPass = configuration.getPass();
    }
    
    public boolean isValidConnection(String user, String pass){
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, user, pass);
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean isConnected(){
        boolean isConnected = false;
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, dbUser, dbPass);
            connection.close();
            isConnected = true;
        } catch (SQLException e) {
            isConnected = false;
        }
        return isConnected;
    }
    
    /**
     * Constructs and returns the URL for connecting to the database.
     *
     * @return The URL string for connecting to the database.
     */
    public String getUrlDataBase(){
       return JDBC_URL + dbName;
    }
    
    /**
     * Creates the database if it doesn't already exist.
     *
     * @throws SQLException If there is an error executing the SQL query.
     */
    public void createDataBaseIfDoesntExist() throws SQLException {
        // Establish a connection to the MySQL server
        Connection connection = DriverManager.getConnection(JDBC_URL, dbUser, dbPass);

        // SQL query to create the database if it doesn't exist
        String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + dbName;

        // Create a statement and execute the query
        Statement statement = connection.createStatement();
        statement.execute(createDatabaseQuery);

        // Close the statement and connection
        statement.close();
        connection.close();
    }
    
    /**
     * Executes the given SQL query.
     *
     * @param query The SQL query to be executed.
     */
    public void executeQuery(String query){
        try(Connection conn = DriverManager.getConnection(getUrlDataBase(), dbUser, dbPass);
            Statement stmt = conn.createStatement();
        ) {
            System.out.println("DataBaseManager execute:");
            System.out.println(query);
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            Logger.getLogger(DefaultData.class.getName()).log(Level.SEVERE, null, e);
        } 
    }
    
    /**
     * Retrieves data from the specified table.
     *
     * @param table The name of the table to retrieve data from.
     * @return A List of HashMaps containing the retrieved data.
     */
    public List<HashMap<String, Object>> getData(String table){
        String query = "SELECT * FROM "+ table +";";
        try(Connection conn = DriverManager.getConnection(getUrlDataBase(), dbUser, dbPass);
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
    
    /**
     * Retrieves data from the database using a custom query.
     *
     * @param query The custom SQL query to retrieve data.
     * @return A List of HashMaps containing the retrieved data.
     */
    public List<HashMap<String, Object>> getDataWithQuery(String query){
        System.out.println(query);
        try(Connection conn = DriverManager.getConnection(getUrlDataBase(), dbUser, dbPass);
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
    
    /**
     * Casts the data from a ResultSet into a List of HashMaps.
     *
     * @param data The ResultSet containing the data to be cast.
     * @return A List of HashMaps containing the casted data.
     * @throws SQLException If there is an error while processing the ResultSet.
     */
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
        executeQuery("INSERT INTO " + table + " " + fields + " VALUES " + values + ";");
    }
    
    public int getCountOf(String table){
        String query = "SELECT COUNT(*) FROM "+ table +";";
        try(Connection conn = DriverManager.getConnection(getUrlDataBase(), dbUser, dbPass);
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
    
    public static List<String> getFacultadesList(){
        DataBaseManager db = new DataBaseManager();
        List<String> list = new ArrayList<>();
        FacultadesTable facultadesTable = new FacultadesTable();
        List<HashMap<String, Object>> data = db.getDataWithQuery(facultadesTable.getAllItemsQuery());
        if (!data.isEmpty()){
            for(HashMap<String, Object> map:data){
                FacultadesClass facultad = new FacultadesClass(map);
                list.add(facultad.getAbbreviation());
            }
            return list;
        }else{
            System.out.println("LOAD DATA SEARCH: NO REGRESA INFO");
        }
        return list;
    }
}
