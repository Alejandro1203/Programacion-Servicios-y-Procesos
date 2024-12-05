package gestorhojas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 * @author Alejandro
 */
public class GestorHojas extends Thread {
    
    private static CopyOnWriteArrayList<String> lista = new CopyOnWriteArrayList<>();
    
    //private static List<String> lista = new ArrayList<>();

    @Override
    public void run() {
        
        while (true) {
            if (lista.size() >= 10) {
                lista.remove(0);
            } else if (lista.size() < 10) {
                lista.add("Texto");
            }
            
            for (String string : lista) {
                System.out.println(string + " ");
            }
        }
    }
    
    public static void main(String[] args) {
        
        for (int indice = 0; indice < 10; indice++) {
            lista.add("Texto");
        }
        
        for (int indice = 0; indice < 100; indice++) {
            new GestorHojas().start();
        }
    }
}