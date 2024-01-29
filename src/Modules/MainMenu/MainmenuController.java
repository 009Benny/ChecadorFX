/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Modules.MainMenu;

import Managers.DataBaseManager;
import Modules.Base.BaseController;
import checadorentrada.ChecadorEntrada;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Benny
 */
public class MainMenuController extends BaseController {
    public static String VIEW = "MainMenu.fxml";
    
    @FXML
    private TextField txtFieldAdminUser;
    
    @FXML
    private TextField txtFieldAdminPass;
    
    @FXML
    private Button btnAdmin;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configListeners();
        didTextFieldSearchChange();
        comprobeConnection();
    }
    
    private void comprobeConnection(){
        DataBaseManager manager = new DataBaseManager();
        if (!manager.isConnected()){
            createAlertMessage("No esta conectado a la base de datos. El programa se cerrará");
        }else{
            System.out.println("MainMenuController || Conexion comprobada");
        }
    }
    
    private void configListeners(){
        txtFieldAdminUser.setOnKeyReleased(new EventHandler(){
            @Override
            public void handle(Event event) {
                didTextFieldSearchChange();
            } 
        });
        txtFieldAdminPass.setOnKeyReleased(new EventHandler(){
            @Override
            public void handle(Event event) {
                didTextFieldSearchChange();
            } 
        });
    }
    
    private void didTextFieldSearchChange(){
        String user = txtFieldAdminUser.getText();
        String pass = txtFieldAdminPass.getText();
        
        btnAdmin.setDisable(user.isBlank() || pass.isBlank());
    }
    
    @FXML
    private void showAdmin() throws IOException {
       ChecadorEntrada.showAdminView();
    }
    
    @FXML
    private void showRegisterTable() throws IOException {
       ChecadorEntrada.showRegisterView();
    }

    private void claseAlertMessage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
