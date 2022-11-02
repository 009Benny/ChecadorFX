/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modules.Admin;

import Models.DataBaseManager;
import Models.DayEnum;
import Models.HorariosClass;
import java.util.HashMap;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author bennyreyes
 */
public class HorariosviewController implements AdminGenericController {
    TableView<HorariosClass> tableContent;
    ObservableList<HorariosClass> horarios = FXCollections.observableArrayList();
    ObservableList<HorariosClass> horariosSelected = FXCollections.observableArrayList();
    ObservableList<CheckBox> checkDays = FXCollections.observableArrayList();
    ObservableList<HBox> boxDays = FXCollections.observableArrayList();
    Button btnDelete;
    Button btnEdit;
    
    DataBaseManager db = new DataBaseManager();
    
    
    public HorariosviewController(TableView<HorariosClass> table, VBox form, Button btnEdit, Button btnDelete){
        this.tableContent = table;
        horariosSelected = tableContent.getSelectionModel().getSelectedItems();
        this.btnEdit = btnEdit;
        this.btnDelete = btnDelete;
        setComponents(form);
        configViews();
        configListeners();
    }
    
    // CONFIGURATION 
    private void configViews(){
        // TABLE
        if (tableContent.getColumns().isEmpty()){
            addColumns();
        }
    }
    
    private void setComponents(VBox form){
        form.getChildren().forEach((item) -> {
            if(item instanceof CheckBox){
                this.checkDays.add((CheckBox)item);
            }else if (item instanceof HBox){
                this.boxDays.add((HBox)item);
            }
        });
    }
    
    private void configListeners(){
        horariosSelected.addListener((ListChangeListener.Change<? extends HorariosClass> c) -> {
            didSelectItem(c.getList().get(0));
        });
        horarios.addListener((Change<? extends HorariosClass> c) -> {
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
        setItemToForm(item);
    }
    
    private void setItemToForm(HorariosClass item){
        // CHECKBOX
        for(int i=0;i<checkDays.size();i++){
            checkDays.get(i).setSelected(item.haveDateForDay(i));
        }
        // HBOX
        for(int i=0;i<boxDays.size();i++){
            String[] values = item.getValueOfDay(DayEnum.values()[i]).split("-");
            //((VBox)boxDays.get(i).getChildren().get(0)).getChildren()
            if (values.length == 2){
                VBox start = (VBox) boxDays.get(i).getChildren().get(0);
                start.getChildren().forEach(view -> {
                    if(view instanceof TextField){
                        TextField tf = (TextField)view;
                        tf.setText(values[0]);
                    }
                });
                VBox end = (VBox) boxDays.get(i).getChildren().get(1);
                end.getChildren().forEach(view -> {
                    if(view instanceof TextField){
                        TextField tf = (TextField)view;
                        tf.setText(values[1]);
                    }
                });
            }   
        }
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
