package ejercicio2;

import java.util.ArrayList;

public class ListaDuplicanteComp<T> {

    private final ArrayList<T> arrayList;

    public ListaDuplicanteComp() {
        this.arrayList = new ArrayList<>();
    }

    public boolean add(T t) {
        arrayList.add(t);
        return arrayList.add(t);
    }

    public T get(int i) {
        return arrayList.get(i);
    }
}
