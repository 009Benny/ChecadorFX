/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Modules.Admin.Usuarios;

import DataBase.Models.NivelesClass;
import Managers.DataBaseManager;
import DataBase.Models.UsuariosClass;
import DataBase.Tables.NivelesTable;
import DataBase.Tables.UsuariosTable;
import java.awt.GridLayout;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * FXML Controller class
 *
 * @author bennyreyes
 */
public class AdminUsuariosController implements Initializable {
    /*
     * USUARIOS VARS
    */
    @FXML
    private TextField txtFieldSearch;
    @FXML
    private TableView<UsuariosClass> tableContent;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    DataBaseManager db = new DataBaseManager();
    ObservableList<UsuariosClass> usuarios = FXCollections.observableArrayList();
    ObservableList<UsuariosClass> usuariosSelected = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configViews();
        configListeners();
        loadData();
    }    
    
    private void configViews(){
        // TABLE
        if (tableContent.getColumns().isEmpty()){
            addColumns();
        }
    }
    
    // VIEW ALTERATION
    private void addColumns(){
        String[] headers = {"ID USUARIO", "Nombre", "NIVEL"};
        String[] keys = {"idUsuario", "nombre", "nivel"};

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
        usuariosSelected = tableContent.getSelectionModel().getSelectedItems();
        btnAdd.setOnAction(new EventHandler(){
            @Override
            public void handle(Event event) {
                addElement();
            }
        });
        btnDelete.setOnAction(new EventHandler(){
            @Override
            public void handle(Event event) {
                deleteElementSelected();
            }
        });
        txtFieldSearch.setOnKeyReleased((Event event) -> {
            didTextFieldSearchChange(); 
        });
        usuariosSelected.addListener((ListChangeListener.Change<? extends UsuariosClass> c) -> {
            btnDelete.setDisable(!(c.getList().size() >0));
        });
    }
    
    // LOAD DATA
    private void loadData(){
        List<HashMap<String, Object>> data = db.getDataWithQuery(UsuariosClass.getQuerytoAllItems());
        if (!data.isEmpty()){
            usuarios.clear();
            for(HashMap<String, Object> map:data){
                usuarios.add(new UsuariosClass(map));
            }
            System.out.println("Se agregaron " + usuarios.size() + " usuarios");
            tableContent.setItems(usuarios);
        }else{
            System.out.println("ES NULL");
        }
    }
    
    // Interactiones
    private void didTextFieldSearchChange(){
        String search = txtFieldSearch.getText();
        if(search.length() > 0){
//            String query = "";
//            if (search.matches("[0-9]+") && search.length() > 0) {
//                query = UsuariosClass.getQuerytoAllItemsByMatricula("'" + search + "%'");
//            } else {
//                query = PersonasClass.getQuerytoAllItemsByText("'" + search + "%'");
//            }
//            List<HashMap<String, Object>> data = db.getDataWithQuery(query);
//            if (!data.isEmpty()){
//                personas.clear();
//                for(HashMap<String, Object> map:data){
//                    personas.add(new PersonasClass(map, false));
//                }
//                tablePersonas.setItems(personas);
//            }else{
//                System.out.println("LOAD DATA SEARCH: NO REGRESA INFO");
//            }
        } else {
            loadData();
        }
    }
    
    private void addElement(){
        JPanel panel = new JPanel(new GridLayout(4, 2));
        JTextField nameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        // PENDIENTE DE OPTIMIZAR
        List<NivelesClass> niveles = getNivelesOptions();
        JComboBox<String> nivelOptionsBox = new JComboBox<>(getNames(niveles));

        // Building Form
        panel.add(new JLabel("Nombre:"));
        panel.add(nameField);
        panel.add(new JLabel("Contraseña:"));
        panel.add(passwordField);
        panel.add(new JLabel("Seleccionar nivel:"));
        panel.add(nivelOptionsBox);
        
        int result = JOptionPane.showConfirmDialog(null, panel, "Agregar Usuario", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String password =  new String(passwordField.getPassword());
            int index = nivelOptionsBox.getSelectedIndex();
            NivelesClass itemSelected = niveles.get(index);
            UsuariosTable table = new UsuariosTable();
            int count = db.getCountOf(table.getTableName());
            UsuariosClass newUser = new UsuariosClass(count + 1, name, password, itemSelected);
            saveNewElement(newUser);
            loadData();
        }
    }
    
    private void saveNewElement(UsuariosClass usuario){
        UsuariosTable table = new UsuariosTable();
        db.createItem(table.getTableName(), usuario.getInsertHeader(), usuario.getValuesQuery());
    }
    
    private void deleteElementSelected(){
        if (usuariosSelected.size() == 1){
            UsuariosClass usuario = usuariosSelected.get(0);
            UsuariosTable usuariosTable = new UsuariosTable();
            db.deleteItem(usuariosTable.getTableName(), usuario.getIdentifierKey() + " = " + usuario.getIdUsuario());
            loadData();
        }
    }
    
    // AUXILIAR METHODS
    private static String[] getNames(List<NivelesClass> list) {
        String[] namesArray = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            namesArray[i] = list.get(i).getName();
        }
        return namesArray;
    }
    
    private List<NivelesClass> getNivelesOptions(){
        List<NivelesClass> niveles = new ArrayList<>();
        List<HashMap<String, Object>> data = db.getDataWithQuery(NivelesClass.getQuerytoAllItems());
        if (!data.isEmpty()){
           for(HashMap<String, Object> map:data){
               niveles.add(new NivelesClass(map));
           }
        }else{
            System.out.println("AdminUsuariosController || Niveles vacio");
        }
        return niveles;
    }
}
