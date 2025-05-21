package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaCurso extends JPanel {
    public JTextField txtCodigo, txtNombre;
    public JComboBox<String> cbDocente;
    public JButton ButtonAgregar, ButtonActualizar, ButtonEliminar, ButtonLimpiar;
    public JTable tabla;
    public DefaultTableModel modeloTabla;

    public VistaCurso() {
        setLayout(new BorderLayout());

        // Panel principal con BoxLayout para que no haya espacio intermedio
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        // ───────── Panel formulario horizontal
        JPanel panelFormulario = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Curso"));

        panelFormulario.add(new JLabel("Código:"));
        txtCodigo = new JTextField(10);
        panelFormulario.add(txtCodigo);

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField(15);
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Docente:"));
        cbDocente = new JComboBox<>();
        cbDocente.setPreferredSize(new Dimension(150, 25));
        panelFormulario.add(cbDocente);

        // ───────── Panel botones horizontal
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        ButtonAgregar = new JButton("Agregar");
        ButtonActualizar = new JButton("Actualizar");
        ButtonEliminar = new JButton("Eliminar");
        ButtonLimpiar = new JButton("Limpiar");

        panelBotones.add(ButtonAgregar);
        panelBotones.add(ButtonActualizar);
        panelBotones.add(ButtonEliminar);
        panelBotones.add(ButtonLimpiar);

        // Agregamos ambos al panel principal
        panelPrincipal.add(panelFormulario);
        panelPrincipal.add(panelBotones);

        // Agregar panel principal arriba
        add(panelPrincipal, BorderLayout.NORTH);

        // ───────── Tabla en la parte inferior
        modeloTabla = new DefaultTableModel(new String[]{"Código", "Nombre", "Docente"}, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);
    }
}
