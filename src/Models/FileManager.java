/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author bennyreyes
 */
public class FileManager {
    
    public static ArrayList getData(String filename) throws FileNotFoundException, IOException{
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(filename)))) {
        String line;
        while ((line = reader.readLine()) != null)
            list.add(line);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
}
