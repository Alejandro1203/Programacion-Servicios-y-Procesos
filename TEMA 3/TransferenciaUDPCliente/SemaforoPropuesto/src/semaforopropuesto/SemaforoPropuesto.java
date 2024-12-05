package semaforopropuesto;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Alejandro
 */
public class SemaforoPropuesto implements Runnable {

    Semaphore semaforo1 = new Semaphore(2);
    Semaphore semaforo2 = new Semaphore(2);
    
    public void metodo1() {
        try {
            System.out.println("Entro método 1");
            Thread.sleep(2000);
            System.out.println("Salgo método 1");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    } 
    
    public void metodo2() {
        try {
            System.out.println("Entro método 2");
            Thread.sleep(2000);
            System.out.println("Salgo método 2");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    } 
    
    @Override
    public void run() {
        try {
            semaforo1.acquire();
            metodo1();
            semaforo1.release();
            semaforo2.acquire();
            metodo2();
            semaforo2.release();
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        SemaforoPropuesto sp = new SemaforoPropuesto();
        
        for (int indice = 0; indice < 6; indice++) {
            new Thread(sp).start();
        }
    }
}