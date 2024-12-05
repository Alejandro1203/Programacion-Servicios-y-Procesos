package concurrenciaparte1;

/**
 *
 * @author Alejandro
 */
public class Contador extends Thread {
    private static int cont = 0;
    
    public synchronized void cuenta() {
        
        for (int indice = 1; indice <= 5000; indice++) {
            System.out.println(cont);
            cont++;
        }
    }

    @Override
    public void run() {
        cuenta();
    }
}