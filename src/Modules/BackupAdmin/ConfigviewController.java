/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modules.BackupAdmin;

import Models.PhotoManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author Benny
 */
public class ConfigviewController implements AdminGenericController  {
    PhotoManager photoManager;
    TextField txtFieldPhotosUrl;
    Button btnSavePhotoUrl;
    
    private void loadData(){
        txtFieldPhotosUrl.setText(photoManager.getUrl());
    }
    
    public ConfigviewController(TextField photosURL, Button btnSavePhotoUrl){
        this.txtFieldPhotosUrl = photosURL;
        this.btnSavePhotoUrl = btnSavePhotoUrl;
        this.photoManager = PhotoManager.getInstance();
        configListeners();
    }
    
    private void configListeners(){
        btnSavePhotoUrl.setOnAction(new EventHandler(){
            @Override
            public void handle(Event event) {
                photoManager.setURL(txtFieldPhotosUrl.getText());
            }
        });
//         btnAdd.setOnAction(new EventHandler(){
//            @Override
//            public void handle(Event event) {
//                addElements();
//            }
//        });
//        btnDownloadFormat.setOnAction(new EventHandler(){
//            @Override
//            public void handle(Event event) {
//                try {
//                    FileManager.downloadFormatAction("PersonasFormat.csv");
//                } catch (IOException ex) {
//                    Logger.getLogger(PersonasviewController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
    }
    
    @Override
    public void notify(boolean willAppear) {
        if (willAppear){
            loadData();
        }
    }
    
}
