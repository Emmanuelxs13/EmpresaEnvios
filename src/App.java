import view.OperadorLogisticoUI;
import javax.swing.SwingUtilities;

// Main: inicia la aplicación
public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OperadorLogisticoUI().setVisible(true));
    }
}
