package extra.dibujando;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Compas unCompas   = new Compas();
        Circulo unCirculo = unCompas.dibujarCirculoConRadio(5);

        Cartuchera unaCartuchera = new Cartuchera();
        unaCartuchera.guardar(
                List.of(new Pincel(), new Pincel(), new Pincel())
        );

        Pincel unPincel    = unaCartuchera.getPinceles().get(2);
        Color unColor      = Color.ROJO;
        unPincel.seleccionarColor(unColor);
        unPincel.pintar(unCirculo);
        Integer superficie = unCirculo.calcularSuperficie();
        System.out.println(superficie);
    }
}
