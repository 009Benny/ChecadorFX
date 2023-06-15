/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.io.File;

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
}
