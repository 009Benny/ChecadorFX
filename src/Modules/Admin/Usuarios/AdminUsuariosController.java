/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Modules.Admin.Usuarios;

import DataBase.DataBaseManager;
import DataBase.Models.UsuariosClass;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TableView<UsuariosClass> tableSuccess;
    @FXML
    private TableView<UsuariosClass> tableFailure;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDownloadFormat;
    @FXML
    private Button btnDelete;
    DataBaseManager db = new DataBaseManager();
    ObservableList<UsuariosClass> usuarios = FXCollections.observableArrayList();
    ObservableList<UsuariosClass> usuariosSelected = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configViews();
        //configListeners();
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
}
