/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modules.Admin;

import Models.DataBaseManager;
import Models.UsuariosClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
        //configViews();
        //configListeners();
    }

    // ADMIN GENERIC CONTROLLER
    @Override
    public void notify(boolean willAppear) {
        
    }
}
