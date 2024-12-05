package barberia;

/**
 *
 * @author Alejandro
 */
public class InsertarClientes extends Thread{
    private ColaClientes cola;
    private long tiempoInicial;

    public ColaClientes getCola() {
        return cola;
    }

    public void setCola(ColaClientes cola) {
        this.cola = cola;
    }

    public long getTiempoInicial() {
        return tiempoInicial;
    }

    public void setTiempoInicial(long tiempoInicial) {
        this.tiempoInicial = tiempoInicial;
    }

    public InsertarClientes(ColaClientes cola, long tiempoInicial) {
        this.cola = cola;
        this.tiempoInicial = tiempoInicial;
    }

    @Override
    public void run() {
        int id = 1;
        
        while(true) {
            
            try {
                int tiempoLlegada = (int) (Math.random() * 2 + 1);
                Thread.sleep(tiempoLlegada * 1000);
                
                Cliente cliente = new Cliente(id);
                id++;
                
                System.out.println("Llegó " + cliente.toString() + " a la cola " + cola.getId());
                cola.insertarCliente(cliente);
                
            } catch(InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}