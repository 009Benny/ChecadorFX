/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modules.Admin;

import DataBase.Models.ItemListClassProtocol;
import DataBase.Tables.TableProtocol;
import Managers.DataBaseManager;
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
 *
 * @author Benny
 */
public class BaseList implements Initializable {
    @FXML
    public TextField txtFieldSearch;
    @FXML
    public TableView<ItemListClassProtocol> tableContent;
    @FXML
    public Button btnAdd;
    @FXML
    public Button btnDelete;
    public DataBaseManager db = new DataBaseManager();
    public ObservableList<ItemListClassProtocol> items = FXCollections.observableArrayList();
    public ObservableList<ItemListClassProtocol> itemsSelected = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configViews();
        configListeners();
        loadData();
    }
    
    /**
    * This could be override to set the headers of the table
    * @return String[]
    */
    public String[] getHeaders(){
        String[] headers = {};
        return headers;
    }
    
    /**
    * This could be override to set the keys to be used by the table to get values of the objects
    * @return String[]
    */
    public String[] getTableKeys(){
        String[] keys = {};
        return keys;
    }
    
    /**
    * This could be override to set the logic to add an element
    */
    public void addElement(){}
    
    /**
    * This could be override to set the logic to delete an element
    */
    public void deleteElementSelected(){}
    
    /**
    * This could be override to set the logic to search an element
    */
    public void didTextFieldSearchChange(){}
    
    /**
    * This could be override to set the logic to load data that need to be extended of ItemListClassProtocol
    */
    public void loadData(){
        List<HashMap<String, Object>> data = db.getDataWithQuery(getTable().getAllItemsQuery());
        if (!data.isEmpty()){
            items.clear();
            for(HashMap<String, Object> map:data){
                items.add(castItemList(map));
            }
            System.out.println("Se agregaron " + items.size() + " items");
            tableContent.setItems(items);
        }else{
            System.out.println("AdminNivelesController || No hay items");
        }
    }
    
    public TableProtocol getTable(){
        throw new UnsupportedOperationException("BaseList - Debes regresar un objeto de la tabla.");
    }
    
    public ItemListClassProtocol castItemList(HashMap<String, Object> map){
        throw new UnsupportedOperationException("BaseList - Debes castear el objeto.");
    }
    
    // VIEW ALTERATION
    private void configViews(){
        // TABLE
        if (tableContent.getColumns().isEmpty()){
            addColumns();
        }
    }
    
    private void addColumns(){
        String[] headers = this.getHeaders();
        String[] keys = this.getTableKeys();

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
        itemsSelected = tableContent.getSelectionModel().getSelectedItems();
        btnAdd.setOnAction(new EventHandler(){
            @Override
            public void handle(Event event) {
                addElement();
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
        itemsSelected.addListener((ListChangeListener.Change<? extends ItemListClassProtocol> c) -> {
            btnDelete.setDisable(!(c.getList().size() >0));
        });
    }
}
