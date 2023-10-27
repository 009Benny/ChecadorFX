/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Configuration;

import DataBase.DataBaseManager;
import Modules.RequestConfiguration.DataBaseException;
import Extensions.DateExtension;
import Managers.FileManager;
import Managers.FileManagerException;
import Models.DefaultData;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Benny
 * Singleton class that handles the configuration of a database connection.
 */
public class DataBaseConfiguration {
    
    private static DataBaseConfiguration instance;
    
    private String FILE_NAME = "DBConfiguration.txt";
    private String name = "";
    private String user = "";
    private String pass = "";

    /**
     * Private constructor to prevent direct instantiation.
     */
    private DataBaseConfiguration() {
        // Private constructor to enforce Singleton pattern
        try {
            readConfiguration();
        } catch (FileManagerException e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Returns the singleton instance of DataBaseConfiguration.
     *
     * @return The singleton instance of DataBaseConfiguration.
     */
    public static DataBaseConfiguration getInstance() {
        if (instance == null) {
            instance = new DataBaseConfiguration();
        }
        return instance;
    }
    
    /**
     * Reads the database configuration from a file and sets the internal values.
     *
     * @throws FileManagerException If there is an issue reading the configuration file.
     */
    public void readConfiguration() throws FileManagerException {
        String path = getConfigurationPath();
        ArrayList<String> list = FileManager.readLinesFromFile(path);
        if (list.size() == 3){
            name = list.get(0);
            user = list.get(1);
            pass = list.get(2);
        }
    }
    
    /**
     * Writes the configuration data to a file at the specified path. If the name and user are not blank,
     * the data is written to the file, overwriting the existing content.
     *
     * @param name The name to be written to the configuration file.
     * @param user The user to be written to the configuration file.
     * @param password The password to be written to the configuration file.
     */
    public void writeConfiguration(String name, String user, String password) throws FileManagerException, DataBaseException {
        String path = getConfigurationPath();
        if (!name.isBlank() && !user.isBlank()){
            DataBaseManager manager = new DataBaseManager();
            if (manager.isValidConnection(user, password)){
                ArrayList<String> data = new ArrayList<>(Arrays.asList(name, user, password));
                FileManager.writeArrayListToFile(data, path, true);
                this.name = name;
                this.user = user;
                this.pass = password;
                loadData();
            }else{
                throw new DataBaseException("Conexion invalida");
            }
        }else{
            throw new DataBaseException("El nombre de la base de datos y el usuario no pueden estar en blanco");
        }
    }
    
    /**
     * Checks if the configuration contains valid values.
     *
     * @return True if the configuration is valid, false otherwise.
     */
    public boolean isValid(){
        return !(name.isBlank() && user.isBlank());
    }
    
    /*
    * This method is to call all methods to load data in our local
    */
    public void loadData(){
        DateExtension.valideTimeZone();
        DefaultData def = new DefaultData();
        def.checkAll();
    }
    
    /**
     * Generates the path to the configuration file.
     *
     * @return The path to the configuration file.
     */
    private String getConfigurationPath(){
        return System.getProperty("user.dir") + "/src/Data/" + FILE_NAME;
    }

    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
    
}
