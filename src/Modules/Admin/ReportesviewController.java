/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modules.Admin;

import DataBase.DataBaseManager;
import DataBase.Models.RegistrosClass;
import Extensions.DateExtension;
import Models.FileManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javax.swing.JOptionPane;

/**
 *
 * @author bennyreyes
 */
public class ReportesviewController implements AdminGenericController {

    DataBaseManager db = new DataBaseManager();
    DatePicker datePickerReport;
    Button btnDownloadReport;
    
    public ReportesviewController(DatePicker date, Button downloadReport){
        this.datePickerReport = date;
        this.btnDownloadReport = downloadReport;
        //configViews();
        configListeners();
    }
    
    private void configListeners(){
        datePickerReport.setOnAction(new EventHandler(){
            @Override
            public void handle(Event event) {
                downloadReportFromDate(DateExtension.getStringFromLocalDate(datePickerReport.getValue(), "dd/MM/YYYY"));
            }
        });
    }
    
    private void downloadReportFromDate(String date){
        List<RegistrosClass> registros = new ArrayList<RegistrosClass>();
        List<HashMap<String, Object>> data = db.getDataWithQuery(RegistrosClass.getQueryAllItemsWithDay(date));
        
        if (!data.isEmpty()){
            registros.clear();
            for(HashMap<String, Object> map:data){
                registros.add(new RegistrosClass(map));
            }
            System.out.println("ReportesView || Se agregan " + registros.size() + " rows");
            createCSV(registros, date);
        }else{
            System.out.println("ReportesView || No tiene elementos en la base de datos");
        }
    }
    
    private void createCSV(List<RegistrosClass> registros, String date){
        List<String> list = new ArrayList<String>();
        for(RegistrosClass item: registros){
            list.add(item.getCSVLine());
        }
        try{
            FileManager.createCSVWithData(RegistrosClass.getHeaderToCSV(), list, "Registros-" + date.replace("/", "-"));
        } catch(IOException e){
            System.out.println(e);
        }
    }
    
    // ADMIN GENERIC CONTROLLER
    @Override
    public void notify(boolean willAppear) {
        
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
    
}
