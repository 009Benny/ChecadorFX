/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import DataBase.DataBaseManager;
import DataBase.Tables.*;
import java.io.IOException;
import static java.lang.Boolean.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bennyreyes
 */
public class DefaultData {
    DataBaseManager manager;
    Boolean debug = TRUE;
    
    public DefaultData(){
        manager = new DataBaseManager();
    }
    
    public void checkAll(){
        try{
            DataBaseManager.createDataBaseIfDoesntExist();
            checkTables();
            checkInstances();
        }catch (Exception e){
               System.out.print(e);
        }
    }
    
    public void checkTables(){
        List<TableProtocol> list = new ArrayList<>();
        /// Primero las tablas sin dependencias
        list.add(new ServiciosTable());
        list.add(new CarrerasTable());
        list.add(new HorariosTable());
        list.add(new NivelesTable());
        list.add(new FacultadesTable());
        /// Tablas con dependencias
        list.add(new PersonasTable());
        list.add(new RegistrosTable());
        list.add(new UsuariosTable());
        // Hacemos el recorrido
        for (TableProtocol item : list) {
            manager.executeQuery(item.getCreationTableQuery());
        }
    }
    
    public void checkInstances(){
        checkFacultades();
        checkCarreras();
        checkNiveles();
        checkDefaultUsers();
        checkDefaultHorarios();
        checkServicios();
        checkDefaultPersonas();
    }
    
