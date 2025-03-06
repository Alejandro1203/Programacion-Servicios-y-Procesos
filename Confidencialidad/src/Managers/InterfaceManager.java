package Managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Alejandro
 */
public class InterfaceManager {
    
    public static void generateMessagePopUp(JFrame frame, String mensaje) {
        JOptionPane.showMessageDialog(frame, mensaje);
    }
    
    public static void generateErrorPopUp(JFrame frame, String mensaje) {
        JOptionPane.showMessageDialog(frame, mensaje, "¡Error!", JOptionPane.ERROR_MESSAGE);
    }
    
    public static String fileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        
        int val = fileChooser.showOpenDialog(null);
        
        if(val == JFileChooser.OPEN_DIALOG) {
            String pathAbsolut = fileChooser.getSelectedFile().getAbsolutePath();
            
            return pathAbsolut;
        } else {
            return "";
        }
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
    
    public static byte[] readBytesFile(String nameFile) {
        byte[] content  = new byte[256];
        
        try {
            
            content = Files.readAllBytes(new File(nameFile).toPath());
        } catch (IOException ex) {
            Logger.getLogger(InterfaceManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return content;
    }
    
    public static void createFile(String nameFile) {
        try {
            File file = new File(nameFile);
            
            file.createNewFile();
            
        } catch (IOException ex) {
            Logger.getLogger(InterfaceManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void writeFile(String nameFile, byte[] mensaje) {
        try {
            Files.write(new File(nameFile).toPath(), mensaje);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
