/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Modules.Register;

import Models.DataBaseManager;
import Models.RegistroClass;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
    ObservableList<RegistroClass> registros = FXCollections.observableArrayList();
    DataBaseManager db = new DataBaseManager();
    @FXML
    private TableView<RegistroClass> tableContent; 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configViews();
        loadData();
    }
    
     // CONFIGURATION 
    private void configViews(){
        // TABLE
        if (tableContent.getColumns().isEmpty()){
            addColumns();
        }
    }
    
    // VIEW ALTERATION
    private void addColumns(){
        String[] headers = {"Fecha", "Status", "Nombre"};
        String[] keys = {"checkDate", "status", "namePersona"};

        TableColumn[] columns = new TableColumn[headers.length];
        for(int i=0;i<headers.length;i++){
            TableColumn c = new TableColumn(headers[i]);
            
            c.setCellValueFactory(new PropertyValueFactory(keys[i]));
            columns[i] =  c;
        }
        tableContent.getColumns().addAll(columns);
    }
    
    // LOAD DATA
    private void loadData(){
        List<HashMap<String, Object>> data = db.getDataWithQuery(RegistroClass.getQuerytoAllItems());
        System.out.println("Data size: " + data.size());
        if (!data.isEmpty()){
            registros.clear();
            for(HashMap<String, Object> map:data){
                registros.add(new RegistroClass(map));
            }
            System.out.println("Se agregan " + registros.size() + " rows");
            tableContent.setItems(registros);
        }else{
            System.out.println("ES NULL");
        }
    }
    
}
