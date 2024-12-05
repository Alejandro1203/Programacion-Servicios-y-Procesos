package repasojava;

/**
 *
 * @author Alejandro
 */
public class Pizza {
    private static int id = 0;
    private int tiempoCoccion;
    private double precio;
    private int numId = 0;

    public int getNumId() {
        return numId;
    }
    
    public int getTiempoCoccion() {
        return tiempoCoccion;
    }

    public void setTiempoCoccion(int tiempoCoccion) {
        this.tiempoCoccion = tiempoCoccion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Pizza(int tiempoCoccion, double precio) {
        this.tiempoCoccion = tiempoCoccion;
        this.precio = precio;
        numId = id++;
    }

    @Override
    public String toString() {
        String cadena = "";
        
        cadena += "Pizza" + this.getClass().getSimpleName() + "\nID: " + this.numId + "\nTiempo cocción: " + this.tiempoCoccion + " segundos.\n" +
                String.format("Precio: %.2f", precio);
        
        return cadena;
    }
}