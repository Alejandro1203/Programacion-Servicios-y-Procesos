package concurrenciaparte1;

/**
 *
 * @author Alejandro
 */
public class ConcurrenciaParte1 {

    public static void main(String[] args) {
        Contador contador1 = new Contador();
        Contador contador2 = new Contador();
        Contador contador3 = new Contador();
        Contador contador4 = new Contador();
        
        contador1.start();
        contador2.start();
        contador3.start();
        contador4.start();
    }
    
}
