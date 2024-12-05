package filosofosemaforo;


/**
 *
 * @author Alejandro
 */
public class Filosofo extends Thread{
    private Mesa mesa;
    private int comensal;
 
    public Filosofo(Mesa m, int comensal) {
        this.mesa = m;
        this.comensal = comensal;
    }
    
    public void run() {
        while (true) {
            pensando();
            comiendo();
        }
    }

    public void pensando() {
        System.out.println("Filósofo " + comensal + " pensando.");

        try {
            Thread.sleep((int) (Math.random() * ((5000 - 1000) + 1) + 1000));
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void comiendo() {
        try {
            mesa.cogerTenedores(comensal);
            System.err.println("Filósofo " + comensal + " comiendo.");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("Filósofo " + comensal + " ha dejado de comer.");
        mesa.dejarTenedores(comensal);
        
    }
}