/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package checadorfx;

import Modules.MainMenu.MainmenuController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Benny
 */
public class ChecadorFX extends Application {
    private static Scene scene;
    
    @Override
    public void start(Stage primaryStage) {
        try{
            scene = new Scene(loadFXML("mainmenu"));
            primaryStage.setTitle("Checador de entrada y salida");
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch(IOException e){
            System.out.print("No se pudo cargar!");
            System.out.println(e);
        }
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainmenuController.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
