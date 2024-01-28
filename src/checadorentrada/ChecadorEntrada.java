/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package checadorentrada;

import DataBase.Configuration.DataBaseConfiguration;
import Managers.DataBaseManager;
import DataBase.Models.PersonasClass;
import DataBase.Models.RegistrosClass;
import Modules.RequestConfiguration.DataBaseConfigurationViewController;
import DataBase.Tables.RegistrosTable;
import Extensions.DateExtension;
import Extensions.StringExtension;
import Managers.AlertManager;
import Modules.Admin.AdminManagerController;
import Modules.MainMenu.MainMenuController;
import Modules.Register.AttendanceviewController;
import Server.ServerDelegate;
import Server.Servidor;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Benny
 */
public class ChecadorEntrada extends Application implements ServerDelegate {
    private static Scene scene;
    private Servidor server;
    
    @Override
    public void start(Stage primaryStage) {
        FXMLLoader fxmlLoader;
        configServer();
        DataBaseConfiguration config = DataBaseConfiguration.getInstance();
        try{
            if (config.isValid()){
                config.loadData();
                fxmlLoader = new FXMLLoader(MainMenuController.class.getResource(MainMenuController.VIEW));
            }else{
                fxmlLoader = new FXMLLoader(DataBaseConfigurationViewController.class.getResource(DataBaseConfigurationViewController.VIEW));
            }
            scene = new Scene(fxmlLoader.load());
            
            primaryStage.setTitle("Checador de entrada y salida");
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(800);
            primaryStage.setMinHeight(600);
            
            javafx.geometry.Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            primaryStage.setX(screenBounds.getMinX());
            primaryStage.setY(screenBounds.getMinY());
            primaryStage.setWidth(screenBounds.getWidth());
            primaryStage.setHeight(screenBounds.getHeight());
            
            primaryStage.setX(0);
            primaryStage.setY(0);
            primaryStage.setResizable(true);
            primaryStage.show();
        }catch(IOException e){
            System.out.println("No se pudo cargar!");
            System.out.println(e);
        }
    }
    
    @Override
    public void stop() throws Exception {
        super.stop();
    }
    
    public static void showDataBaeConfigurationForm(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(DataBaseConfigurationViewController.class.getResource(DataBaseConfigurationViewController.VIEW));
            scene.setRoot(fxmlLoader.load());
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    public static void showMenu(){
        DataBaseConfiguration config = DataBaseConfiguration.getInstance();
        if (config.isValid()){
            config.loadData();
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(MainMenuController.class.getResource(MainMenuController.VIEW));
                scene.setRoot(fxmlLoader.load());
            }catch(IOException e){
                System.out.println(e);
            }
        }else{
            ChecadorEntrada.showDataBaeConfigurationForm();
        }
    }
    
    public static void showRegisterView(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(AttendanceviewController.class.getResource("attendanceview.fxml"));
            scene.setRoot(fxmlLoader.load());
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    public static void showAdminView(){
        try{
            String oldAdmin = "adminview.fxml";
            
//            FXMLLoader fxmlLoader = new FXMLLoader(AdminviewController.class.getResource(oldAdmin));
            String newAdmin = "AdminManager.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(AdminManagerController.class.getResource(newAdmin));
            scene.setRoot(fxmlLoader.load());
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    public void configServer(){
        try{
            this.server = new Servidor();
            this.server.setDelegate(this);
            Thread backgroundThread = new Thread(() -> {
                server.startServer();
            });
            backgroundThread.start();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    @Override
    public void didReceiveMessage(String message){
        if (message.startsWith("{") && message.endsWith("}")){
            System.out.println("Mensaje: " + message);
            HashMap<String,String> map = this.getMap(message);
            String matricula = map.getOrDefault("matricula", "");
            String password = map.getOrDefault("password", "");
            String token = map.getOrDefault("token", "prueba");
            System.out.println("Matricula: " + matricula + " bool " + !matricula.isEmpty());
            System.out.println("Password: " + password + " bool " + !password.isEmpty());
            System.out.println("Token: " + token + " bool " + !token.isEmpty());
            if (!matricula.isEmpty() && !password.isEmpty() && !token.isEmpty()){
                System.out.println(token);
                registerUser(matricula, password);
            }
        }
    }
    
     private void registerUser(String matricula, String password){
        RegistrosTable registros = new RegistrosTable();
        DataBaseManager db = new DataBaseManager();
        DataBaseConfiguration config = DataBaseConfiguration.getInstance();
        
        if (StringExtension.onlyDigits(matricula) && matricula.length() > 5){
            if (password.length() >= StringExtension.kPASSWORD_LENGTH){
                PersonasClass persona = PersonasClass.getPersonaBy(matricula);
                if (persona != null){
                    if (persona.getPassword().equals(password)){
                        int count = db.getCountOf(registros.getTableName());
                        RegistrosClass last =  RegistrosClass.getLastRegistroBy(matricula);
                        boolean status = (last != null) ? !last.isSalida() : false;

                        // CREAR REGISTRO
                        String today = DateExtension.getStringDate(new Date(), "dd/MM/YYYY");
                        RegistrosClass registro = new RegistrosClass(count + 1, today, !status, matricula);
                        db.createItem(registros.getTableName(), registro.getInsertHeader() , registro.getValuesQuery());
                        
                        config.loadData();
                        showMessage("Se registro la entrada correctamente");
                    }else{
                        showMessage("La contraseña es incorrecta");
                    }
                }else{
                    showMessage("La persona con la matricula " + matricula + "no existe");
                }
            }else{
                showMessage("Ingresa una contraseña mayor a " + StringExtension.kPASSWORD_LENGTH);
            }
        }else{
            showMessage("Ingresa una matricula valida");
        }
    }
     
     private void showMessage(String message){
        AlertManager.showAlert("Checador de Entrada", message, Alert.AlertType.ERROR);
    }
    
    private HashMap<String, String> getMap(String line){
        HashMap<String, String> map = new HashMap<>();
        String aux = line.replace("{", "").replace("}", "").replace("\"", "");
        String[] split = aux.split(",");
        List<String> list = Arrays.asList(split);
        for(String obj:list){
            String[] objSplit = obj.split(":");
            if(objSplit.length == 2){
                map.put(objSplit[0], objSplit[1]);
            }
        }
        return map;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}