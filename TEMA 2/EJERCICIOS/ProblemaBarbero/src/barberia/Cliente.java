package barberia;

/**
 *
 * @author Alejandro
 */
public class Cliente {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cliente " + id;
    }
}