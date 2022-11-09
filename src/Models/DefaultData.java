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
        checkTables();
        checkInstances();
    }
    
    public void checkTables(){
        createCarrerasTable();
        createHorariosTable();
        createNivelesTable();
        createFacultadesTable();
        createPersonasTable();
        createRegistrosTable();
        createUsersTable();
    }
    
    public void checkInstances(){
        checkFacultades();
        checkCarreras();
        checkNiveles();
        checkDefaultUsers();
        checkDefaultHorarios();
    }
    
    // CREATION OF TABLES
    private void createRegistrosTable(){
        String query = "CREATE TABLE IF NOT EXISTS " + DataBaseManager.registros_table + " " +
        "(" + DataBaseManager.registros_id + " INT not NULL, " +
        " checkDate DATE not NULL, " +
        " status Boolean not NULL, " +
        " id_Persona Int not NULL, " +
        " CONSTRAINT fkPersona FOREIGN KEY (id_Persona) REFERENCES "+DataBaseManager.personas_table+"(" + DataBaseManager.personas_id + ")," +
        " PRIMARY KEY ( " + DataBaseManager.registros_id + " ))"; 
        manager.executeQuery(query);
    }
    
    private void createUsersTable(){
        String query = "CREATE TABLE IF NOT EXISTS " + DataBaseManager.usuarios_table + " " +
        "(" + DataBaseManager.usuarios_id + " INT not NULL, " +
        " nombre VARCHAR(255) not NULL, " +
        " password VARCHAR(255) not NULL, " +
        " id_Nivel Int not NULL, " +
        " CONSTRAINT fkNivel FOREIGN KEY (id_Nivel) REFERENCES NIVELES(" + DataBaseManager.niveles_id + ")," +
        " PRIMARY KEY ( " + DataBaseManager.usuarios_id + " ))"; 
        manager.executeQuery(query);
    }
    
    private void createPersonasTable(){
        String query = "CREATE TABLE IF NOT EXISTS " + DataBaseManager.personas_table + " " +
        "(" + DataBaseManager.personas_id + " INT not NULL, " +
        " nombre VARCHAR(255) not NULL, " +
        " apPaterno VARCHAR(255) not NULL, " +
        " apMaterno VARCHAR(255) not NULL, " +
        " perCorreo VARCHAR(255), " +
        " insCorreo VARCHAR(255), " +
        " celular VARCHAR(12), " +
        " id_Carrera INT, " +
        " id_Facultad INT not NULL, " +
        " id_Horario INT not NULL, " +
        " CONSTRAINT fkCarrera FOREIGN KEY (id_Carrera) REFERENCES CARRERAS(" + DataBaseManager.carreras_id + ")," +
        " CONSTRAINT fkFacultad FOREIGN KEY (id_Facultad) REFERENCES FACULTADES(" + DataBaseManager.facultades_id + ")," +
        " CONSTRAINT fkHorario FOREIGN KEY (id_Horario) REFERENCES HORARIOS(" + DataBaseManager.horarios_id + ")," +
        " PRIMARY KEY ( " + DataBaseManager.personas_id + " ))"; 
        manager.executeQuery(query);
    }

    private void createHorariosTable(){
        String query = "CREATE TABLE IF NOT EXISTS " + DataBaseManager.horarios_table + " " +
        "(" + DataBaseManager.horarios_id + " INT not NULL, " +
        " horario VARCHAR(255) not NULL, " +
        " startDate DATE, " +
        " endDate DATE, " +
        " lunes VARCHAR(255), " +
        " martes VARCHAR(255), " +
        " miercoles VARCHAR(255), " +
        " jueves VARCHAR(255), " +
        " viernes VARCHAR(255), " +
        " sabado VARCHAR(255), " +
        " domingo VARCHAR(255), " +
        " PRIMARY KEY ( " + DataBaseManager.horarios_id + " ))"; 
        manager.executeQuery(query);
    }

    private void createNivelesTable(){
        String query = "CREATE TABLE IF NOT EXISTS " + DataBaseManager.niveles_table + " " +
        "(" + DataBaseManager.niveles_id + " INT not NULL, " +
        " nivel VARCHAR(255) not NULL, " +
        " PRIMARY KEY ( " + DataBaseManager.niveles_id + "))"; 
        manager.executeQuery(query);
    }

    private void createFacultadesTable(){
        String query = "CREATE TABLE IF NOT EXISTS " + DataBaseManager.facultades_table + " " +
        "(" + DataBaseManager.facultades_id + " INT not NULL, " +
        " facultad VARCHAR(255) not NULL, " +
        " PRIMARY KEY ( " + DataBaseManager.facultades_id + " ))"; 
        manager.executeQuery(query);
    }

    private void createCarrerasTable(){
        String query = "CREATE TABLE IF NOT EXISTS " + DataBaseManager.carreras_table + " " +
        "(" + DataBaseManager.carreras_id + " INT not NULL, " +
        " carrera VARCHAR(255) not NULL, " +
        " PRIMARY KEY ( " + DataBaseManager.carreras_id + " ))"; 
        manager.executeQuery(query);
    }
    
    // CREATION OF DEFAULT INSTANCES
    public void checkFacultades(){
        int count = manager.getCountOf(DataBaseManager.facultades_table);
        System.out.println("Facultades || Cantidad de lineas " + count);
        if (count == 0) {
            try {
                ArrayList<String> facultades = FileManager.getData("src/Data/Facultades.txt");
                String query = "INSERT INTO `checador_fime`.`" + DataBaseManager.facultades_table + "` (`" + DataBaseManager.facultades_id + "`, `facultad`) VALUES";
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
        int count = manager.getCountOf(DataBaseManager.carreras_table);
        System.out.println("Carreras || Cantidad de lineas " + count);
        if (count == 0) {
            try {
                ArrayList<String> carreras = FileManager.getData("src/Data/Carreras.txt");
                String query = "INSERT INTO `checador_fime`.`" + DataBaseManager.carreras_table + "` (`" + DataBaseManager.carreras_id + "`, `carrera`) VALUES";
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
    
    public void checkNiveles(){
        int count = manager.getCountOf(DataBaseManager.niveles_table);
        System.out.println("Niveles || Cantidad de lineas " + count);
        if (count == 0) {
            try {
                ArrayList<String> niveles = FileManager.getData("src/Data/Niveles.txt");
                String query = "INSERT INTO `checador_fime`.`" + DataBaseManager.niveles_table + "` (`" + DataBaseManager.niveles_id + "`, `nivel`) VALUES";
                int identifier = 1;
                for(String nivel : niveles){
                    query += " ("+ identifier +", '"+ nivel +"')";
                    query += (identifier != niveles.size()) ? "," : "";
                    identifier ++;
                }
                manager.executeQuery(query);
            } catch (IOException ex) {
                Logger.getLogger(DefaultData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void checkDefaultUsers(){
        int count = manager.getCountOf(DataBaseManager.usuarios_table);
        System.out.println("Users || Cantidad de lineas " + count);
        if (count == 0) {
            try {
                ArrayList<String> usuarios = FileManager.getData("src/Data/Usuarios.txt");
                String query = "INSERT INTO `checador_fime`.`" + DataBaseManager.usuarios_table + "` (`" + DataBaseManager.usuarios_id + "`, `nombre`, `password`, `id_Nivel`) VALUES";
                int i = 1;
                for(String usuario : usuarios){
                    
                    String[] split = usuario.split("\\s+");
                    query += " ("+ split[0] +", '"+ split[1] +"', '"+ split[2] +"', '"+ split[3] +"')";
                    
                    query += (i != usuarios.size()) ? "," : "";
                    i ++;
                }
                manager.executeQuery(query);
            } catch (IOException ex) {
                Logger.getLogger(DefaultData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void checkDefaultHorarios(){
        int count = manager.getCountOf(DataBaseManager.horarios_table);
        System.out.println("Horarios || Cantidad de lineas " + count);
        if (count == 0) {
            try {
                ArrayList<String> horarios = FileManager.getData("src/Data/Horarios.txt");
                String query = "INSERT INTO `checador_fime`.`" + DataBaseManager.horarios_table + "` (`" + DataBaseManager.horarios_id + "`, `horario`, `startDate`, `endDate`, `lunes`, `martes`, `miercoles`, `jueves`, `viernes`, `sabado`, `domingo`) VALUES";
                int i = 1;
                for(String horario : horarios){
                    String[] split = horario.split("\\s+");
                    query += " ("+ split[0] +", '"+ split[1] +"', STR_TO_DATE('"+ split[2] +"','%d/%m/%Y'), STR_TO_DATE('"+ split[3] +"','%d/%m/%Y'), '"+ split[4] +"', '"+ split[5] +"', '"+ split[6] +"', '"+ split[7] +"', '"+ split[8] +"', '"+ split[9] +"', '"+ split[10] +"')";
                    
                    query += (i != horarios.size()) ? "," : "";
                    i ++;
                }
                System.out.println(query);
                manager.executeQuery(query);
            } catch (IOException ex) {
                Logger.getLogger(DefaultData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
