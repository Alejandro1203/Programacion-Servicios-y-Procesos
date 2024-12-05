package repasojava;

/**
 *
 * @author Alejandro
 */
public class Cola<T> implements AccionesEstructuras<T>{
    private T[] array;
    private int numElementos;

    public Cola() {
        array = (T[]) new Object[10];
        numElementos = 0;
    }
    
    @Override
    public boolean push(T elemento) {
        boolean introducido = true;
        
        if(this.numElementos >= array.length) {
            introducido = false;
        } else {
            array[this.numElementos++] = elemento;
        }
        
        return introducido;
    }

    @Override
    public T pop() {
        T devuelto = array[0];

        for (int indice = 0; indice < this.numElementos - 1; indice++) {
            array[indice] = array[indice + 1];
        }

        this.numElementos--;

        return devuelto;
    }

    @Override
    public boolean esVacia() {
        boolean vacia = false;
        
        if(getNumElementos() == 0) {
            vacia = true;
        }
        
        return vacia;
    }

    @Override
    public int getNumElementos() {
        return this.numElementos;
    }
}