package filosofosemaforo;

/**
 *
 * @author Alejandro
 */
public class Main {

    
    public static void main(String[] args) {
        int num = 5;
        
        Mesa m = new Mesa(num);
        for (int i = 1; i <= num; i++) {
            Filosofo f = new Filosofo(m, i);
            f.start();
        }
    }
}