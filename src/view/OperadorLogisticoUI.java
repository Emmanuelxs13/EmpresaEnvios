package view;

// Importa las clases necesarias para la interfaz gráfica y la lógica de negocio
import model.*; // Clases de los envíos
import controller.Logistica; // Lógica para manejar la lista de envíos
import javax.swing.*; // Componentes gráficos de Java
import javax.swing.table.DefaultTableModel; // Modelo de tabla para mostrar datos
import java.awt.*; // Herramientas para diseño visual
import java.text.NumberFormat; // Formato de números
import java.util.Locale; // Configuración regional

// Esta clase representa la ventana principal de la aplicación de envíos
public class OperadorLogisticoUI extends JFrame {
    // Lógica para gestionar la lista de envíos
    private Logistica logistica;
    // Campos de texto para ingresar datos del envío
    private JTextField txtCodigo;
    private JTextField txtCliente;
    private JTextField txtPeso;
    private JTextField txtDistancia;
    // Menú desplegable para seleccionar el tipo de envío
    private JComboBox<String> cmbTipo;
    // Modelo y tabla para mostrar los envíos registrados
    private DefaultTableModel tableModel;
    private JTable table;
    // Constantes para el diseño visual
    private static final String FONT_NAME = "Segoe UI";
    private static final int INPUT_HEIGHT = 32;
    private static final int INPUT_FONT_SIZE = 15;
    // Formato para mostrar valores en pesos colombianos
    private NumberFormat copFormat = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

    // Constructor: configura la ventana principal y llama a la función que arma la interfaz
    public OperadorLogisticoUI() {
        logistica = new Logistica(); // Inicializa la lógica de envíos
        setTitle("Operador Logístico"); // Título de la ventana
        setMinimumSize(new Dimension(600, 400)); // Tamaño mínimo
        setSize(800, 500); // Tamaño inicial
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Cierra la app al cerrar la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setLayout(new BorderLayout()); // Usa un diseño de bordes
        getContentPane().setBackground(new Color(243, 244, 246)); // Color de fondo principal
        initUI(); // Construye la interfaz visual
    }

