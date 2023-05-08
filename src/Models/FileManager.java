/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import DataBase.Models.PersonasClass;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author bennyreyes
 */
public class FileManager {
    
    /*
    * Methods to get instances
    */
    public static Map<String, List<PersonasClass>> getPersonasFromFile(){
        Map<String, List<PersonasClass>> map =  new HashMap<String, List<PersonasClass>>();
        List<PersonasClass> success = new ArrayList<PersonasClass>();
        List<PersonasClass> failure = new ArrayList<PersonasClass>();
        
        File file = FileManager.getFile();
        List<String> lines = FileManager.getListFromCSV(file.getAbsolutePath());
   
        if (lines.get(0).startsWith("No.")){
            // EXAMPLE OF UANL
            lines.remove(0);
            lines.remove(0);  // SE REMUEVEN DOS LINEAS
            int[] indexList = {1, 3, 8, 10, 12, 14};
            // MATRICULA, NOMBRE COMPLETO, SEMESTRE, FECHA DE NACIMIENTO, CARRERA, DEPORTE
            for(String line: lines){
            //for(String line:getListFromCSV("/Users/bennyreyes/Downloads/PersonasFormat.csv")){
                System.out.println(line);
//                PersonasClass persona = new PersonasClass(line, indexList);
//                if (persona.isValid()){
//                    success.add(persona);
//                }else{
//                    failure.add(persona);
//                }
            }
            
        }else {
            //DEFAULT FORMAT
            for(String line: lines){
            //for(String line:getListFromCSV("/Users/bennyreyes/Downloads/PersonasFormat.csv"))
                if(!line.startsWith("ID,NOMBRE")){
                    System.out.println(line);
                    PersonasClass persona = new PersonasClass(line);
                    if (persona.getIsValid()){
                        success.add(persona);
                    }else{
                        failure.add(persona);
                    }
                }
            }
        }
        map.put("success", success);
        map.put("failure", failure);
        return map;
    }
    
    static private int[] getIndexFromPersonas(String header){
        String[] split = header.split(",");
        int[] indexList = new int[split.length];
        
        return indexList;
    }
    
    
    
    
    /*
    * Download file demo
    */
    public static void downloadFormatAction(String name) throws IOException{
        String home = System.getProperty("user.home");
        String project = System.getProperty("user.dir");
        FileManager.copyFile(new File(project + "/src/Data/" + name), new File(home + "/Downloads/name"));
    }
    
    private static void copyFile(File sourceFile, File destFile) throws IOException {
        if (!sourceFile.exists()) {
            System.out.println("src: " + sourceFile.getAbsolutePath());
            JOptionPane.showMessageDialog(null, "Hubo un error, no se enconro el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!destFile.exists()) {
            destFile.createNewFile();
        }else{
            JOptionPane.showMessageDialog(null, "El archivo " + destFile +" ya existe.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        FileChannel source = null;
        FileChannel destination = null;
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        if (destination != null && source != null) {
            destination.transferFrom(source, 0, source.size());
        }
        if (source != null) {
            source.close();
        }
        if (destination != null) {
            destination.close();
        }
        JOptionPane.showMessageDialog(null, "Se descargó correctamente en su carpeta de descargas.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    /*
    * GET DATA FROM CSV File
    */
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
    
    private static File getFile(){
        JFileChooser fc = new JFileChooser();
        File file = null;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Solo soporto csv", "csv");
        fc.setFileFilter(filter);
        int selected = fc.showOpenDialog(null);
        if (selected == JFileChooser.APPROVE_OPTION){
            file = fc.getSelectedFile();
        }
        return file;
    }
    
    private static List<String> getListFromCSV(String path){
        List<String> lines = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return lines;
    }
}
