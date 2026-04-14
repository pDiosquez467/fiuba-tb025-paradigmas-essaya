package extra.dibujando;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Cartuchera {

    private final List<Guardable> items;

    public Cartuchera() {
        this.items = new ArrayList<>();
    }

    public void guardar(Guardable item) {
        if (item == null) return;
        items.add(item);
    }

    public void guardar(Collection<Guardable> nuevos) {
        if (nuevos == null) return;
        items.addAll(nuevos);
    }

    public List<Pincel> getPinceles() {
        return items.stream()
                .filter(item -> item instanceof Pincel)
                .map(item -> (Pincel)item)
                .toList();
    }

    public List<Guardable> getItems() {
        return List.copyOf(items);
    }
}
