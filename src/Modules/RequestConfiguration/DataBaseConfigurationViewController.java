/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Modules.RequestConfiguration;

import Configuration.DataBaseConfiguration;
import Managers.AlertManager;
import Managers.FileManagerException;
import checadorentrada.ChecadorEntrada;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Benny
 */
public class DataBaseConfigurationViewController implements Initializable {
    public static String VIEW = "DataBaseConfigurationView.fxml";
    public static String TITLE = "Configuracion de Base de Datos";
    
    @FXML
    private TextField txtFieldName;
    @FXML
    private TextField txtFieldUser;
    @FXML
    private TextField txtFieldPassword;
    @FXML
    private Button btnSave;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configListeners();
    }
    
    /**
     * Saves the configuration data when the Save button is clicked.
     * Notifies the delegate upon successful configuration save.
     */
    @FXML
    private void saveData() {
        System.out.println("DataBaseConfigurationViewController || saveData");
        String name = txtFieldName.getText();
        String user = txtFieldUser.getText();
        String password = txtFieldPassword.getText();
        System.out.println("DataBaseConfigurationViewController || este");
        System.out.println("Password: " + password);
        
        DataBaseConfiguration configuration = DataBaseConfiguration.getInstance();
        try {
            configuration.writeConfiguration(name, user, password);
            
            ChecadorEntrada.showMenu();
        } catch (FileManagerException | DataBaseException e){
            AlertManager.showAlert("Configuracion de Base De Datos", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
     /**
     * Configures event listeners for text fields to track changes.
     */
    private void configListeners(){
        txtFieldName.setOnKeyReleased(new EventHandler(){
            @Override
            public void handle(Event event) {
                didTextFieldChange();
            } 
        });
        txtFieldUser.setOnKeyReleased(new EventHandler(){
            @Override
            public void handle(Event event) {
                didTextFieldChange();
            } 
        });
    }
    
    /**
     * Enables or disables the Save button based on the content of the text fields.
     */
    private void didTextFieldChange(){
        String name = txtFieldName.getText();
        String user = txtFieldUser.getText();
        btnSave.setDisable(name.isBlank() || user.isBlank());
    }
}
