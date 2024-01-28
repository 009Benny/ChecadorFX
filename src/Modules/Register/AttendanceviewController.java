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
import Managers.DataBaseManager;
import DataBase.Models.HorariosClass;
import Models.PhotoManager;
import checadorentrada.ChecadorEntrada;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author bennyreyes
 */
public class AttendanceviewController implements Initializable {
    ObservableList<RegistrosClass> registros = FXCollections.observableArrayList();
    DataBaseManager db = new DataBaseManager();
    PhotoManager photoManager = PhotoManager.getInstance();
    @FXML
    private TableView<RegistrosClass> tableContent;
    @FXML
    private Label labelTime;
    @FXML
    private TextField textFieldMatricula;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnDeny;
    @FXML
    private Button btnClear;
    @FXML
    private ImageView imgPerson;
    
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
        btnRegister.setDisable(true);
        btnDeny.setDisable(true);
        
        textFieldMatricula.setOnKeyReleased(new EventHandler(){
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
        String today = DateExtension.getStringDate(new Date(), "dd/MM/YYYY");
        List<HashMap<String, Object>> data = db.getDataWithQuery(RegistrosClass.getQueryAllItemsWithDay(today));
        // Adding day validation
        
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
    private void registerUser(String matricula){
        RegistrosTable registros = new RegistrosTable();
        if (StringExtension.onlyDigits(matricula) && matricula.length() > 5){
            PersonasClass persona = PersonasClass.getPersonaBy(matricula);
            if (persona != null){
                // TRAER HORARIO
                HorariosClass horario = HorariosClass.getHorarioById(persona.getIdHorario());
                if (horario != null){
                    Date now = new Date();
                    int count = db.getCountOf(registros.getTableName());
                    RegistrosClass last =  RegistrosClass.getLastRegistroBy(matricula);
                    boolean status = (last != null) ? !last.isSalida() : false;
                    if (status || horario.isOnValidTime(now)){
                        // CREAR REGISTRO
                        String today = DateExtension.getStringDate(now, "dd/MM/YYYY HH:mm:ss");
                        RegistrosClass registro = new RegistrosClass(count + 1, today, status, matricula);
                        db.createItem(registros.getTableName(), registro.getInsertHeader() , registro.getValuesQuery());
                        loadData();
                        String mensaje = !status ? "entrada": "salida";
                        showMessage("Se registro la "+mensaje+" correctamente");
                    }else {
                        // TODO: ALERT OF HOW MANY TIME
                        showMessage("La hora no corresponde a su horario");
                    }
                }else {
                    showMessage("No se encontro un horario valido para el estudiante");
                }
            }else{
                showMessage("La persona con la matricula " + matricula + "no existe");
            }
        }else{
            showMessage("Ingresa una matricula valida");
        }
        textFieldMatricula.setText("");
        imgPerson.setImage( photoManager.getImageIfExistFromMatricula(""));
    }
    
    /*
    * Gestures
    */
    @FXML
    private void didTapRegisterButton() throws IOException {
       registerUser(textFieldMatricula.getText());
    }
    
    @FXML
    private void didTapDenyButton() throws IOException {
       // TODO: ADD LOGIC TO MARK PENALTY
    }
    
    @FXML
    private void didTapClearButton() throws IOException {
       textFieldMatricula.setText("");
    }
    
    @FXML
    private void didTapBackButton() throws IOException {
       ChecadorEntrada.showMenu();
    }    
    
    // This method will enable/disable the button to make a register
    private void didTextFieldChange(){
        String matricula = textFieldMatricula.getText();
        Boolean valid =(matricula.length() > 0);
        btnRegister.setDisable(!valid);
        btnDeny.setDisable(!valid);
        if (matricula.length() >= StringExtension.kPASSWORD_LENGTH) {
            imgPerson.setImage( photoManager.getImageIfExistFromMatricula(matricula));
        }
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
