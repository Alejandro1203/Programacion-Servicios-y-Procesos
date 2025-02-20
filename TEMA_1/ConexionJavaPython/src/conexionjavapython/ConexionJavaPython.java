package conexionjavapython;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Alejandro
 */
public class ConexionJavaPython {

    
    public static void main(String[] args) {
        
        
        try {
            Process proceso = new ProcessBuilder(
                    "py",
                    "C:\\Users\\ALUMNO2425\\Desktop\\proceso_Python.py"
                    ).start();
            
//            Process proceso = new ProcessBuilder(
//                    "py",
//                    "C:\\Users\\USUARIO\\Desktop\\proceso_Python.py"
//                    ).start();         
            
            BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            proceso.waitFor();
            int exitStatus = proceso.exitValue();
            System.out.println("Retorno: " + br.readLine());
            System.out.println("Valor de la salida: " + exitStatus);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();            
        }
    }
}