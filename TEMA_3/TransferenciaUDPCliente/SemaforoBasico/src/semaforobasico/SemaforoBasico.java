package semaforobasico;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Alejandro
 */
public class SemaforoBasico implements Runnable{

    Semaphore semaforo = new Semaphore(3);
    
    @Override
    public void run() {
        try {
            semaforo.acquire();
            System.out.println("Paso 1");
            Thread.sleep(2000);
            System.out.println("Paso 2");
            Thread.sleep(2000);
            System.out.println("Paso 3");
            Thread.sleep(2000);
            semaforo.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        SemaforoBasico sb = new SemaforoBasico();
        
        for (int indice = 0; indice < 10; indice++) {
            new Thread(sb).start();
        }
    }
}