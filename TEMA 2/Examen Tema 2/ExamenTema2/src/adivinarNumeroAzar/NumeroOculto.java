
package adivinarNumeroAzar;

/**
 *
 * @author Alejandro
 */
public class NumeroOculto extends Thread{
    private static boolean numeroAdivinado = false;
    private int numeroAzar;
    private int id;

    public NumeroOculto(int numeroAzar, int id) {
        this.numeroAzar = numeroAzar;
        this.id = id;
    }
    
    private synchronized int propuestaNumero() {   
        int num = (int) (Math.random() * 100);
        
        System.out.println(num);
        
        if(numeroAdivinado) {
            return -1;
        } else if (num == numeroAzar) {
            numeroAdivinado = true;
            return 1;
        } else {
            return 0;
        }
    }
    
    @Override
    public void run() {
        
        while (!numeroAdivinado) {
            int propuesta = propuestaNumero();
            
            if (propuesta == -1) {
                System.out.println("Hilo " + id + " paró, número descubierto: " + numeroAzar);
                return;
            }
        }
    }
}