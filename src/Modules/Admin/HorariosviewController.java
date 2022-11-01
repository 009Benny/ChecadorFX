/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modules.Admin;

import Models.DataBaseManager;
import Models.DefaultData;
import Models.HorariosClass;
import com.mysql.cj.protocol.Resultset;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author bennyreyes
 */
public class HorariosviewController implements AdminGenericController {
    ObservableList<HorariosClass> horarios;
    DataBaseManager db;
    TableView<HorariosClass> tableContent;
    
    public HorariosviewController(TableView<HorariosClass> table){
        horarios = FXCollections.observableArrayList();
        db = new DataBaseManager();
        tableContent = table;
        configTable();
    }
    
    // CONFIGURATION 
    private void configTable(){
        // COLUMNS
        if (tableContent.getColumns().isEmpty()){
            addColumns();
        }
    }
    
    private void addColumns(){
        System.out.println("ADD COLUMNS");
        String[] headers = {"Horario ID", "Nombre", "Fecha inicio", "Fecha final", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
        String[] keys = {"idHorario", "name", "starDate", "endDate", "lunes", "martes", "miercoles", "jueves", "viernes", "sabado", "domingo"};

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
        ResultSet set;
        set = db.getData(DataBaseManager.horarios_table);
        System.out.println("LOAD DATA");
        if (set != null){
            List<HorariosClass> list = new ArrayList();
            try{
                System.out.println("si entro al try");
                while(set.next()){
                    HorariosClass obj = new HorariosClass(set);
                    list.add(obj);
                }
                System.out.println("si termino de castear");
                horarios = FXCollections.observableArrayList();
                for(HorariosClass item:list){
                    System.out.println("Horario: " + item.getName() + ", id: " + item.getId());
                }
                horarios.addAll(list);
            }catch(SQLException e){
                Logger.getLogger(DefaultData.class.getName()).log(Level.SEVERE, null, e);
            }
        }else{
            System.out.println("ES NULL");
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
