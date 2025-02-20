package barberia;

import java.util.Scanner;

/**
 *
 * @author Alejandro
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int numSillas;
        long tiempoInicial = System.currentTimeMillis();
        
        System.out.print("Ingrese el número de sillas: ");
        numSillas = sc.nextInt();
        
        ColaClientes cola = new ColaClientes(1, numSillas);
        Barbero barbero = new Barbero(1, cola, tiempoInicial);
        barbero.start();
        
        InsertarClientes insertar = new InsertarClientes(cola, tiempoInicial);
        insertar.start();
    }
}