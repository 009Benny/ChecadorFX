/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Modules.Admin.Usuarios;

import Managers.DataBaseManager;
import DataBase.Models.UsuariosClass;
import DataBase.Tables.UsuariosTable;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
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
public class AdminUsuariosController implements Initializable {
    /*
     * USUARIOS VARS
    */
    @FXML
    private TextField txtFieldSearch;
    @FXML
    private TableView<UsuariosClass> tableContent;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEditUser;
    @FXML
    private Button btnDelete;
    DataBaseManager db = new DataBaseManager();
    ObservableList<UsuariosClass> usuarios = FXCollections.observableArrayList();
    ObservableList<UsuariosClass> usuariosSelected = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configViews();
        configListeners();
        loadData();
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
    
     // BUTTON ACTIONS
    private void configListeners(){
        usuariosSelected = tableContent.getSelectionModel().getSelectedItems();
        btnAdd.setOnAction(new EventHandler(){
            @Override
            public void handle(Event event) {
                addElement();
            }
        });
        btnEditUser.setOnAction(new EventHandler(){
            @Override
            public void handle(Event event) {
                editElement();
            }
        });
        btnDelete.setOnAction(new EventHandler(){
            @Override
            public void handle(Event event) {
                deleteElementSelected();
            }
        });
        txtFieldSearch.setOnKeyReleased((Event event) -> {
            didTextFieldSearchChange(); 
        });
        usuariosSelected.addListener((ListChangeListener.Change<? extends UsuariosClass> c) -> {
            btnDelete.setDisable(!(c.getList().size() >0));
        });
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
            System.out.println("Se agregaron " + usuarios.size() + " usuarios");
            tableContent.setItems(usuarios);
        }else{
            System.out.println("ES NULL");
        }
    }
    
    // Interactiones
    private void didTextFieldSearchChange(){
        String search = txtFieldSearch.getText();
        if(search.length() > 0){
//            String query = "";
//            if (search.matches("[0-9]+") && search.length() > 0) {
//                query = UsuariosClass.getQuerytoAllItemsByMatricula("'" + search + "%'");
//            } else {
//                query = PersonasClass.getQuerytoAllItemsByText("'" + search + "%'");
//            }
//            List<HashMap<String, Object>> data = db.getDataWithQuery(query);
//            if (!data.isEmpty()){
//                personas.clear();
//                for(HashMap<String, Object> map:data){
//                    personas.add(new PersonasClass(map, false));
//                }
//                tablePersonas.setItems(personas);
//            }else{
//                System.out.println("LOAD DATA SEARCH: NO REGRESA INFO");
//            }
        } else {
            loadData();
        }
    }
    
    private void addElement(){
    
    }
    
    private void editElement(){
    
    }
    
    private void deleteElementSelected(){
        if (usuariosSelected.size() == 1){
            UsuariosClass usuario = usuariosSelected.get(0);
            UsuariosTable usuariosTable = new UsuariosTable();
            db.deleteItem(usuariosTable.getTableName(), usuario.getIdentifierKey() + " = " + usuario.getIdUsuario());
            loadData();
        }
    }
}
