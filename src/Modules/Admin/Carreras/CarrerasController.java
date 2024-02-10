/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Modules.Admin.Carreras;

import DataBase.Models.CarrerasClass;
import DataBase.Models.ItemListClassProtocol;
import DataBase.Tables.CarrerasTable;
import DataBase.Tables.TableProtocol;
import Modules.Admin.BaseList;
import java.util.HashMap;

/**
 * FXML Controller class
 *
 * @author Benny
 */
public class CarrerasController extends BaseList {
    
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
        return new CarrerasTable();
    }
    
    @Override
    public ItemListClassProtocol castItemList(HashMap<String, Object> map){
        return new CarrerasClass(map);
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
