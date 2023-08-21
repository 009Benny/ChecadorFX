/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Managers;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javax.swing.JOptionPane;

/**
 *
 * @author Benny
 */
public class AlertManager {
    
    /**
    * Muestra una alerta genérica con el título, mensaje y tipo de alerta especificados.
    *
    * @param title El título de la alerta.
    * @param message El mensaje de la alerta.
    * @param alertType El tipo de alerta (INFORMATION, WARNING, ERROR, CONFIRMATION).
    */
    public static void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null); // No header text
        alert.setContentText(message);

        alert.showAndWait();
    }
    
    
    public static void showSwingAlert(String title, String message, int alertType){
        JOptionPane.showMessageDialog(null, message, title, alertType);
    }
    
    
}
