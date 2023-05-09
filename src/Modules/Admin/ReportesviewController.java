/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modules.Admin;

import DataBase.DataBaseManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

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
