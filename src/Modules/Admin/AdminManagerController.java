/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Modules.Admin;

import Models.PagesEnum;
import Modules.Admin.Configuration.AdminConfigurationController;
import Modules.Admin.Personas.AdminPersonasController;
import checadorentrada.ChecadorEntrada;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * FXML Controller class
 *
 * @author Benny
 */
public class AdminManagerController implements Initializable {
    public static String VIEW = "AdminManager.fxml";
    private ArrayList<PagesEnum> pages;

    @FXML
    private TabPane mainContainer;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       pages = new ArrayList<PagesEnum>();
       
       // TODO REMOVE
       pages.add(PagesEnum.configuration);
       pages.add(PagesEnum.personas);
       loadPages();
    }
    
    private void loadPages(){
        clearPane();
        for (PagesEnum page: pages){
            mainContainer.getTabs().add(this.getTabFromFXML(page));
        }
    }
    
    private Tab getTabFromFXML(PagesEnum page){
        Tab tabPage = new Tab();
        tabPage.setText(page.getTitle());
        try {
            // TODO: Optimize
//            FXMLLoader fxmlLoader = new FXMLLoader(page.getClass().getResource(page.getFXMLName()));
            FXMLLoader fxmlLoader = null;
            switch(page){
                case configuration:
                    fxmlLoader = new FXMLLoader(AdminConfigurationController.class.getResource("AdminConfiguration.fxml"));
                    break;
                case personas:
                    fxmlLoader = new FXMLLoader(AdminPersonasController.class.getResource("AdminPersonas.fxml"));
                    break;
            }
            tabPage.setContent(fxmlLoader.load());
        } catch(Exception e) {
            System.out.println("AdminManagerController || " + e.getMessage());
        }
        return tabPage;
    }
    
    private void clearPane(){
        this.mainContainer.getTabs().removeAll();
    }
    
    @FXML
    private void didTapBackButton() throws IOException {
       ChecadorEntrada.showMenu();
    } 
    
    private void laodConnfigurationView(){
        AdminConfigurationController configuration = new AdminConfigurationController();
        Tab configTab = configuration.getTab();
        mainContainer.getTabs().add(configTab);
    }
    
    public void setPages(ArrayList<PagesEnum> pages){
        this.pages = pages;
        this.loadPages();
    }
}
