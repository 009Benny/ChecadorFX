/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modules.Admin;

import Models.DataBaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author bennyreyes
 */
public class ReportesviewController implements AdminGenericController {

    DataBaseManager db = new DataBaseManager();
    DatePicker datePickerReport;
    Button btnDownloadReport;
    Button btnDownloadFacultades;
    Button btnDownloadCarreras;
    
    public ReportesviewController(DatePicker date, Button downloadReport, Button downloadFacultades, Button downloadCarreras){
        this.datePickerReport = date;
        this.btnDownloadReport = downloadReport;
        this.btnDownloadFacultades = downloadFacultades;
        this.btnDownloadCarreras = downloadCarreras;
        //configViews();
        //configListeners();
    }
    
    // ADMIN GENERIC CONTROLLER
    @Override
    public void notify(boolean willAppear) {
        
    }
    
}