    // Esta función arma y organiza todos los componentes visuales de la ventana
    private void initUI() {
        // Panel central que contiene el formulario y la tabla
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225), 1), // Borde gris elegante
            BorderFactory.createEmptyBorder(18, 18, 18, 18)
        ));
        panelCentral.setBackground(new Color(255, 255, 255)); // Fondo blanco

        // Panel para los campos de entrada y etiquetas
        JPanel panelDatos = new JPanel(new GridBagLayout()); // Distribución flexible
        panelDatos.setBackground(new Color(255, 255, 255));
        GridBagConstraints gbc = new GridBagConstraints(); // Ayuda a posicionar los elementos
        gbc.insets = new Insets(6, 6, 6, 6); // Espaciado entre elementos
        gbc.fill = GridBagConstraints.HORIZONTAL; // Los campos ocupan todo el ancho disponible

        // Inicializa los campos de entrada y el menú de tipo de envío
        txtCodigo = createInputField(); // Campo para el código
        txtCliente = createInputField(); // Campo para el cliente
        txtPeso = createInputField(); // Campo para el peso
        txtDistancia = createInputField(); // Campo para la distancia
        cmbTipo = new JComboBox<>(new String[]{"Terrestre", "Aereo", "Maritimo"}); // Tipos de envío
        cmbTipo.setFont(new Font(FONT_NAME, Font.PLAIN, INPUT_FONT_SIZE));
        cmbTipo.setPreferredSize(new Dimension(120, INPUT_HEIGHT));

        // Etiquetas y campos alineados en el panel de datos
        String[] labels = {"Número", "Cliente", "Peso (Kg)", "Distancia Km", "Tipo"};
        JComponent[] fields = {txtCodigo, txtCliente, txtPeso, txtDistancia, cmbTipo};
        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = i; gbc.gridy = 0;
            JLabel label = new JLabel(labels[i]); // Crea la etiqueta
            label.setFont(new Font(FONT_NAME, Font.BOLD, 15)); // Fuente clara y grande
            label.setForeground(new Color(30, 41, 59)); // Color elegante
            panelDatos.add(label, gbc); // Agrega la etiqueta
            gbc.gridy = 1;
            panelDatos.add(fields[i], gbc); // Agrega el campo debajo de la etiqueta
        }

        // Configura la tabla para mostrar los envíos agregados
        tableModel = new DefaultTableModel(new Object[]{"Tipo", "Código", "Cliente", "Peso", "Distancia", "Costo"}, 0);
        table = new JTable(tableModel) {
            // Personaliza el renderizado de las filas para alternar colores y resaltar selección
             
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row))
                    c.setBackground(row % 2 == 0 ? new Color(241, 245, 249) : Color.WHITE); // Color alterno para filas
                else
                    c.setBackground(new Color(59, 130, 246)); // Color de selección
                c.setForeground(isRowSelected(row) ? Color.WHITE : new Color(30, 41, 59)); // Color de texto
                if (c instanceof JComponent) {
                    ((JComponent) c).setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 8)); // Espaciado interno
                }
                return c;
            }
        };
        table.setRowHeight(28); // Altura de las filas
        table.getTableHeader().setFont(new Font(FONT_NAME, Font.BOLD, 14)); // Fuente del encabezado
        table.getTableHeader().setBackground(new Color(30, 64, 175)); // Fondo del encabezado
        table.getTableHeader().setForeground(Color.WHITE); // Texto del encabezado
        table.setFont(new Font(FONT_NAME, Font.PLAIN, 13)); // Fuente de la tabla
        table.setGridColor(new Color(203, 213, 225)); // Color de las líneas
        table.setSelectionBackground(new Color(59, 130, 246)); // Fondo al seleccionar
        table.setSelectionForeground(Color.WHITE); // Texto al seleccionar
        JScrollPane scrollPane = new JScrollPane(table); // Permite desplazarse en la tabla
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(203, 213, 225), 1)); // Borde elegante

        // Panel de botones para guardar y eliminar envíos
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Botones centrados
        panelBotones.setBackground(new Color(241, 245, 249)); // Fondo claro
        panelBotones.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(203, 213, 225))); // Borde superior
        JButton btnGuardar = new JButton("Guardar"); // Botón para guardar
        JButton btnEliminar = new JButton("Eliminar"); // Botón para eliminar
        // Configuración visual y de interacción para el botón Guardar
        btnGuardar.setBackground(new Color(59, 130, 246)); // Fondo azul
        btnGuardar.setForeground(Color.WHITE); // Texto blanco
        btnGuardar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(59, 130, 246), 1),
            BorderFactory.createEmptyBorder(6, 0, 6, 0)
        ));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setPreferredSize(new Dimension(140, 38));
        btnGuardar.setFont(new Font(FONT_NAME, Font.BOLD, 16));
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardar.setOpaque(true);
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            // Cambia el color al pasar el mouse
             
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardar.setBackground(new Color(37, 99, 235)); // Azul más oscuro
            }
             
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardar.setBackground(new Color(59, 130, 246)); // Azul original
            }
        });
        // Configuración visual y de interacción para el botón Eliminar
        btnEliminar.setBackground(new Color(239, 68, 68)); // Fondo rojo
        btnEliminar.setForeground(Color.WHITE); // Texto blanco
        btnEliminar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(239, 68, 68), 1),
            BorderFactory.createEmptyBorder(6, 0, 6, 0)
        ));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setPreferredSize(new Dimension(140, 38));
        btnEliminar.setFont(new Font(FONT_NAME, Font.BOLD, 16));
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEliminar.setOpaque(true);
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            // Cambia el color al pasar el mouse
             
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminar.setBackground(new Color(220, 38, 38)); // Rojo más oscuro
            }
             
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminar.setBackground(new Color(239, 68, 68)); // Rojo original
            }
        });
        panelBotones.add(btnGuardar);
        panelBotones.add(btnEliminar);

        // Acciones de los botones para agregar y eliminar envíos
        btnGuardar.addActionListener(e -> agregarEnvio()); // Cuando se presiona Guardar
        btnEliminar.addActionListener(e -> eliminarEnvio()); // Cuando se presiona Eliminar

        // Agrega los paneles a la ventana principal
        panelCentral.add(panelDatos, BorderLayout.NORTH); // Formulario arriba
        panelCentral.add(scrollPane, BorderLayout.CENTER); // Tabla al centro
        add(panelCentral, BorderLayout.CENTER); // Panel central en la ventana
        add(panelBotones, BorderLayout.SOUTH); // Botones abajo
    }

    // Crea y configura un campo de entrada para el formulario
    private JTextField createInputField() {
        JTextField field = new JTextField(); // Campo de texto
        field.setPreferredSize(new Dimension(120, INPUT_HEIGHT)); // Tamaño
        field.setFont(new Font(FONT_NAME, Font.PLAIN, INPUT_FONT_SIZE)); // Fuente
        field.setBackground(new Color(241, 245, 249)); // Fondo claro
        field.setForeground(new Color(30, 41, 59)); // Texto oscuro
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225), 1), // Borde gris
            BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
        field.setCaretColor(new Color(59, 130, 246)); // Color del cursor
        field.setSelectionColor(new Color(191, 219, 254)); // Color al seleccionar texto
        field.setOpaque(true);
        // Efecto visual al enfocar el campo
        field.addFocusListener(new java.awt.event.FocusAdapter() {
             
            public void focusGained(java.awt.event.FocusEvent evt) {
                field.setBorder(BorderFactory.createLineBorder(new Color(59, 130, 246), 2)); // Borde azul al enfocar
            }
             
            public void focusLost(java.awt.event.FocusEvent evt) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
                    BorderFactory.createEmptyBorder(6, 10, 6, 10)
                ));
            }
        });
        return field;
    }

    // Toma los datos del formulario y agrega un nuevo envío a la lista
    private void agregarEnvio() {
        // Restablece el color de fondo de los campos
        txtCodigo.setBackground(Color.WHITE);
        txtCliente.setBackground(Color.WHITE);
        txtPeso.setBackground(Color.WHITE);
        txtDistancia.setBackground(Color.WHITE);
        try {
            // Obtiene los datos ingresados por el usuario
            String codigo = txtCodigo.getText().trim();
            String cliente = txtCliente.getText().trim();
            double peso = Double.parseDouble(txtPeso.getText().trim());
            double distancia = Double.parseDouble(txtDistancia.getText().trim());
            String tipo = (String) cmbTipo.getSelectedItem();

            boolean error = false;
            // Valida que los campos no estén vacíos o tengan valores válidos
            if (codigo.isEmpty()) { txtCodigo.setBackground(new Color(254, 202, 202)); error = true; } // Campo vacío
            if (cliente.isEmpty()) { txtCliente.setBackground(new Color(254, 202, 202)); error = true; }
            if (peso <= 0) { txtPeso.setBackground(new Color(254, 202, 202)); error = true; }
            if (distancia <= 0) { txtDistancia.setBackground(new Color(254, 202, 202)); error = true; }
            if (error) throw new IllegalArgumentException("Completa todos los campos correctamente.");

            // Crea el objeto de envío según el tipo seleccionado
            Envio envio;
            switch (tipo) {
                case "Terrestre": envio = new Terrestre(codigo, cliente, peso, distancia); break;
                case "Aereo": envio = new Aereo(codigo, cliente, peso, distancia); break;
                case "Maritimo": envio = new Maritimo(codigo, cliente, peso, distancia); break;
                default: throw new IllegalArgumentException("Tipo de envío no válido");
            }
            // Agrega el envío a la lista y actualiza la tabla
            logistica.agregarEnvio(envio);
            actualizarTabla();
            limpiarCampos();
            txtCodigo.requestFocus(); // Vuelve a enfocar el campo código
            JOptionPane.showMessageDialog(this, "Envío agregado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Elimina el envío seleccionado de la tabla y la lista
    private void eliminarEnvio() {
        int fila = table.getSelectedRow(); // Obtiene la fila seleccionada
        if (fila >= 0) {
            String codigo = (String) tableModel.getValueAt(fila, 1); // Obtiene el código
            if (logistica.eliminarEnvio(codigo)) { // Elimina el envío
                actualizarTabla(); // Actualiza la tabla
                limpiarCampos(); // Limpia los campos
                txtCodigo.requestFocus(); // Vuelve a enfocar el campo código
                JOptionPane.showMessageDialog(this, "Envío eliminado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el envío.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un envío en la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Actualiza la tabla para mostrar todos los envíos registrados
    private void actualizarTabla() {
        tableModel.setRowCount(0); // Limpia la tabla
        for (Envio envio : logistica.listarEnvios()) { // Recorre la lista de envíos
            tableModel.addRow(new Object[]{
                envio.getClass().getSimpleName(), // Tipo de envío
                envio.getCodigo(), // Código
                envio.getCliente(), // Cliente
                envio.getPeso(), // Peso
                envio.getDistancia(), // Distancia
                copFormat.format(envio.calcularTarifa()) // Costo formateado
            });
        }
    }

    // Limpia los campos del formulario para ingresar un nuevo envío
    private void limpiarCampos() {
        txtCodigo.setText("");
        txtCliente.setText("");
        txtPeso.setText("");
        txtDistancia.setText("");
        cmbTipo.setSelectedIndex(0); // Vuelve al primer tipo
    }
}
