package controller;

import model.Envio;
import java.util.ArrayList;

// Esta clase maneja la lista de envíos
public class Logistica {
    private ArrayList<Envio> envios;

    // Constructor
    public Logistica() {
        envios = new ArrayList<>();
    }

    // Agrega un envío a la lista
    public void agregarEnvio(Envio envio) {
        envios.add(envio);
    }

    // Elimina un envío por código
    public boolean eliminarEnvio(String codigo) {
        return envios.removeIf(e -> e.getCodigo().equals(codigo));
    }

    // Devuelve la lista de envíos
    public ArrayList<Envio> listarEnvios() {
        return envios;
    }
}
