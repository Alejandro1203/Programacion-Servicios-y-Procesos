package filosofosemaforo;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Alejandro
 */
public class Mesa {
    private Semaphore[] semaforos; 
//    private Semaphore semaforo2 = new Semaphore(2);
    
     
    public Mesa(int numTenedores){
        semaforos = new Semaphore[numTenedores];
        
        for (int indice = 0; indice < semaforos.length; indice++) {
            semaforos[indice] = new Semaphore(1);
        }
    }
     
    public int tenedorIzquierda(int comensal){
        return comensal - 1;
    }
     
    public int tenedorDerecha(int comensal){
        
        if(comensal == semaforos.length) {
            return 0;
        } else {
            return comensal;
        }         
    }
     
    public void cogerTenedores(int comensal) { 
//        this.semaforo2.tryAcquire();
        
        try {
            this.semaforos[tenedorIzquierda(comensal)].acquire();
            this.semaforos[tenedorDerecha(comensal)].acquire();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void dejarTenedores (int comensal){

        this.semaforos[tenedorIzquierda(comensal)].release();
        this.semaforos[tenedorDerecha(comensal)].release();
//        this.semaforo2.release();
    }
}