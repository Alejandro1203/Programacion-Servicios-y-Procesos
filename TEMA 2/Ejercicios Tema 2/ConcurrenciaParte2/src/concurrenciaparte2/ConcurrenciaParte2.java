package concurrenciaparte2;

import java.io.File;
import java.util.Arrays;

/**
 *
 * @author Alejandro
 */
public class ConcurrenciaParte2 {

    
    public static void main(String[] args) {
        
        for (int i = 0; i < args.length; i++) {
            File file = new File(args[i]);
            FicheroContador fichero = new FicheroContador(new File(args[i]));
            fichero.start();
        }
        
    }
    
}
