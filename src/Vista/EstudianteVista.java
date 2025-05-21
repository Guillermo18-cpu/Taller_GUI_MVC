package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EstudianteVista extends JPanel {

    public JTextField txtCodigo, txtNombre;
    public JButton btnAgregar, btnActualizar, btnEliminar, btnLimpiar;
    public JTable tabla;
    public DefaultTableModel modeloTabla;

    public EstudianteVista() {
        setLayout(new BorderLayout());

        // ───────── Panel superior (formulario + botones)
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BorderLayout());

        // Subpanel de formulario (GridLayout)
        JPanel panelFormulario = new JPanel(new GridLayout(2, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Estudiante"));

        panelFormulario.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        panelFormulario.add(txtCodigo);

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelSuperior.add(panelFormulario, BorderLayout.NORTH);

        // Subpanel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnAgregar = new JButton("Agregar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        // Agregar panel superior al top
        add(panelSuperior, BorderLayout.NORTH);

        // ───────── Panel de tabla expandible
        modeloTabla = new DefaultTableModel(new String[]{"Código", "Nombre"}, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);
    }

}
