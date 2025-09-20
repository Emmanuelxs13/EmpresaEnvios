package model;

// Envío por medio aéreo
public class Aereo extends Envio {
    private static final double TARIFA_KM = 5000;
    private static final double RECARGO_KG = 4000;

    public Aereo(String codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }

    // Calcula la tarifa para aéreo
     
    public double calcularTarifa() {
        return (getDistancia() * TARIFA_KM) + (getPeso() * RECARGO_KG);
    }
}
