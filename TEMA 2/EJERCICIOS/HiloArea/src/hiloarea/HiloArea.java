package hiloarea;

/**
 *
 * @author Alejandro
 */
public class HiloArea extends Thread{

    private double base;
    private double altura;
    
    public HiloArea (double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public void run() {
        double area = (base * altura) / 2;
        
        System.out.println("Con base " + String.format("%.2f", base) + " y altura " + String.format("%.2f", altura) + ". El área es de " + String.format("%.2f", area));
    }
    
    public static void main(String[] args) {
        double base;
        double altura;
        
        for (int indice = 1; indice <= 1000000; indice++) {
            base = (Math.random() * 100 + 1);
            altura = (Math.random() * 100 + 1);
            
            (new HiloArea(base, altura)).start();
        }
    }
}