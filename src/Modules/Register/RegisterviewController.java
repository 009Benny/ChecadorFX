/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Modules.Register;

import Extensions.StringExtension;
import Models.DataBaseManager;
import Models.PersonasClass;
import Models.RegistroClass;
import checadorfx.ChecadorFX;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author bennyreyes
 */
public class RegisterviewController implements Initializable {
    ObservableList<RegistroClass> registros = FXCollections.observableArrayList();
    DataBaseManager db = new DataBaseManager();
    @FXML
    private TableView<RegistroClass> tableContent;
    @FXML
    private Label labelTime;
    @FXML
    private TextField textFieldMatricula;
    @FXML
    private PasswordField textFieldPassword;
    @FXML
    private Button btnRegister;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configViews();
        loadData();
    }
    
     // CONFIGURATION 
    private void configViews(){
        // TABLE
        if (tableContent.getColumns().isEmpty()){
            addColumns();
        }
        // Register Form
        textFieldMatricula.setText("");
        textFieldPassword.setText("");
        btnRegister.setDisable(true);
        
        textFieldMatricula.setOnKeyReleased(new EventHandler(){
            @Override
            public void handle(Event event) {
                didTextFieldChange();
            } 
        });
        
        textFieldPassword.setOnKeyReleased(new EventHandler(){
            @Override
            public void handle(Event event) {
                didTextFieldChange();
            } 
        });
    }
    
    // VIEW ALTERATION
    private void addColumns(){
        String[] headers = {"Fecha", "Status", "Nombre"};
        String[] keys = {"checkDate", "status", "namePersona"};

        TableColumn[] columns = new TableColumn[headers.length];
        for(int i=0;i<headers.length;i++){
            TableColumn c = new TableColumn(headers[i]);
            
            c.setCellValueFactory(new PropertyValueFactory(keys[i]));
            columns[i] =  c;
        }
        tableContent.getColumns().addAll(columns);
    }
    
    private void showMessage(String message){
        Alert alert = new Alert(AlertType.WARNING);
        alert.setContentText(message);
        alert.show();
    }
    
    // LOAD DATA
    private void loadData(){
        List<HashMap<String, Object>> data = db.getDataWithQuery(RegistroClass.getQuerytoAllItems());
        System.out.println("Data size: " + data.size());
        if (!data.isEmpty()){
            registros.clear();
            for(HashMap<String, Object> map:data){
                registros.add(new RegistroClass(map));
            }
            System.out.println("Se agregan " + registros.size() + " rows");
            tableContent.setItems(registros);
        }else{
            System.out.println("ES NULL");
        }
    }
    
    /// this method will validate the data
    private void registerUser(String matricula, String password){
        if (StringExtension.onlyDigits(matricula) && matricula.length() > 5){
            PersonasClass persona = PersonasClass.getPersonaBy(matricula);
            int count = db.getCountOf(DataBaseManager.registros_table);
            RegistroClass last =  RegistroClass.getLastRegistroBy(matricula);
            boolean status = (last != null) ? !last.isSalida() : false;
            if (persona != null){
                RegistroClass registro = new RegistroClass(count + 1, new Date(), status, persona.getIdPersona());
                db.createItem(DataBaseManager.registros_table, RegistroClass.getQueryFields() , registro.getQueryValues());
                loadData();
                showMessage("Se registro la entrada correctamente");
            }else{
                showMessage("La persona con la matricula " + matricula + "no existe");
            }
        }else{
            showMessage("Ingresa una matricula valida");
        }
        textFieldMatricula.setText("");
        textFieldPassword.setText("");
    }
    
    /*
    * Gestures
    */
    @FXML
    private void didTapRegisterButton() throws IOException {
       registerUser(textFieldMatricula.getText(), textFieldPassword.getText());
    }
    
    @FXML
    private void didTapBackButton() throws IOException {
       ChecadorFX.showMenu();
    }    
    
    // This method will enable/disable the button to make a register
    private void didTextFieldChange(){
        String matricula = textFieldMatricula.getText();
        String password = textFieldPassword.getText();
        btnRegister.setDisable(!(matricula.length() >= 6 && password.length() >= 6));
    }
    
}
