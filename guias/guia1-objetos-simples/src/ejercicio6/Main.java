package ejercicio6;

public class Main {
    public static void main(String[] args) {
        Fecha fecha1 = new Fecha(14, 6, 1992);
        fecha1.setFormato(new FormatoFechaNumerico());
        fecha1.imprimir();

        fecha1.setFormato(new FormatoFechaTexto());
        fecha1.imprimir();

        fecha1.setFormato(new FormatoFechaDiaDelAnio());
        fecha1.imprimir();

        Fecha fecha2 = new Fecha("Junio", 14, 1992);
        fecha2.setFormato(new FormatoFechaTexto());
        fecha2.imprimir();

        Fecha fecha3 = new Fecha(165, 1992);
        fecha3.setFormato(new FormatoFechaNumerico());
        fecha3.imprimir();

        fecha3.setFormato(new FormatoFechaDiaDelAnio());
        fecha3.imprimir();
    }
}
