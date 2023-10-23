/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modules.Admin;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;

/**
 *
 * @author Benny
 */
public interface GenericTabPane {
    String getTitle();
    String getViewName();
    
    default Tab getTab(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(getViewName() + ".fxml"));
        return new Tab(getTitle(), fxmlLoader.getRoot());
    }
}