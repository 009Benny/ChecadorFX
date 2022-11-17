/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modules.Admin;

import Models.DataBaseManager;
import Models.HorariosClass;
import Models.PersonasClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author bennyreyes
 */
public class PersonasviewController implements AdminGenericController {
    DataBaseManager db = new DataBaseManager();
    TableView<PersonasClass> tableContent;
    TableView<PersonasClass> tableSuccess;
    TableView<PersonasClass> tableFailure;
    ObservableList<PersonasClass> personas = FXCollections.observableArrayList();
    ObservableList<PersonasClass> personasSelected = FXCollections.observableArrayList();
    TextField txtFieldSearch;
    Button btnAdd;
    Button btnDownloadFormat;
    Button btnDelete;
    
    public PersonasviewController(TableView<PersonasClass> table, TableView<PersonasClass> tableSuccess, TableView<PersonasClass> tableFailure, TextField search, Button add, Button download, Button delete){
        this.tableContent = table;
        this.tableSuccess = tableSuccess;
        this.tableFailure = tableFailure;
        this.txtFieldSearch = search;
        this.btnAdd = add;
        this.btnDownloadFormat = download;
        this.btnDelete = delete;
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
    
    // VIEW ALTERATION
    private void addColumns(){
        String[] headers = {"Persona ID", "Nombre", "Apellido Paterno", "Apellido Materno", "Email Personal", "Email Institucional", "Celular", "Carrera", "Facultad", "Horario"};
        String[] keys = {"idPersona", "nombre", "apPaterno", "apMaterno", "perEmail", "instEmail", "phone", "carrera", "facultad", "horario"};

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
         btnAdd.setOnAction(new EventHandler(){
            @Override
            public void handle(Event event) {
                addElements();
            }
        });
        btnDownloadFormat.setOnAction(new EventHandler(){
            @Override
            public void handle(Event event) {
                try {
                    downloadFormatAction();
                } catch (IOException ex) {
                    Logger.getLogger(PersonasviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        /*horariosSelected.addListener((ListChangeListener.Change<? extends HorariosClass> c) -> {
            didSelectItem(c.getList().get(0));
        });
        horarios.addListener((ListChangeListener.Change<? extends HorariosClass> c) -> {
            //tableContent.setItems(horarios);
        });*/
    }
    
    private void addElements(){
        File file = getFile();
    }
    
    private static File getFile(){
        System.out.println("getFile");
        JFileChooser fc = new JFileChooser();
        File file = null;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Solo soporto csv", "csv");
        fc.setFileFilter(filter);
        int selected = fc.showOpenDialog(null);
        if (selected == JFileChooser.APPROVE_OPTION){
            System.out.println("APPROVE");
            file = fc.getSelectedFile();
        }
        System.out.println("TERMINO");
        
        
        return file;
    }
    
    private void downloadFormatAction() throws IOException{
        copyFile(new File("/Users/bennyreyes/Developer/FIME/ChecadorFX/src/Data/PersonasFormat.csv"), new File("/Users/bennyreyes/Downloads/PersonasFormat.csv"));
    }
    
    private void copyFile(File sourceFile, File destFile) throws IOException {
        System.out.println("LO INTENTA");
        if (!sourceFile.exists()) {
            JOptionPane.showMessageDialog(null, "Hubo un error, no se enconro el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!destFile.exists()) {
            destFile.createNewFile();
        }else{
            JOptionPane.showMessageDialog(null, "El archivo ya existe.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        FileChannel source = null;
        FileChannel destination = null;
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        if (destination != null && source != null) {
            destination.transferFrom(source, 0, source.size());
        }
        if (source != null) {
            source.close();
        }
        if (destination != null) {
            destination.close();
        }
        JOptionPane.showMessageDialog(null, "Se descargó correctamente en su carpeta de descargas.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    // LOAD DATA
    private void loadData(){
        List<HashMap<String, Object>> data = db.getDataWithQuery(PersonasClass.getQuerytoAllItems());
        System.out.println("Data size: " + data.size());
        if (!data.isEmpty()){
            personas.clear();
            for(HashMap<String, Object> map:data){
                personas.add(new PersonasClass(map));
            }
            System.out.println("Se agregan " + personas.size() + " rows");
            tableContent.setItems(personas);
        }else{
            System.out.println("ES NULL");
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
