package filosofo;

public class Mesa {
     
    private boolean[] tenedores;
     
    public Mesa(int numTenedores){
        this.tenedores = new boolean[numTenedores];
    }
     
    public int tenedorIzquierda(int comensal){
        return comensal - 1;
    }
     
    public int tenedorDerecha(int comensal){
        
        if(comensal == tenedores.length) {
            return 0;
        } else {
            return comensal;
        }        
    }
     
    public synchronized void cogerTenedores(int comensal){
         
        while (tenedores[tenedorIzquierda(comensal)] || tenedores[tenedorDerecha(comensal)]) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        
        tenedores[tenedorIzquierda(comensal)] = true;
        tenedores[tenedorDerecha(comensal)] = true;
    }
     
    public synchronized void dejarTenedores (int comensal){
        tenedores[tenedorIzquierda(comensal)] = false;
        tenedores[tenedorDerecha(comensal)] = false;
        notifyAll();        
    }
}