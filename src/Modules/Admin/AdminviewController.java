/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Modules.Admin;

import Models.HorariosClass;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author bennyreyes
 */
public class AdminviewController implements Initializable {
    HorariosviewController horariosVC;
   
    @FXML
    private TabPane mainPane;
    
    @FXML
    private TableView<HorariosClass> tableHorarios;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configureSubControllers();
    }
    
    private void configureSubControllers(){
        horariosVC = new HorariosviewController(tableHorarios);
        addSelectionListener();
    }
    
    private void addSelectionListener(){
        mainPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> ov, Tab tabOld, Tab tabNew) {
                System.out.println("Tab selected: '" + tabNew.getText());
                notifyAppear(tabOld.getText(), false);
                notifyAppear(tabNew.getText(), true);
            }
        });
    }
    
    private void notifyAppear(String objective, boolean willOpen){
        switch(objective){
            case "Horarios":
                if (willOpen) { horariosVC.didAppear(); } else { horariosVC.didDissappear(); }
                break;
            default:
                System.out.println("No es horarios");
                break;
        }
    }
    
    
}
