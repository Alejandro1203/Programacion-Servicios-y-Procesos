package hilosprocon;

/**
 *
 * @author Alejandro
 */
public class HilosProCon {

    
    public static void main(String[] args) {
        Valores v = new Valores();
        HiloProductor productor1 =  new HiloProductor(v);
        HiloProductor productor2 =  new HiloProductor(v);
        HiloConsumidor consumidor = new HiloConsumidor(v);
        
        productor1.start();
        productor2.start();
        consumidor.start();
    }
}