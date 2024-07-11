import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main extends JFrame {
    private JTextField nombreField;
    private JTextField cedulaField;
    private JTextField edadField;
    private JTextField carreraField;
    private JCheckBox activoCheckBox;
    private JButton guardarButton;
    private JButton buscarButton;
    private JTextField buscarCedulaField; // Campo para buscar por cédula
    private JTextArea resultadoArea;

    private ArrayList<Estudiante> listaEstudiantes;

    public Main() {
        super("Gestión de Estudiantes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        JPanel panelBuscar = new JPanel(new GridBagLayout());
        JPanel panelResultados = new JPanel(new BorderLayout());

        // Componentes del formulario
        GridBagConstraints gbcForm = new GridBagConstraints();
        gbcForm.insets = new Insets(5, 5, 5, 5);
        gbcForm.anchor = GridBagConstraints.WEST;

        nombreField = new JTextField(20);
        cedulaField = new JTextField(20);
        edadField = new JTextField(20);
        carreraField = new JTextField(20);
        activoCheckBox = new JCheckBox("Activo");
        guardarButton = new JButton("Guardar");

        gbcForm.gridx = 0;
        gbcForm.gridy = 0;
        panelFormulario.add(new JLabel("Nombre:"), gbcForm);
        gbcForm.gridx = 1;
        panelFormulario.add(nombreField, gbcForm);

        gbcForm.gridx = 0;
        gbcForm.gridy++;
        panelFormulario.add(new JLabel("Cédula:"), gbcForm);
        gbcForm.gridx = 1;
        panelFormulario.add(cedulaField, gbcForm);

        gbcForm.gridx = 0;
        gbcForm.gridy++;
        panelFormulario.add(new JLabel("Edad:"), gbcForm);
        gbcForm.gridx = 1;
        panelFormulario.add(edadField, gbcForm);

        gbcForm.gridx = 0;
        gbcForm.gridy++;
        panelFormulario.add(new JLabel("Carrera:"), gbcForm);
        gbcForm.gridx = 1;
        panelFormulario.add(carreraField, gbcForm);

        gbcForm.gridx = 0;
        gbcForm.gridy++;
        panelFormulario.add(new JLabel("Activo:"), gbcForm);
        gbcForm.gridx = 1;
        panelFormulario.add(activoCheckBox, gbcForm);

        gbcForm.gridx = 0;
        gbcForm.gridy++;
        gbcForm.gridwidth = 2;
        panelFormulario.add(guardarButton, gbcForm);

        // Componentes del panel de búsqueda
        GridBagConstraints gbcBuscar = new GridBagConstraints();
        gbcBuscar.insets = new Insets(5, 5, 5, 5);
        gbcBuscar.anchor = GridBagConstraints.WEST;

        buscarCedulaField = new JTextField(20); // Campo para buscar por cédula
        buscarButton = new JButton("Buscar");
        resultadoArea = new JTextArea(10, 40);
        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);

        gbcBuscar.gridx = 0;
        gbcBuscar.gridy = 0;
        panelBuscar.add(new JLabel("Buscar por Cédula:"), gbcBuscar);
        gbcBuscar.gridx = 1;
        panelBuscar.add(buscarCedulaField, gbcBuscar);
        gbcBuscar.gridx = 2;
        panelBuscar.add(buscarButton, gbcBuscar);

        // Componentes del panel de resultados
        panelResultados.setBorder(BorderFactory.createTitledBorder("Resultados de Búsqueda"));
        panelResultados.add(scrollPane, BorderLayout.CENTER);

        // Panel principal
        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);
        panelPrincipal.add(panelBuscar, BorderLayout.CENTER);
        panelPrincipal.add(panelResultados, BorderLayout.SOUTH);

        // Inicializar lista de estudiantes
        listaEstudiantes = new ArrayList<>();

        // Acción del botón guardar
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarEstudiante();
            }
        });

        // Acción del botón buscar
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarEstudiantePorCedula();
            }
        });

        // Agregar panel principal al frame
        add(panelPrincipal);

        // Mostrar ventana
        pack();
        setLocationRelativeTo(null); // Centrar ventana en pantalla
        setVisible(true);
    }

    // Método para guardar un estudiante
    private void guardarEstudiante() {
        String nombre = nombreField.getText();
        String cedula = cedulaField.getText();
        int edad = Integer.parseInt(edadField.getText());
        String carrera = carreraField.getText();
        boolean activo = activoCheckBox.isSelected();

        Estudiante estudiante = new Estudiante(nombre, cedula, edad, carrera, activo);
        listaEstudiantes.add(estudiante);
        mostrarMensaje("Estudiante guardado:\n\nNombre: " + estudiante.getNombre() +
                "\nCédula: " + estudiante.getCedula() +
                "\nEdad: " + estudiante.getEdad() +
                "\nCarrera: " + estudiante.getCarrera() +
                "\nActivo: " + (estudiante.isActivo() ? "Sí" : "No"));
        limpiarCampos();
    }

    // Método para buscar un estudiante por cédula
    private void buscarEstudiantePorCedula() {
        String cedula = buscarCedulaField.getText();
        StringBuilder resultado = new StringBuilder();

        for (Estudiante estudiante : listaEstudiantes) {
            if (estudiante.getCedula().equals(cedula)) {
                resultado.append("Nombre: ").append(estudiante.getNombre()).append("\n");
                resultado.append("Cédula: ").append(estudiante.getCedula()).append("\n");
                resultado.append("Edad: ").append(estudiante.getEdad()).append("\n");
                resultado.append("Carrera: ").append(estudiante.getCarrera()).append("\n");
                resultado.append("Activo: ").append(estudiante.isActivo() ? "Sí" : "No").append("\n\n");
            }
        }

        if (resultado.length() == 0) {
            resultado.append("No se encontró ningún estudiante con la cédula: ").append(cedula);
        }

        resultadoArea.setText(resultado.toString());
    }

    // Método para limpiar los campos del formulario
    private void limpiarCampos() {
        nombreField.setText("");
        cedulaField.setText("");
        edadField.setText("");
        carreraField.setText("");
        activoCheckBox.setSelected(false);
    }

    // Método para mostrar un mensaje de información
    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    // Clase principal que inicia la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
