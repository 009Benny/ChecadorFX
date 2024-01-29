/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Modules.Admin.Configuration;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Modules.Admin.GenericTabPane;

/**
 * FXML Controller class
 *
 * @author Benny
 */
public class AdminConfigurationController implements Initializable, GenericTabPane {
    public static String VIEW = "AdminConfiguration";
    @FXML
    private TextField txtFieldPhotosURL;
    @FXML
    private Button btnSavePhotosURL;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("AdminConfigurationController || init");
    }    

    @Override
    public String getTitle() {
        return "Configuracion";
    }

    @Override
    public String getViewName() {
        return VIEW;
    }
    
}
