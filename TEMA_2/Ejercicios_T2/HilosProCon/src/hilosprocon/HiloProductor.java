package hilosprocon;

/**
 *
 * @author Alejandro
 */
public class HiloProductor extends Thread {
    Valores v;

    public HiloProductor(Valores v) {
        this.v = v;
    }
    
    @Override
    public void run() {
        
        while(!v.isFinalizado()) {
            int valor = (int) (Math.random() * 100 + 1);
            v.producir(valor);
            System.out.println("Hilo productor ha producido: " + valor);
        }
    }
}