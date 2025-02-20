package es.paraninfo.sincronizacion;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Alejandro
 */

public class ProcesoPrincipal {

    public static void main(String[] args) {
        
        try {
//          String[] infoProceso = {"java", "sincronizacionprocesos.ProcesoSecundario"};
//          Process proceso = Runtime.getRuntime().exec(infoProceso);

            File ruta = new File(".\\build\\classes");
            ProcessBuilder builder = new ProcessBuilder("java", "es.paraninfo.sincronizacion.ProcesoSecundario");
            builder.directory(ruta);
            System.out.println(builder.directory());
            builder.redirectErrorStream(true);
            builder.inheritIO();
            Process proceso = builder.start();

            int valorRetorno = proceso.waitFor();
           
            if (valorRetorno == 0) {
                System.out.println("El proceso se ha completado satisfactoriamente");
            } else {
               System.out.println("El proceso ha fallado. Codigo de error: " + valorRetorno);
            }
            
        } catch (IOException | InterruptedException e) {  
            e.printStackTrace();
        }
    }
}