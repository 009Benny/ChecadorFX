/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modules.Admin;

import Models.DataBaseManager;
import Models.HorariosClass;
import java.util.HashMap;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author bennyreyes
 */
public class HorariosviewController implements AdminGenericController {
    ObservableList<HorariosClass> horarios;
    ObservableList<HorariosClass> horariosSelected;
    DataBaseManager db;
    TableView<HorariosClass> tableContent;
    
    public HorariosviewController(TableView<HorariosClass> table){
        horarios = FXCollections.observableArrayList();
        db = new DataBaseManager();
        tableContent = table;
        horariosSelected = tableContent.getSelectionModel().getSelectedItems();
        configTable();
        configListeners();
    }
    
    // CONFIGURATION 
    private void configTable(){
        // COLUMNS
        if (tableContent.getColumns().isEmpty()){
            addColumns();
        }
    }
    
    private void configListeners(){
        horariosSelected.addListener((ListChangeListener.Change<? extends HorariosClass> c) -> {
            didSelectItem(c.getList().get(0));
        });
        horarios.addListener((Change<? extends HorariosClass> c) -> {
            System.out.println("OnChange");
            tableContent.setItems(horarios);
        });
    }
    
    private void addColumns(){
        String[] headers = {"Horario ID", "Nombre", "Fecha inicio", "Fecha final", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
        String[] keys = {"idHorario", "name", "startDate", "endDate", "lunes", "martes", "miercoles", "jueves", "viernes", "sabado", "domingo"};

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
        List<HashMap<String, Object>> data = db.getData(DataBaseManager.horarios_table);
        if (!data.isEmpty()){
            horarios.stream().forEach((item) -> {
                horarios.remove(item);
            });
            for(HashMap<String, Object> map:data){
                horarios.add(new HorariosClass(map));
            }
        }else{
            System.out.println("ES NULL");
        }
    }
    
    // EVENTS
    private void didSelectItem(HorariosClass item){
        System.out.println(item.getName());
    }

    // ADMIN GENERIC CONTROLLER
    @Override
    public void didAppear() {
        loadData();
    }

    @Override
    public void didDissappear() {
        System.out.println("didDissappear");
    }
    
}
