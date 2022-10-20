/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Modules.Register;

import Models.DataBaseManager;
import Models.RegistroClass;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author bennyreyes
 */
public class RegisterviewController implements Initializable {
    ObservableList<RegistroClass> registros;
    DataBaseManager db;
    @FXML
    private TableView<RegistroClass> tableContent;
    @FXML
    private TableColumn horaColumn;
    @FXML
    private TableColumn statusColumn;
    @FXML
    private TableColumn matriculaColumn;
    @FXML
    private TableColumn nombreColumn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new DataBaseManager();
        registros = FXCollections.observableArrayList();
        registros.addListener(registrosListener());
        
        horaColumn.setCellValueFactory(new PropertyValueFactory("horaregistro"));
        statusColumn.setCellValueFactory(new PropertyValueFactory("status"));
        matriculaColumn.setCellValueFactory(new PropertyValueFactory("matricula"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory("nombre"));
    }
    
    private ListChangeListener<RegistroClass> registrosListener(){
        return (ListChangeListener.Change<? extends RegistroClass> c) -> {
            tableContent.setItems(registros);
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        };
    }
    
}
