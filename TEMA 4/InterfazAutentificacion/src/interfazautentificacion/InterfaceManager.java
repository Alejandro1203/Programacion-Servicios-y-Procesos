package interfazautentificacion;

import java.awt.Color;
import java.awt.Label;
import java.io.File;

/**
 *
 * @author Alejandro
 */
public class InterfaceManager {
    
    public static boolean ficheroExiste(String ruta) {
        boolean existe = false;
        File archivo = new File(ruta);
        
        if(archivo.exists()) {
            existe = true;
        }
        
        return existe;
    }
    
    public static void setLabel(Label label, Color color, String texto) {
        label.setForeground(color);
        label.setText(texto);
    }
}