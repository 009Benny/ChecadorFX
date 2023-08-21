/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Benny
 */
public class ConfigurationModel {
    private static ConfigurationModel instance;
    /// DATABASE
    private String dbName = "checador_fime";
    private String dbUser = "root";
    private String dbPass = "";

    private ConfigurationModel() {
        loadData();
    }
    
    private void loadData(){
    
    }

    public static ConfigurationModel getInstance() {
        if (instance == null) {
            instance = new ConfigurationModel();
        }
        return instance;
    }
}
