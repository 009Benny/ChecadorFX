/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modules.Admin;

import Models.DataBaseManager;
import Models.PersonasClass;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
    ObservableList<PersonasClass> personasSuccess = FXCollections.observableArrayList();
    ObservableList<PersonasClass> personasFailure = FXCollections.observableArrayList();
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
            String[] headers = {"Persona ID", "Nombre", "Apellido Paterno", "Apellido Materno", "Email Personal", "Email Institucional", "Celular", "Carrera", "Facultad", "Horario"};
            String[] keys = {"idPersona", "nombre", "apPaterno", "apMaterno", "perEmail", "instEmail", "phone", "carrera", "facultad", "horario"};
            configColumnsToTable(tableContent, headers, keys);
        }
        if (tableSuccess.getColumns().isEmpty()){
            String[] headers = {"Persona ID", "Nombre"};
            String[] keys = {"idPersona", "nombre"};
            configColumnsToTable(tableSuccess, headers, keys);
        }
        if (tableFailure.getColumns().isEmpty()){
            String[] headers = {"Linea"};
            String[] keys = {"lineFailure"};
            configColumnsToTable(tableFailure, headers, keys);
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
    
    private void configColumnsToTable(TableView table, String[] headers, String[] keys){
        TableColumn[] columns = new TableColumn[headers.length];
        for(int i=0;i<headers.length;i++){
            TableColumn c = new TableColumn(headers[i]);
            
            c.setCellValueFactory(new PropertyValueFactory(keys[i]));
            columns[i] =  c;
        }
        table.getColumns().addAll(columns);
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
        System.out.println(file.list());
        // REMOVE HEADER
        
        personasSuccess.clear();
        personasFailure.clear();
        for(String line:getListFromCSV(file.getAbsolutePath())){
            System.out.println(line);
            if(!line.startsWith("ID,NOMBRE")){
                PersonasClass persona = new PersonasClass(line);
                if (persona.isValid()){
                    personasSuccess.add(persona);
                }else{
                    personasFailure.add(persona);
                }
            }
        }
        System.out.println("SUCCES: " + personasSuccess.size());
        System.out.println("FAILURE: " + personasFailure.size());
        tableSuccess.setItems(personasSuccess);
        tableFailure.setItems(personasFailure);
    }
    
    private List<String> getListFromCSV(String path){
        List<String> lines = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return lines;
    }
    
    private static File getFile(){
        JFileChooser fc = new JFileChooser();
        File file = null;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Solo soporto csv", "csv");
        fc.setFileFilter(filter);
        int selected = fc.showOpenDialog(null);
        if (selected == JFileChooser.APPROVE_OPTION){
            file = fc.getSelectedFile();
        }
        return file;
    }
    
    private void downloadFormatAction() throws IOException{
        String home = System.getProperty("user.home");
        String project = System.getProperty("user.dir");
        copyFile(new File(project + "/src/Data/PersonasFormat.csv"), new File(home + "/Downloads/PersonasFormat.csv"));
    }
    
    private void copyFile(File sourceFile, File destFile) throws IOException {
        if (!sourceFile.exists()) {
            System.out.println("src: " + sourceFile.getAbsolutePath());
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
        if (!data.isEmpty()){
            personas.clear();
            for(HashMap<String, Object> map:data){
                personas.add(new PersonasClass(map));
            }
            tableContent.setItems(personas);
        }else{
            System.out.println("LOAD DATA: NO REGRESA INFO");
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
