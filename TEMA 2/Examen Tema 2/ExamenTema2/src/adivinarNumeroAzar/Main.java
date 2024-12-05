package adivinarNumeroAzar;

/**
 *
 * @author Alejandro
 */
public class Main {
    
    static int numAzar = (int) (Math.random() * 100);

    public static void main(String[] args) {
        for (int indice = 0; indice < 10; indice++) {
            Thread hilo = new NumeroOculto(numAzar, indice);
            hilo.start();
        }
    }
    
}
