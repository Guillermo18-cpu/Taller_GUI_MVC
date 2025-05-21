package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DocenteVista extends JPanel {

    public JTextField txtCodigo, txtNombre;
    public JButton btnAgregar, btnActualizar, btnEliminar, btnLimpiar;
    public JTable tabla;
    public DefaultTableModel modeloTabla;

    public DocenteVista() {
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("Docente"));

        form.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        form.add(txtCodigo);

        form.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        form.add(txtNombre);

        btnAgregar = new JButton("Agregar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        JPanel botones = new JPanel();
        botones.add(btnAgregar);
        botones.add(btnActualizar);
        botones.add(btnEliminar);
        botones.add(btnLimpiar);

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
