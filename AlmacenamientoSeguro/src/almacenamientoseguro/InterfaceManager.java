package almacenamientoseguro;

import java.awt.Color;
import java.awt.Label;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro
 */
public class InterfaceManager {
    
    public static void setLabel(Label label, Color color, String text) {
        label.setForeground(color);
        label.setText(text);
    }
    
    public static String readFile(String path) {
        String content  = "";
        
        try {
            BufferedReader bf = new BufferedReader(new FileReader(path));
            String linea;
            
            while((linea = bf.readLine()) != null) {
                content += linea + "\n";
            }
            
            bf.close();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return content.trim();
    }
    
    public static void createFile(String nameFile, String contentFile) {
        try {
            File file = new File(nameFile);
            
            file.createNewFile();
            
            FileWriter fileWriter = new FileWriter(file);
            
            fileWriter.write(contentFile);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void deleteFile(String path) {
        File file = new File(path);
        
        file.delete();
    }
    
}
