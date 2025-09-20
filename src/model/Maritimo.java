package model;

// Envío por medio marítimo
public class Maritimo extends Envio {
    private static final double TARIFA_KM = 800;
    private static final double RECARGO_KG = 1000;

    public Maritimo(String codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }

    // Calcula la tarifa para marítimo
     
    public double calcularTarifa() {
        return (getDistancia() * TARIFA_KM) + (getPeso() * RECARGO_KG);
    }
}
