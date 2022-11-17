/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modules.Admin;

import Models.DataBaseManager;
import Models.UsuariosClass;
import java.util.HashMap;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author bennyreyes
 */
public class UsuariosviewController implements AdminGenericController{
    DataBaseManager db = new DataBaseManager();
    TableView<UsuariosClass> tableContent;
    TableView<UsuariosClass> tableSuccess;
    TableView<UsuariosClass> tableFailure;
    ObservableList<UsuariosClass> usuarios = FXCollections.observableArrayList();
    ObservableList<UsuariosClass> usuariosSelected = FXCollections.observableArrayList();
    TextField txtFieldSearch;
    Button btnAdd;
    Button btnDownloadFormat;
    Button btnDelete;
    
    public UsuariosviewController(TableView<UsuariosClass> table, TableView<UsuariosClass> tableSuccess, TableView<UsuariosClass> tableFailure, TextField search, Button add, Button download, Button delete){
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

    private void configViews(){
        // TABLE
        if (tableContent.getColumns().isEmpty()){
            addColumns();
        }
    }
    
    // VIEW ALTERATION
    private void addColumns(){
        String[] headers = {"ID USUARIO", "Nombre", "NIVEL"};
        String[] keys = {"idUsuario", "nombre", "idNivel"};

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
        List<HashMap<String, Object>> data = db.getDataWithQuery(UsuariosClass.getQuerytoAllItems());
        System.out.println("Data size: " + data.size());
        System.out.println(data);
        if (!data.isEmpty()){
            usuarios.clear();
            for(HashMap<String, Object> map:data){
                usuarios.add(new UsuariosClass(map));
            }
            System.out.println("Se agregaron " + usuarios.size() + " usuarios" );
            tableContent.setItems(usuarios);
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
