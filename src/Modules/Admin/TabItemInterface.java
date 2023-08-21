/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modules.Admin;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;

/**
 *
 * @author Benny
 */
public interface TabItemInterface {
    String getTitleTab();
    String getViewName();
    
    default Tab getTab(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(getViewName() + ".fxml"));
        return new Tab(getTitleTab(), fxmlLoader.getRoot());
    }
}
