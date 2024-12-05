
package hilosprocon;

/**
 *
 * @author Alejandro
 */
public class Valores {
    private final int tamanyo = 10;
    private int[] valores;
    private static int contValores = 0;
    private boolean finalizado;

    public boolean isFinalizado() {
        return finalizado;
    }    

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public Valores() {
        valores = new int[tamanyo];
        finalizado = false;
    }
    
    public boolean estaLleno() {
        boolean lleno = false;
        
        if(contValores == tamanyo) {
            lleno = true;
        }
        
        return lleno;
    }
    
    public boolean estaVacio() {
        boolean vacio = false;
        
        if(contValores == 0) {
            vacio = true;
        }
        
        return vacio;
    }
    
    public synchronized boolean producir(int valor) {
        boolean producido = false;
        
        if(!estaLleno()) {
            valores[contValores] = valor;
            contValores++;
            producido = true;
            notifyAll();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        
        return producido;
    }
    
    public synchronized int consumir() {
        int valor = 0;
        
        if(!estaVacio() || finalizado) {
            valor = valores[contValores-1];
            valores[contValores-1] = 0;
            contValores--;
            notifyAll();
            
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        
        return valor;
    }
}