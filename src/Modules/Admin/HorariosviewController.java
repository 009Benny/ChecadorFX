 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modules.Admin;

import DataBase.Models.HorariosClass;
import DataBase.Tables.HorariosTable;
import DataBase.DataBaseManager;
import Models.DayEnum;
import java.util.HashMap;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
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
    TextField txtFieldName;
    TextField txtFieldIdentifier;
    TextField txtFieldStartDate;
    TextField txtFieldEndDate;
    Button btnDelete;
    Button btnEdit;
    Button btnClear;
    Button btnNew;
    
    DataBaseManager db = new DataBaseManager();
    
    public HorariosviewController(TableView<HorariosClass> table, VBox form, Button btnNew, Button btnClear, Button btnEdit, Button btnDelete){
        this.tableContent = table;
        horariosSelected = tableContent.getSelectionModel().getSelectedItems();
        this.btnNew = btnNew;
        this.btnClear = btnClear;
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
            }else if(item instanceof TextField){
                if (this.txtFieldName == null){
                   this.txtFieldName = (TextField)item;
                }else if (this.txtFieldIdentifier == null ){
                    this.txtFieldIdentifier = (TextField)item;
                }else if (this.txtFieldStartDate == null ){
                    this.txtFieldStartDate = (TextField)item;
                }else if (this.txtFieldEndDate == null ){
                    this.txtFieldEndDate = (TextField)item;
                }
            }
        });
    }
    
    private void configListeners(){
        horariosSelected.addListener((ListChangeListener.Change<? extends HorariosClass> c) -> {
            if (c.getList().size() >0){
                didSelectItem(c.getList().get(0));
            }
        });
        horarios.addListener((Change<? extends HorariosClass> c) -> {
            tableContent.setItems(horarios);
        });
        
        btnClear.setOnAction(new EventHandler(){
            @Override
            public void handle(Event event) {
                clearSelection();
            }
        });
        
        btnEdit.setOnAction(new EventHandler(){
            @Override
            public void handle(Event event) {
                updateItem();
            }
        });
        
        btnNew.setOnAction(new EventHandler(){
            @Override
            public void handle(Event event) {
                createItem();
            }
        });
        
        btnDelete.setOnAction(new EventHandler(){
            @Override
            public void handle(Event event) {
                deleteItem();
            }
        });
    }
    
    // ALTER DATA   
    private void updateItem(){
        if(horariosSelected.size()==1){
            int identifier = horariosSelected.get(0).getIdHorario();
            HorariosClass item = getFormData();
            HorariosTable tableH = new HorariosTable();
            db.updateItem(tableH.getTableName(), item.getUpdateQuery(), "`" + item.getKeyId() + "` = " + identifier);
            loadData();
        }
    }
    
    private void deleteItem(){
        if(horariosSelected.size()==1){
            HorariosTable horariosTable = new HorariosTable();
            HorariosClass item = horariosSelected.get(0);
            db.deleteItem(horariosTable.getTableName(),  item.getKeyId() + " = " + item.getIdHorario());
            loadData();
        }
    }
    
    private void createItem(){
        if(horariosSelected.isEmpty()){
            HorariosClass item = getFormData();
            HorariosTable horariosTable = new HorariosTable();
            db.createItem(horariosTable.getTableName(), item.getInsertHeader(), item.getValuesQuery());
            loadData();
        }
    }
    
    private void clearSelection(){
        tableContent.getSelectionModel().clearSelection();
        clearForm();
    }
    
    /*
    * VIEW ALTERATION
    */
    private void addColumns(){
        String[] headers = {"Horario ID", "Nombre", "Fecha inicio", "Fecha final", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo", "Tolerancia"};
        String[] keys = {"idHorario", "name", "startDate", "endDate", "lunes", "martes", "miercoles", "jueves", "viernes", "sabado", "domingo", "tolerancia"};

        TableColumn[] columns = new TableColumn[headers.length];
        for(int i=0;i<headers.length;i++){
            TableColumn c = new TableColumn(headers[i]);
            
            c.setCellValueFactory(new PropertyValueFactory(keys[i]));
            columns[i] =  c;
        }
        tableContent.getColumns().addAll(columns);
    }
    
    private void setItemToForm(HorariosClass item){
        txtFieldName.setText(item.getName());
        txtFieldIdentifier.setText(Integer.toString(item.getIdHorario()));
        txtFieldStartDate.setText(item.getStartDate());
        txtFieldEndDate.setText(item.getEndDate());
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
    
    private void clearForm(){
        txtFieldName.setText("");
        txtFieldIdentifier.setText("");
        txtFieldStartDate.setText("");
        txtFieldEndDate.setText("");
        // CHECKBOX
        for(int i=0;i<checkDays.size();i++){
            checkDays.get(i).setSelected(false);
        }
        // HBOX
        for(int i=0;i<boxDays.size();i++){
            VBox start = (VBox) boxDays.get(i).getChildren().get(0);
            start.getChildren().forEach(view -> {
                if(view instanceof TextField){
                    TextField tf = (TextField)view;
                    tf.setText("");
                }
            });
            VBox end = (VBox) boxDays.get(i).getChildren().get(1);
            end.getChildren().forEach(view -> {
                if(view instanceof TextField){
                    TextField tf = (TextField)view;
                    tf.setText("");
                }
            });
        }
    }
    
    private HorariosClass getFormData(){
        HorariosClass item;
        if (horariosSelected.isEmpty()){
            item = HorariosClass.getEmptyObj("Prueba");
        }else{
            item = horariosSelected.get(0);
        }
        item.setName(txtFieldName.getText());
        String aux = txtFieldIdentifier.getText();
        item.setIdHorario(Integer.parseInt(aux));
        item.setStartDate(txtFieldStartDate.getText());
        item.setEndDate(txtFieldEndDate.getText());
        // CHECKBOX
        for(int i=0;i<checkDays.size();i++){
            if(checkDays.get(i).isSelected()){
                String date = "";
                // START
                VBox start = (VBox) boxDays.get(i).getChildren().get(0);
                for(Node n:start.getChildren()){
                    if(n instanceof TextField){
                        TextField tf = (TextField)n;
                        date += tf.getText();
                    }
                }
                date+="-";
                //END
                VBox end = (VBox) boxDays.get(i).getChildren().get(1);
                for(Node n:end.getChildren()){
                    if(n instanceof TextField){
                        TextField tf = (TextField)n;
                        date += tf.getText();
                    }
                }
                item.setValueOfDay(date, DayEnum.values()[i]);
            }else{
                item.setValueOfDay("", DayEnum.values()[i]);
            }
        }
        return item;
    }
    
    // LOAD DATA
    private void loadData(){
        HorariosTable horariosTable = new HorariosTable();
        List<HashMap<String, Object>> data = db.getData(horariosTable.getTableName());
        if (!data.isEmpty()){
            horarios.clear();
            for(HashMap<String, Object> map:data){
                horarios.add(new HorariosClass(map));
            }
            tableContent.setItems(horarios);
        }else{
            System.out.println("ES NULL");
        }
    }
    
    // EVENTS
    private void didSelectItem(HorariosClass item){
        if (item != null){ 
            System.out.println("Se selecciono: " + item.getName());
            setItemToForm(item);
        }
    }

    // ADMIN GENERIC CONTROLLER
    @Override
    public void notify(boolean willAppear) {
        if (willAppear){
            loadData();
        }
    }
    
}
