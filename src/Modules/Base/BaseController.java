/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modules.Base;

import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.Platform.exit;
import javafx.fxml.Initializable;
import javax.swing.JOptionPane;

/**
 *
 * @author Benny
 */
public class BaseController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void showAlertMessage(String message){
        // FIXME: This could have problems on MacOS
        JOptionPane.showMessageDialog(null, message);
    }
    
    public void createAlertMessage(String message){
         JOptionPane.showMessageDialog(null, message, "OK", JOptionPane.INFORMATION_MESSAGE);
        exit();
    }
    
}
