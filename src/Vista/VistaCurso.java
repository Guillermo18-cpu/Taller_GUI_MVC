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

        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("Curso"));

        form.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        form.add(txtCodigo);

        form.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        form.add(txtNombre);

        form.add(new JLabel("Docente:"));
        cbDocente = new JComboBox<>();
        form.add(cbDocente);

        ButtonAgregar = new JButton("Agregar");
        ButtonActualizar = new JButton("Actualizar");
        ButtonEliminar = new JButton("Eliminar");
        ButtonLimpiar = new JButton("Limpiar");

        JPanel botones = new JPanel();
        botones.add(ButtonAgregar);
        botones.add(ButtonActualizar);
        botones.add(ButtonEliminar);
        botones.add(ButtonLimpiar);

        modeloTabla = new DefaultTableModel(new String[]{"Código", "Nombre", "Docente"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modeloTabla);

        add(form, BorderLayout.NORTH);
        add(botones, BorderLayout.CENTER);
        add(new JScrollPane(tabla), BorderLayout.SOUTH);
    }
}
