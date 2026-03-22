package ejercicio2;

import java.util.ArrayList;

public class ListaDuplicanteHerencia<T> extends ArrayList<T> {

    @Override
    public boolean add(T t) {
        super.add(t);
        return super.add(t);
    }
}
