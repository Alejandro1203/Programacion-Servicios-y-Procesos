package repasojava;

/**
 *
 * @author Alejandro
 */
public class Clasica extends Pizza{
    private boolean bordeRelleno;

    public boolean isBordeRelleno() {
        return bordeRelleno;
    }

    public void setBordeRelleno(boolean bordeRelleno) {
        this.bordeRelleno = bordeRelleno;
    }

    public Clasica(boolean bordeRelleno, int tiempoCoccion, double precio) {
        super(tiempoCoccion, precio);
        this.bordeRelleno = bordeRelleno;
    }

    @Override
    public String toString() {
        String cadena =  super.toString();
        
        cadena += "\nBorde relleno: " + this.bordeRelleno;
        
        return cadena;
    }
}