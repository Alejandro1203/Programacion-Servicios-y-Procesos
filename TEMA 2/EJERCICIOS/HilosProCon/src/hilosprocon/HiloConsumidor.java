package hilosprocon;

/**
 *
 * @author Alejandro
 */
public class HiloConsumidor extends Thread {
    Valores v;
    private static int suma = 0;
    private final int sumaMax = 1000;

    public HiloConsumidor(Valores v) {
        this.v = v;
    }

    @Override
    public void run() {
        
        while(!v.isFinalizado()) {
            int valor = v.consumir(); 
            suma += valor;
            System.out.println("Hilo Consumidor ha consumido: " + valor + ". \tSuma: " + suma);
            
            if(suma >= sumaMax) {
                v.setFinalizado(true);
            }
        }         
    } 
}