package atm;
import java.util.List;

/**
 * Created by peter on 04.08.2017.
 */
public class Memento {
    private final List<Cell> cells;

    public Memento(List<Cell> cells) {
        this.cells = cells;
    }

    public List<Cell> getCells() {
        return cells;
    }
}
