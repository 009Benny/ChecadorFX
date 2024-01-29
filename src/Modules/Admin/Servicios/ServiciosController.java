/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Modules.Admin.Servicios;

import Modules.Admin.BaseList;

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
    public void loadData(){
//        List<HashMap<String, Object>> data = db.getDataWithQuery(NivelesClass.getQuerytoAllItems());
//        if (!data.isEmpty()){
//            items.clear();
//            for(HashMap<String, Object> map:data){
//                items.add(new NivelesClass(map));
//            }
//            System.out.println("Se agregaron " + items.size() + " items");
//            tableContent.setItems(items);
//        }else{
//            System.out.println("AdminNivelesController || No hay items");
//        }
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

