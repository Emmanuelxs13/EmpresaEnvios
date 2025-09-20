package model;

// Esta clase representa un envío genérico
public abstract class Envio {
    private String codigo;
    private String cliente;
    private double peso;
    private double distancia;

    // Constructor
    public Envio(String codigo, String cliente, double peso, double distancia) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.peso = peso;
        this.distancia = distancia;
    }

    // Métodos para obtener y cambiar los datos
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }
    public double getDistancia() { return distancia; }
    public void setDistancia(double distancia) { this.distancia = distancia; }

    // Método que cada tipo de envío debe implementar para calcular la tarifa
    public abstract double calcularTarifa();
}
