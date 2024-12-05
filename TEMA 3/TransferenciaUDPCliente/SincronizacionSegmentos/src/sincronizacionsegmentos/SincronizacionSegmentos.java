package sincronizacionsegmentos;

/**
 *
 * @author Alejandro
 */
public class SincronizacionSegmentos extends Thread{
    int id;
    static Object bloqueo1 = new Object();
    static Object bloqueo2 = new Object();
    
    public SincronizacionSegmentos(int id) {
        this.id = id;
    }

    public void metodo1() {
        synchronized (bloqueo1) {
            System.out.println("Comienzo del método 1 del hilo " + id);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                return;
            }

            System.out.println("Fin del método 1 del hilo " + id);
        }
    }
    
    public synchronized void metodo2() {
        synchronized (bloqueo1) {
            System.out.println("Comienzo del método 2 del hilo " + id);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                return;
            }

            System.out.println("Fin del método 2 del hilo " + id);
        }
    }

    @Override
    public void run() {
        
        if(id == 1) {
            metodo1();
            metodo2();
        } else {
            metodo2();
            metodo1();
        }
    }
    public static void main(String[] args) {
//        SincronizacionSegmentos ss = new SincronizacionSegmentos(1);
//        new Thread(ss).start();
//        new Thread(ss).start();
        new SincronizacionSegmentos(1).start();
        new SincronizacionSegmentos(2).start();
    }
    
}