    // CREATION OF DEFAULT INSTANCES
    public void checkFacultades(){
        FacultadesTable facultades = new FacultadesTable();
        int count = manager.getCountOf(facultades.getTableName());
        System.out.println("Facultades || Cantidad de lineas " + count);
        if (count == 0) {
            try {
                ArrayList<String> array = FileManager.getData("src/Data/Facultades.txt");
                String query = "INSERT INTO `" + DataBaseManager.getDataBaseName() + "`.`" + facultades.getTableName() + "` " + facultades.getHeadersQuery() + " VALUES";
                int identifier = 1;
                for(String line : array){
                    String[] split = line.split(",");
                    if (split.length == 2){
                        String facultad = split[0];
                        String abbreviation = split[1];
                        query += " ("+ identifier +", '"+ facultad +"', '"+ abbreviation +"')";
                        query += (identifier != array.size()) ? "," : "";
                        identifier ++;
                    }
                }
                manager.executeQuery(query);
            } catch (IOException ex) {
                Logger.getLogger(DefaultData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void checkCarreras(){
        CarrerasTable carreras = new CarrerasTable();
        int count = manager.getCountOf(carreras.getTableName());
        System.out.println("Carreras || Cantidad de lineas " + count);
        if (count == 0) {
            try {
                ArrayList<String> array = FileManager.getData("src/Data/Carreras.txt");
                String query = "INSERT INTO `" + DataBaseManager.getDataBaseName() + "`.`" + carreras.getTableName() + "` " + carreras.getHeadersQuery() + " VALUES";
                int identifier = 1;
                for(String carrera : array){
                    query += " ("+ identifier +", '"+ carrera +"')";
                    query += (identifier != array.size()) ? "," : "";
                    identifier ++;
                }
                manager.executeQuery(query);
            } catch (IOException ex) {
                Logger.getLogger(DefaultData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void checkNiveles(){
        NivelesTable niveles = new NivelesTable();
        int count = manager.getCountOf(niveles.getTableName());
        System.out.println("Niveles || Cantidad de lineas " + count);
        if (count == 0) {
            try {
                ArrayList<String> array = FileManager.getData("src/Data/Niveles.txt");
                String query = "INSERT INTO `" + DataBaseManager.getDataBaseName() + "`.`" + niveles.getTableName() + "` " + niveles.getHeadersQuery() + " VALUES";
                int identifier = 1;
                for(String nivel : array){
                    query += " ("+ identifier +", '"+ nivel +"')";
                    query += (identifier != array.size()) ? "," : "";
                    identifier ++;
                }
                manager.executeQuery(query);
            } catch (IOException ex) {
                Logger.getLogger(DefaultData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void checkServicios(){
        ServiciosTable servicios = new ServiciosTable();
        int count = manager.getCountOf(servicios.getTableName());
        System.out.println("DEPORTES || Cantidad de lineas " + count);
        if (count == 0) {
            try {
                ArrayList<String> array = FileManager.getData("src/Data/Servicios.txt");
                String query = "INSERT INTO `" + DataBaseManager.getDataBaseName() + "`.`" + servicios.getTableName() + "` " + servicios.getHeadersQuery() + " VALUES";
                int identifier = 1;
                for(String nivel : array){
                    query += " ("+ identifier +", '"+ nivel +"')";
                    query += (identifier != array.size()) ? "," : "";
                    identifier ++;
                }
                manager.executeQuery(query);
            } catch (IOException ex) {
                Logger.getLogger(DefaultData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void checkDefaultUsers(){
        UsuariosTable usuarios = new UsuariosTable();
        int count = manager.getCountOf(usuarios.getTableName());
        System.out.println("Users || Cantidad de lineas " + count);
        if (count == 0) {
            try {
                ArrayList<String> array = FileManager.getData("src/Data/Usuarios.txt");
                String query = "INSERT INTO `" + DataBaseManager.getDataBaseName() + "`.`" + usuarios.getTableName() + "` " + usuarios.getHeadersQuery() + " VALUES";
                int i = 1;
                for(String usuario : array){
                    
                    String[] split = usuario.split("\\s+");
                    query += " ("+ split[0] +", '"+ split[1] +"', '"+ split[2] +"', '"+ split[3] +"')";
                    
                    query += (i != array.size()) ? "," : "";
                    i ++;
                }
                manager.executeQuery(query);
            } catch (IOException ex) {
                Logger.getLogger(DefaultData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void checkDefaultHorarios(){
        HorariosTable horarios = new HorariosTable();
        int count = manager.getCountOf(horarios.getTableName());
        System.out.println("Horarios || Cantidad de lineas " + count);
        if (count == 0) {
            try {
                ArrayList<String> array = FileManager.getData("src/Data/Horarios.txt");
                String query = "INSERT INTO `" + DataBaseManager.getDataBaseName() + "`.`" + horarios.getTableName() + "` " + horarios.getHeadersQuery() + " VALUES";
                int i = 1;
                for(String horario : array){
                    String[] split = horario.split("\\s+");
                    query += " ("+ split[0] +", '"+ split[1] +"', '"+ split[2] +"', '"+ split[3] +"', '"+ split[4] +"', '"+ split[5] +"', '"+ split[6] +"', '"+ split[7] +"', '"+ split[8] +"', '"+ split[9] +"', '"+ split[10] +"', '"+ split[11] +"')";
                    
                    query += (i != array.size()) ? "," : "";
                    i ++;
                }
                System.out.println(query);
                manager.executeQuery(query);
            } catch (IOException ex) {
                Logger.getLogger(DefaultData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void checkDefaultPersonas(){
        if(debug){
            PersonasTable personas = new PersonasTable();
            int count = manager.getCountOf(personas.getTableName());
            System.out.println("Personas || Cantidad de lineas " + count);
            if (count == 0) {
                try {
                    ArrayList<String> array = FileManager.getData("src/Data/Personas.txt");
                    String query = "INSERT INTO `" + DataBaseManager.getDataBaseName() + "`.`" + personas.getTableName() + "` " + personas.getHeadersQuery() + " VALUES";
                    int i = 1;
                    for(String persona : array){
                        String[] split = persona.split(",");
                        query += " ("+ split[0] +", '"+ split[1] +"', '"+ split[2] +"', '"+ split[3] +"', '"+ split[4] +"', '"+ split[5] +"', '"+ split[6] +"', '"+ split[7] +"', '"+ split[8] +"', '"+ split[9] +"', '"+ split[10] +"', '"+ split[11] +"', '"+ split[12] +"', '"+ split[13] +"')";

                        query += (i != array.size()) ? "," : "";
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
    
}
