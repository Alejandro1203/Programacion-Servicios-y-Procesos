package barberia;

import java.util.ArrayList;

/**
 *
 * @author Alejandro
 */
public class ColaClientes {
    private ArrayList<Cliente> listaCliente;
    private int id;
    private int numeroSillas;

    public synchronized int getId() {
        return id;
    }

    public synchronized void setId(int id) {
        this.id = id;
    }

    public synchronized int getNumeroSillas() {
        return numeroSillas;
    }

    public synchronized void setNumeroSillas(int numeroSillas) {
        this.numeroSillas = numeroSillas;
    }

    public synchronized ArrayList<Cliente> getListaCliente() {
        return listaCliente;
    }

    public synchronized void setListaCliente(ArrayList<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public ColaClientes(int id, int numeroSillas) {
        this.id = id;
        this.numeroSillas = numeroSillas;
        this.listaCliente = new ArrayList<>();
    }
    
    public synchronized void insertarCliente(Cliente cliente) {
        if(numeroSillas != listaCliente.size()) {
            listaCliente.add(cliente);
            this.notify();
        } else {
            System.out.println(cliente.toString() + " se fue porque no habia sillas.");
        }
    }
    
    public synchronized void eliminarCliente() {
        
        while(listaCliente.isEmpty()) {
            
            try {
                this.wait();
            }catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        
        if(!listaCliente.isEmpty()) {
            listaCliente.removeFirst();
        }
    }
}