/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Modules.MainMenu;

import checadorentrada.ChecadorEntrada;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Benny
 */
public class MainMenuController implements Initializable {
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
       System.out.println("Se muestra ADMIN");
       ChecadorEntrada.showAdminView();
    }
    
    @FXML
    private void showRegisterTable() throws IOException {
       System.out.println("TableRegister");
       ChecadorEntrada.showRegisterView();
    }
    
}
