package concurrenciaparte2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro
 */
public class FicheroContador extends Thread {
    File file;

    public FicheroContador(File file) {
        this.file = file;
    }
    
    public synchronized int contarCaracteres() {
        int caracteres = 0;
        
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            
            String cadena;
            
            while((cadena = bufferedReader.readLine()) != null) {
                String[] cadenaSplit = cadena.split("[,\\.\\s]");
                
                for (int indice = 0; indice < cadenaSplit.length; indice++) {
                    caracteres += cadenaSplit[indice].length();
                }
            }            
            
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException ex) {
            Logger.getLogger(FicheroContador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return caracteres;  
    }
    
    
    public synchronized int contarPalabras() {
        int palabras = 0;
        
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            
            String cadena;
            
            while((cadena = bufferedReader.readLine()) != null) {
                String[] cadenaSplit = cadena.split("[,\\.\\s]");
                palabras += cadenaSplit.length;
            }            
            
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException ex) {
            Logger.getLogger(FicheroContador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return palabras;
    }
    
    public synchronized int contarLineas() {
        int lineas = 0;
        
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            
            String cadena;
            
            while((cadena = bufferedReader.readLine()) != null) {
                lineas++;
            }            
            
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException ex) {
            Logger.getLogger(FicheroContador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lineas;
    }

    @Override
    public void run() {
        System.out.println("Caracteres: " + contarCaracteres());
        System.out.println("Lineas: " + contarLineas());
        System.out.println("Palabras: " + contarPalabras());
    }
}
