/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Modules.Admin;

import Models.HorariosClass;
import Models.PersonasClass;
import Models.UsuariosClass;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author bennyreyes
 */
public class AdminviewController implements Initializable {
    HorariosviewController horariosVC;
    PersonasviewController personasVC;
    ReportesviewController reportesVC;
    UsuariosviewController usuariosVC;
   
    @FXML
    private TabPane mainPane;
    
    /*
     * PERSONAS VARS
    */
    @FXML
    private TextField txtFieldSearchPersonas;
    @FXML
    private TableView<PersonasClass> tablePersonas;
    @FXML
    private TableView<PersonasClass> tableCorrectPersonas;
    @FXML
    private TableView<PersonasClass> tableFailurePersonas;
    @FXML
    private Button btnAddPersonas;
    @FXML
    private Button btnDownloadPersonasFormat;
    @FXML
    private Button btnDeletePersonas;
    
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
    
    /*
     * REPORTES VARS
    */
    @FXML
    private DatePicker datePickerReport;
    @FXML
    private Button btnDownloadReport;
    @FXML
    private Button btnDownloadFacultades;
    @FXML
    private Button btnDownloadCarreras;
    
    /*
     * USUARIOS VARS
    */
    @FXML
    private TextField txtFieldSearchUsuarios;
    @FXML
    private TableView<UsuariosClass> tableUsuarios;
    @FXML
    private TableView<UsuariosClass> tableCorrectUsuarios;
    @FXML
    private TableView<UsuariosClass> tableFailureUsuarios;
    @FXML
    private Button btnAddUsuarios;
    @FXML
    private Button btnDownloadUsuariosFormat;
    @FXML
    private Button btnDeleteUsuarios;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configureSubControllers();
        personasVC.notify(true);
    }
    
    private void configureSubControllers(){
        horariosVC = new HorariosviewController(tableHorarios, horariosForm, btnNewHorario, btnClearHorario, btnEditHorario, btnDeleteHorario);
        personasVC = new PersonasviewController(tablePersonas, tableCorrectPersonas, tableFailurePersonas, txtFieldSearchPersonas, btnAddPersonas, btnDownloadPersonasFormat, btnDeleteUsuarios);
        reportesVC = new ReportesviewController(datePickerReport, btnDownloadReport, btnDownloadFacultades, btnDownloadCarreras);
        usuariosVC = new UsuariosviewController(tableUsuarios, tableCorrectUsuarios, tableFailureUsuarios, txtFieldSearchUsuarios, btnAddUsuarios, btnDownloadUsuariosFormat, btnDeleteUsuarios);
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
            case "Personas":
                personasVC.notify(willOpen);
                break;
            case "Horarios":
                horariosVC.notify(willOpen);
                break;
            case "Reportes":
                reportesVC.notify(willOpen);
                break;
            case "Usuarios":
                usuariosVC.notify(willOpen);
            default:
                break;
        }
    }
    
    
}
