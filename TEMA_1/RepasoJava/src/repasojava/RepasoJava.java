package repasojava;

/**
 *
 * @author Alejandro
 */
public class RepasoJava {
    
    public static int menu() {
        int opcion;
        
        do {
            System.out.println();
            System.out.println("""
                               ------Pizzería Belén------
                               1. Pedir pizza
                               2. Porcentaje de pizzas clásicas con borde relleno
                               3. Encendido de horno
                               0.Salir""");
            
            opcion = EntradaTeclado.pedirEntero("Introduzca una opción: ");
        } while (opcion < 0 || opcion > 3);
        
        return opcion;
    }
    
    public static Pizza pedirPizza() {
        int tiempoCoccion, tipo;
        double precio;
        char borde;
        boolean llevaBorde;
        Pizza pizza;
        
        tiempoCoccion = EntradaTeclado.pedirEntero("Indique el tiempo de cocción: ");
        precio = EntradaTeclado.pedirDouble("Indique el precio de la pizza: ");
        
        do {
            tipo = EntradaTeclado.pedirEntero("Indique si es Pizza Clásica(1) o Calzone(2): ");
        } while (tipo < 1 || tipo > 2);
        
        if(tipo == 1) {
            do {
                borde = EntradaTeclado.pedirChar("La pizza lleva borde (S) o no(N): ");
            } while ((borde != 'S') && (borde != 'N'));
            
            llevaBorde = (borde == 'S');
            pizza = new Clasica(llevaBorde, tiempoCoccion, precio);
        } else {
            pizza = new Calzone(tiempoCoccion, precio);
        }
        
        return pizza;
    } 
    
    public static void tiempoHorneado(int segundos) {
        try {
            java.lang.Thread.sleep(segundos * 1000);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

    
    public static void main(String[] args) {
        Cola<Pizza> pizzas = new Cola<>();
        int opcion = 0;
        Pizza pizza;
        int numBorde;
        
        do {
            do {
                opcion = menu();
            } while (opcion < 0 || opcion > 4);
            
            switch (opcion) {
                case 1 -> {
                    pizza = pedirPizza();
                    pizzas.push(pizza);
                }
                
                case 2 -> {
                    numBorde = 0;
                    
                    System.out.println(pizzas.getNumElementos());
                    for (int indice = 1; indice <= pizzas.getNumElementos(); indice++) {
                        pizza = pizzas.pop();
                        System.out.println(pizza.toString());
                        if(pizza instanceof Clasica clasica) {
                            if(clasica.isBordeRelleno()) {
                                numBorde++;
                            }
                        } 
                        pizzas.push(pizza);
                    }
                    
                    if(pizzas.getNumElementos() > 0) {
                        System.out.println(numBorde);
                        System.out.println(pizzas.getNumElementos());
                        System.out.printf("El porcentaje de pizzas con borde de queso es de: %.2f", ((numBorde / pizzas.getNumElementos()) * 100.0));
                    } else {
                        System.out.println("No hay pizzas.");
                    }
                }
                
                case 3 -> {
                    for (int indice = 1; indice <= pizzas.getNumElementos(); indice++) {
                        pizza = pizzas.pop();
                        
                        if(pizza instanceof Calzone) {
                            System.out.println(pizza.toString());
                            tiempoHorneado(pizza.getTiempoCoccion());
                        } else {
                            pizzas.push(pizza);
                        }
                    }
                    
                    for (int indice = 1; indice <= pizzas.getNumElementos(); indice++) {
                        pizza = pizzas.pop();
                        System.out.println(pizza.toString());
                        tiempoHorneado(pizza.getTiempoCoccion());
                    }
                }
            }
        } while (opcion != 0);
    }
}