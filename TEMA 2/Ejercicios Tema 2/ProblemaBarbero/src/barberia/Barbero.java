package barberia;

/**
 *
 * @author Alejandro
 */
public class Barbero extends Thread{
    private int id;
    private ColaClientes cola;
    private long tiempoInicial;

    public int getIdBarbero() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Barbero(int id, ColaClientes cola, long tiempoInicial) {
        this.id = id;
        this.cola = cola;
        this.tiempoInicial = tiempoInicial;
    }

    @Override
    public void run() {
        
        while(true) {
            if (!cola.getListaCliente().isEmpty()) {

                try {
                    int tiempoAtencion = (int) (Math.random() * 3 + 3);
                    Thread.sleep(tiempoAtencion * 1000);

                    Cliente cliente = cola.getListaCliente().getFirst();
                    System.out.println("El barbero " + id + " está atendiendo a " + cliente.toString());

                    cola.eliminarCliente();
                    System.out.println("El barbero " + id + " terminó de atender a " + cliente.toString() + "\nTiempo tardado: " + tiempoAtencion + " segundos.");
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
    }
}