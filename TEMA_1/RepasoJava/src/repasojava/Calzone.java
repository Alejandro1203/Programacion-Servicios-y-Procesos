package repasojava;

/**
 *
 * @author Alejandro
 */
public class Calzone extends Pizza{

    public Calzone(int tiempoCoccion, double precio) {
        super(tiempoCoccion, precio*1.05);
    }
}