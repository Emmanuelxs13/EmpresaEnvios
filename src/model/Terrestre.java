package model;

// Clase que representa un envío realizado por medio terrestre.
// Calcula el costo del envío según la distancia y el peso usando tarifas específicas.
public class Terrestre extends Envio {
    // Tarifa por kilómetro recorrido (COP)
    private static final double TARIFA_KM = 1500;
    // Recargo por cada kilogramo transportado (COP)
    private static final double RECARGO_KG = 2000;

    // Constructor: inicializa los datos del envío terrestre
    public Terrestre(String codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }

    // Calcula el costo total del envío terrestre
    // Fórmula: (distancia * tarifa por km) + (peso * recargo por kg)
     
    public double calcularTarifa() {
        return (getDistancia() * TARIFA_KM) + (getPeso() * RECARGO_KG);
    }
}
