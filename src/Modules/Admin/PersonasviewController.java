/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modules.Admin;

import Models.DataBaseManager;
import Models.HorariosClass;
import Models.PersonasClass;
import java.util.HashMap;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author bennyreyes
 */
public class PersonasviewController implements AdminGenericController {
    DataBaseManager db = new DataBaseManager();
    TableView<PersonasClass> tableContent;
    TableView<PersonasClass> tableSuccess;
    TableView<PersonasClass> tableFailure;
    ObservableList<PersonasClass> personas = FXCollections.observableArrayList();
    ObservableList<PersonasClass> personasSelected = FXCollections.observableArrayList();
    TextField txtFieldSearch;
    Button btnAdd;
    Button btnDownloadFormat;
    Button btnDelete;
    
    public PersonasviewController(TableView<PersonasClass> table, TableView<PersonasClass> tableSuccess, TableView<PersonasClass> tableFailure, TextField search, Button add, Button download, Button delete){
        this.tableContent = table;
        this.tableSuccess = tableSuccess;
        this.tableFailure = tableFailure;
        this.txtFieldSearch = search;
        this.btnAdd = add;
        this.btnDownloadFormat = download;
        this.btnDelete = delete;
        configViews();
        //configListeners();
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
        String[] headers = {"Persona ID", "Nombre", "Apellido Paterno", "Apellido Materno", "Email Personal", "Email Institucional", "Celular", "Carrera", "Facultad", "Horario"};
        String[] keys = {"idPersona", "nombre", "apPaterno", "apMaterno", "perEmail", "instEmail", "phone", "carrera", "facultad", "horario"};

        TableColumn[] columns = new TableColumn[headers.length];
        for(int i=0;i<headers.length;i++){
            TableColumn c = new TableColumn(headers[i]);
            
            c.setCellValueFactory(new PropertyValueFactory(keys[i]));
            columns[i] =  c;
        }
        tableContent.getColumns().addAll(columns);
    }
    
    private void configListeners(){
        /*horariosSelected.addListener((ListChangeListener.Change<? extends HorariosClass> c) -> {
            didSelectItem(c.getList().get(0));
        });
        horarios.addListener((ListChangeListener.Change<? extends HorariosClass> c) -> {
            //tableContent.setItems(horarios);
        });*/
    }
    
    // LOAD DATA
    private void loadData(){
        List<HashMap<String, Object>> data = db.getDataWithQuery(PersonasClass.getQuerytoAllItems());
        System.out.println("Data size: " + data.size());
        if (!data.isEmpty()){
            personas.clear();
            for(HashMap<String, Object> map:data){
                personas.add(new PersonasClass(map));
            }
            System.out.println("Se agregan " + personas.size() + " rows");
            tableContent.setItems(personas);
        }else{
            System.out.println("ES NULL");
        }
    }
    
    
    // ADMIN GENERIC CONTROLLER
    @Override
    public void notify(boolean willAppear) {
        if (willAppear){
            loadData();
        }
    }
    
}
