package propuesta2.pkg5;

/**
 *
 * @author Alejandro
 */
public class Hilo extends Thread {
    
    private String nombre;
    
    public Hilo (String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        
        while(true) {
            System.out.println("Soy el hilo " + nombre + " y estoy trabajando.");
            
            try {
                Thread.sleep( (int) (Math.random() * 10 + 1) * 1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        String[] nombres = {"Marco", "Clara", "Linton", "Julia", "Azir"};
        
        for (int indice = 0; indice < 5; indice++) {
            (new Hilo(nombres[indice])).start();
        }
    }
}