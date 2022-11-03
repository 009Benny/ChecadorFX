/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Modules.Admin;

import Models.HorariosClass;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author bennyreyes
 */
public class AdminviewController implements Initializable {
    HorariosviewController horariosVC;
   
    @FXML
    private TabPane mainPane;
    
    /*
     * HORARIOS VARS
    */
    @FXML
    private TableView<HorariosClass> tableHorarios;
    @FXML
    private VBox horariosForm;
    @FXML
    private Button btnNewHorario;
    @FXML
    private Button btnClearHorario;
    @FXML
    private Button btnEditHorario;
    @FXML
    private Button btnDeleteHorario;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configureSubControllers();
    }
    
    private void configureSubControllers(){
        horariosVC = new HorariosviewController(tableHorarios, horariosForm, btnNewHorario, btnClearHorario, btnEditHorario, btnDeleteHorario);
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
                break;
        }
    }
    
    
}
