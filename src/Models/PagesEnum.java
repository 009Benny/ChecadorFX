/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Modules.Admin.Configuration.AdminConfigurationController;

/**
 *
 * @author Benny
 */
public enum PagesEnum {
    configuration("Configuracion", "AdminConfiguration.fxml"),
    usuarios("Usuarios", "AdminUsuarios.fxml"),
    personas("Personas", "AdminPersonas.fxml"),
    niveles("Niveles", "AdminNiveles.fxml"),
    carreras("Carreras", "Carreras.fxml"),
    facultades("Facultades", "Facultades.fxml"),
    servicios("Servicios", "Servicios.fxml");
    
    private String title;
    private String fmxlName;
    
    private PagesEnum(String title, String fxmlName){
        this.title = title;
        this.fmxlName = fxmlName;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public String getFXMLName(){
       return this.fmxlName;
    }
    
    public Class<?> getController(){
        switch(this){
            case configuration:
                return AdminConfigurationController.class;
        }
        return null;
    }
}