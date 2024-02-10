/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Modules.Admin.Servicios;

import DataBase.Models.ItemListClassProtocol;
import DataBase.Models.ServiciosClass;
import DataBase.Tables.ServiciosTable;
import DataBase.Tables.TableProtocol;
import Modules.Admin.BaseList;
import java.util.HashMap;

/**
 * FXML Controller class
 *
 * @author Benny
 */
public class ServiciosController extends BaseList {
    
    @Override
    public String[] getHeaders(){
        String[] headers = {"ID NIVEL", "NOMBRE"};
        return headers;
    }
    
    @Override
    public String[] getTableKeys(){
        String[] keys = {"id", "name"};
        return keys;
    }
    
    @Override
    public TableProtocol getTable(){
        return new ServiciosTable();
    }
    
    @Override
    public ItemListClassProtocol castItemList(HashMap<String, Object> map){
        return new ServiciosClass(map);
    }
//    public void addElement(){}
//    
//    /**
//    * This could be override to set the logic to delete an element
//    */
//    public void deleteElementSelected(){}
//    
//    /**
//    * This could be override to set the logic to search an element
//    */
//    public void didTextFieldSearchChange(){}
//    
//    /**
//    * This could be override to set the logic to load data that need to be extended of ItemListClassProtocol
//    */
//    private void loadData(){}
    
}
