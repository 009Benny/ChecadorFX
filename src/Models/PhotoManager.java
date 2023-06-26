/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.io.File;
import javafx.scene.image.Image;

/**
 *
 * @author Benny
 */
public final class PhotoManager {
    private static PhotoManager instance;
    private String fileName;

    private PhotoManager(){
        fileName = "PhotoUrl.txt";
    }

    public static PhotoManager getInstance() {
        if (instance == null) {
            instance = new PhotoManager();
        }
        return instance;
    }
    
    public String getUrl(){
        return FileManager.getTextInDataFolder(fileName);
    }
    
    public void setURL(String newUrl){
        FileManager manager = new FileManager();
        manager.overwriteTextInDataFolder(newUrl, fileName);
    }
    
    public Image getImageIfExistFromMatricula(String matricula){
        String path = getUrl() + File.separator + matricula + ".png";
        System.out.println(path);
        if (FileManager.checkIfExistImage(path)){
            System.out.println("SI EXISTE");
            File f = new File(path);
            Image img = new Image(f.toURI().toString());
            System.out.println("SI LA CARGO");
            return img;
        }
        File f = new File("./src/Images/user.png");
        return new Image(f.toURI().toString());
    }
    
}
