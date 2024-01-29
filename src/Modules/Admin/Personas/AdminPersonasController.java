/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Modules.Admin.Personas;

import Managers.DataBaseManager;
import DataBase.Models.PersonasClass;
import DataBase.Tables.PersonasTable;
import Models.FileManager;
import Modules.BackupAdmin.PersonasviewController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author bennyreyes
 */
public class AdminPersonasController implements Initializable {
    @FXML
    private TextField txtFieldSearch;
    @FXML
    private TableView<PersonasClass> tablePersonas;
    @FXML
    private TableView<PersonasClass> tableCorrectPersonas;
    @FXML
    private TableView<PersonasClass> tableFailurePersonas;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDownloadFormat;
    @FXML
    private Button btnDelete;
    DataBaseManager db = new DataBaseManager();
    ObservableList<PersonasClass> personas = FXCollections.observableArrayList();
    ObservableList<PersonasClass> personasSuccess = FXCollections.observableArrayList();
    ObservableList<PersonasClass> personasFailure = FXCollections.observableArrayList();
    ObservableList<PersonasClass> personasSelected = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("AdminPersonasController || init");
        personasSelected = tablePersonas.getSelectionModel().getSelectedItems();
        configViews();
        configListeners();
        loadData();
    }

    private void configViews(){
        // TABLE
        if (tablePersonas.getColumns().isEmpty()){
            String[] headers = {"Matricula", "Nombre", "Semestre", "Celular", "Fecha de nacimiento", "Email", "Facultad", "Carrera", "Servicio", "Horario"};
            String[] keys = {"id", "name", "semester", "phone", "birthDate", "email", "facultad", "carrera", "servicio", "horario"};
            configColumnsToTable(tablePersonas, headers, keys);
        }
        if (tableCorrectPersonas.getColumns().isEmpty()){
            String[] headers = {"Persona ID", "Nombre"};
            String[] keys = {"id", "name"};
            configColumnsToTable(tableCorrectPersonas, headers, keys);
        }
        if (tableFailurePersonas.getColumns().isEmpty()){
            String[] headers = {"Linea"};
            String[] keys = {"lineFailure"};
            configColumnsToTable(tableFailurePersonas, headers, keys);
            tableFailurePersonas.autosize();
        }
         btnDelete.setDisable(true);
    }
    
    // VIEW ALTERATION
    private void configColumnsToTable(TableView table, String[] headers, String[] keys){
        TableColumn[] columns = new TableColumn[headers.length];
        for(int i=0;i<headers.length;i++){
            TableColumn c = new TableColumn(headers[i]);
            
            c.setCellValueFactory(new PropertyValueFactory(keys[i]));
            columns[i] =  c;
        }
        table.getColumns().addAll(columns);
    }
    
    // BUTTON ACTIONS
    private void configListeners(){
         btnAdd.setOnAction(new EventHandler(){
            @Override
            public void handle(Event event) {
                addElements();
            }
        });
        btnDownloadFormat.setOnAction(new EventHandler(){
            @Override
            public void handle(Event event) {
                try {
                    FileManager.downloadFormatAction("PersonasFormat.csv");
                } catch (IOException ex) {
                    Logger.getLogger(PersonasviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        txtFieldSearch.setOnKeyReleased(new EventHandler(){
            @Override
            public void handle(Event event) {
                didTextFieldSearchChange();
            } 
        });
        personasSelected.addListener((ListChangeListener.Change<? extends PersonasClass> c) -> {
            btnDelete.setDisable(!(c.getList().size() >0));
        });
        btnDelete.setOnAction(new EventHandler(){
            @Override
            public void handle(Event event) {
                deleteElementSelected();
            }
        });
    }
    
    private void addElements(){
        // REMOVE HEADER
        personasSuccess.clear();
        personasFailure.clear();

        Map<String, List<PersonasClass>> map = FileManager.getPersonasFromFile();
        Set<String> keys = map.keySet();
        
        if (keys.size() == 2){
            personasSuccess.addAll(map.get("success"));
            personasFailure.addAll(map.get("failure"));
            // VALIDAR DUPLICADOS POR MATRICULA
            List<Integer> duplicated = new ArrayList<>();
            for (PersonasClass current: personas){
                for (int i=0;i<personasSuccess.size();i++){
                    if (current.getId() == personasSuccess.get(i).getId()){
                        duplicated.add(i);
                    }
                }
            }
            for (Integer index:duplicated){
                PersonasClass persona = personasSuccess.get(index);
                personasSuccess.remove(persona);
                personasFailure.add(persona);
            }       
            tableCorrectPersonas.setItems(personasSuccess);
            tableFailurePersonas.setItems(personasFailure);
        }
        
        if(personasSuccess.size() > 0 ){
            for (PersonasClass persona : personasSuccess){
                createItem(persona);
            }
            loadData();
        }
    }
    
    private void deleteElementSelected(){
        if (personasSelected.size() == 1){
            PersonasClass persona = personasSelected.get(0);
            PersonasTable personasTable = new PersonasTable();
            db.deleteItem(personasTable.getTableName(), persona.getKeyId() + " = " + persona.getId());
            loadData();
        }
    }
    
    private void createItem(PersonasClass persona){
        if(persona.getIsValid()){
            PersonasTable personasTable = new PersonasTable();
            db.createItem(personasTable.getTableName(), persona.getInsertHeader(), persona.getValuesQuery());
        }
    }
    
    // Interactiones
    private void didTextFieldSearchChange(){
        String search = txtFieldSearch.getText();
        if(search.length() > 0){
            String query = "";
            if (search.matches("[0-9]+") && search.length() > 0) {
                query = PersonasClass.getQuerytoAllItemsByMatricula("'" + search + "%'");
            } else {
                query = PersonasClass.getQuerytoAllItemsByText("'" + search + "%'");
            }
            List<HashMap<String, Object>> data = db.getDataWithQuery(query);
            if (!data.isEmpty()){
                personas.clear();
                for(HashMap<String, Object> map:data){
                    personas.add(new PersonasClass(map, false));
                }
                tablePersonas.setItems(personas);
            }else{
                System.out.println("LOAD DATA SEARCH: NO REGRESA INFO");
            }
        } else {
            loadData();
        }
    }
    
    // LOAD DATA
    private void loadData(){
        List<HashMap<String, Object>> data = db.getDataWithQuery(PersonasClass.getQuerytoAllItems());
        if (!data.isEmpty()){
            personas.clear();
            for(HashMap<String, Object> map:data){
                personas.add(new PersonasClass(map, false));
            }
            tablePersonas.setItems(personas);
        }else{
            System.out.println("LOAD DATA: NO REGRESA INFO");
        }
    }
    
}
