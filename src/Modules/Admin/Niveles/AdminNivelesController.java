/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Modules.Admin.Niveles;

import DataBase.Models.ItemListClassProtocol;
import DataBase.Models.NivelesClass;
import DataBase.Tables.NivelesTable;
import DataBase.Tables.TableProtocol;
import Modules.Admin.BaseList;
import java.util.HashMap;
import java.util.List;

/**
 * FXML Controller class
 *
 * @author Benny
 */
public final class AdminNivelesController extends BaseList {
    
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
        return new NivelesTable();
    }
    
    @Override
    public ItemListClassProtocol castItemList(HashMap<String, Object> map){
        return new NivelesClass(map);
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
