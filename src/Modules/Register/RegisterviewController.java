/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Modules.Register;

import DataBase.Models.PersonasClass;
import DataBase.Models.RegistrosClass;
import DataBase.Tables.RegistrosTable;
import Extensions.DateExtension;
import Extensions.StringExtension;
import Models.DataBaseManager;
import checadorfx.ChecadorFX;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
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
    ObservableList<RegistrosClass> registros = FXCollections.observableArrayList();
    DataBaseManager db = new DataBaseManager();
    @FXML
    private TableView<RegistrosClass> tableContent;
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
        startHourCounter();
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
        List<HashMap<String, Object>> data = db.getDataWithQuery(RegistrosClass.getQuerytoAllItems());
        System.out.println("Data size: " + data.size());
        if (!data.isEmpty()){
            registros.clear();
            for(HashMap<String, Object> map:data){
                registros.add(new RegistrosClass(map));
            }
            System.out.println("RegisterView || Se agregan " + registros.size() + " rows");
            tableContent.setItems(registros);
        }else{
            System.out.println("RegisterView || No tiene elementos en la base de datos");
        }
    }
    
    /// this method will validate the data
    private void registerUser(String matricula, String password){
        RegistrosTable registros = new RegistrosTable();
        
        if (StringExtension.onlyDigits(matricula) && matricula.length() > 5){
            if (password.length() >= StringExtension.kPASSWORD_LENGTH){
                PersonasClass persona = PersonasClass.getPersonaBy(matricula);
                if (persona != null){
                    if (persona.getPassword().equals(password)){
                        int count = db.getCountOf(registros.getTableName());
                        RegistrosClass last =  RegistrosClass.getLastRegistroBy(matricula);
                        boolean status = (last != null) ? !last.isSalida() : false;

                        // CREAR REGISTRO
                        String today = DateExtension.getStringDate(new Date(), "dd/MM/YYYY");
                        RegistrosClass registro = new RegistrosClass(count + 1, today, !status, matricula);
                        db.createItem(registros.getTableName(), registro.getInsertHeader() , registro.getValuesQuery());
                        loadData();
                        showMessage("Se registro la entrada correctamente");
                    }else{
                        showMessage("La contraseña es incorrecta");
                    }
                }else{
                    showMessage("La persona con la matricula " + matricula + "no existe");
                }
            }else{
                showMessage("Ingresa una contraseña mayor a " + StringExtension.kPASSWORD_LENGTH);
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
        btnRegister.setDisable(!(matricula.length() >= StringExtension.kPASSWORD_LENGTH && password.length() >= StringExtension.kPASSWORD_LENGTH));
    }
    
    /*
        TIMER
    */
    private void startHourCounter(){
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                Platform.runLater(() -> {
                    Date now = new Date();
                    SimpleDateFormat simpleformat = new SimpleDateFormat("HH:mm:ss");
                    labelTime.setText(simpleformat.format(now));
                });
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, 1000);
    }
    
    
}
