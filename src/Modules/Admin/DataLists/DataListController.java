/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Modules.Admin.DataLists;

import Managers.DataBaseManager;
import DataBase.DataListProtocol;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
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
public class DataListController implements Initializable {
    DataBaseManager db = new DataBaseManager();
    ObservableList<DataListProtocol> items = FXCollections.observableArrayList();
    ObservableList<DataListProtocol> itemsSelected = FXCollections.observableArrayList();
    @FXML
    private TextField txtFieldAdd;
    @FXML
    private TableView<DataListProtocol> tableContent;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        itemsSelected = tableContent.getSelectionModel().getSelectedItems();
    }
    
    private void configViews(){
        // TABLE
        if (tableContent.getColumns().isEmpty()){
            String[] headers = {"IC", "Nombre"};
            String[] keys = {"id", "name"};
            configColumnsToTable(tableContent, headers, keys);
        }
        btnDelete.setDisable(true);
    }

    private void configColumnsToTable(TableView table, String[] headers, String[] keys){
        TableColumn[] columns = new TableColumn[headers.length];
        for(int i=0;i<headers.length;i++){
            TableColumn c = new TableColumn(headers[i]);
            c.setCellValueFactory(new PropertyValueFactory(keys[i]));
            columns[i] =  c;
        }
        //table.getColumns().addAll(columns);
    }
    
    private void configListeners(){
        //btnAdd.setOnAction((Event event) -> {
        //    addElement();
        //});

        txtFieldAdd.setOnKeyReleased((Event event) -> {
            didTextFieldSearchChange();
         });
        itemsSelected.addListener((ListChangeListener.Change<? extends DataListProtocol> c) -> {
            btnDelete.setDisable(!!c.getList().isEmpty());
        });
        //btnDelete.setOnAction((Event event) -> {
        //    deleteElementSelected();
        //});
    }
    
    private void addElement(){
        // REMOVE HEADER
//        personasSuccess.clear();
//        personasFailure.clear();
//
//        Map<String, List<PersonasClass>> map = FileManager.getPersonasFromFile();
//        Set<String> keys = map.keySet();
//        
//        if (keys.size() == 2){
//            personasSuccess.addAll(map.get("success"));
//            personasFailure.addAll(map.get("failure"));
//            // VALIDAR DUPLICADOS POR MATRICULA
//            List<Integer> duplicated = new ArrayList<>();
//            for (PersonasClass current: personas){
//                for (int i=0;i<personasSuccess.size();i++){
//                    if (current.getId() == personasSuccess.get(i).getId()){
//                        duplicated.add(i);
//                    }
//                }
//            }
//            for (Integer index:duplicated){
//                PersonasClass persona = personasSuccess.get(index);
//                personasSuccess.remove(persona);
//                personasFailure.add(persona);
//            }       
//            tableCorrectPersonas.setItems(personasSuccess);
//            tableFailurePersonas.setItems(personasFailure);
//        }
//        
//        if(personasSuccess.size() > 0 ){
//            for (PersonasClass persona : personasSuccess){
//                createItem(persona);
//            }
//            loadData();
//        }
    }
    
    private void deleteElementSelected(){
        if (itemsSelected.size() == 1){
//            PersonasClass persona = personasSelected.get(0);
//            PersonasTable personasTable = new PersonasTable();
//            db.deleteItem(personasTable.getTableName(), persona.getKeyId() + " = " + persona.getId());
//            loadData();
        }
    }
    
    // Interactiones
    private void didTextFieldSearchChange(){
        String text = txtFieldAdd.getText();
        txtFieldAdd.setDisable(text.length() <= 0);
    }
    
}
