/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bennyreyes
 */
public class DefaultData {
    DataBaseManager manager;
    
    public DefaultData(){
        manager = new DataBaseManager();
    }
    
    public void checkAll(){
        checkFacultades();
        checkCarreras();
    }
    
    public void checkFacultades(){
        int count = manager.getCountOf(DataBaseManager.facultades_name);
        System.out.println("Cantidad de lineas " + count);
        if (count == 0) {
            try {
                ArrayList<String> facultades = FileManager.getData("src/Data/Facultades.txt");
                String query = "INSERT INTO `checador_fime`.`" + DataBaseManager.facultades_name + "` (`idFacultad`, `nombre`) VALUES";
                int identifier = 1;

                for(String facultad : facultades){
                    query += " ("+ identifier +", '"+ facultad +"')";
                    query += (identifier != facultades.size()) ? "," : "";
                    identifier ++;
                }
                manager.executeQuery(query);
            } catch (IOException ex) {
                Logger.getLogger(DefaultData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void checkCarreras(){
        int count = manager.getCountOf(DataBaseManager.carreras_name);
        System.out.println("Cantidad de lineas " + count);
        if (count == 0) {
            try {
                ArrayList<String> carreras = FileManager.getData("src/Data/Facultades.txt");
                String query = "INSERT INTO `checador_fime`.`" + DataBaseManager.carreras_name + "` (`idCarrera`, `nombre`) VALUES";
                int identifier = 1;

                for(String carrera : carreras){
                    query += " ("+ identifier +", '"+ carrera +"')";
                    query += (identifier != carreras.size()) ? "," : "";
                    identifier ++;
                }
                manager.executeQuery(query);
            } catch (IOException ex) {
                Logger.getLogger(DefaultData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
