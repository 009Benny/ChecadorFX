/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author Benny
 */
public class FileManager {
    
    /**
     * Reads lines from a text file and returns them as an ArrayList of strings.
     *
     * @param filePath The path to the text file to read.
     * @return An ArrayList containing the lines read from the file.
     * @throws FileManagerException If the file does not exist or an error occurs while reading.
     */
    public static ArrayList<String> readLinesFromFile(String filePath) throws FileManagerException {
        ArrayList<String> lines = new ArrayList<>();

        // Check if the file exists
        if (!fileExists(filePath)) {
            throw new FileManagerException("No existe el archivo: " + filePath);
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new FileManagerException("Error al leer el archivo: " + filePath);
        }
        return lines;
    }
    
    /**
     * Writes the contents of an ArrayList of strings to a file. If the file already exists,
     * behavior depends on the 'override' parameter.
     *
     * @param arrayList The ArrayList containing strings to be written to the file.
     * @param filePath The path of the file where the contents will be written.
     * @param override If true, the existing file will be deleted and overwritten. If false,
     *                 an exception will be thrown if the file already exists.
     * @throws FileManagerException If there is an error during file operations.
     */
    public static void writeArrayListToFile(ArrayList<String> arrayList, String filePath, boolean override) throws FileManagerException {
        // Elimina el archivo si ya existe
        if (fileExists(filePath)) {
            if (override){
                try {
                    Files.delete(Paths.get(filePath));
                } catch (IOException ex) {
                    throw new FileManagerException("Error al eliminar el archivo: " + filePath);
                }
            }else{
                throw new FileManagerException("El archivo ya existe: " + filePath);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String element : arrayList) {
                writer.write(element);
                writer.newLine(); // Write each element on a new line
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            throw new FileManagerException("Error al escribir el archivo: " + filePath);
        }
    }
    
    /**
     * Checks if a file exists at the given path.
     *
     * @param filePath The path to the file to check.
     * @return True if the file exists, false otherwise.
     */
    private static boolean fileExists(String filePath) {
       return new java.io.File(filePath).exists();
    }
    
}
