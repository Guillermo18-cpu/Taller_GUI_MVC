package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaMatricula extends JPanel {
    public JComboBox<String> cbEstudiante, cbCurso;
    public JTextField txtNota;
    public JButton buttonRegistrar;
    public JTable tabla;
    public DefaultTableModel modeloTabla;

    public VistaMatricula() {
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("Registrar Matrícula"));

        form.add(new JLabel("Estudiante:"));
        cbEstudiante = new JComboBox<>(); form.add(cbEstudiante);

        form.add(new JLabel("Curso:"));
        cbCurso = new JComboBox<>(); form.add(cbCurso);

        form.add(new JLabel("Nota:"));
        txtNota = new JTextField(); form.add(txtNota);

        form.add(new JLabel(""));
        buttonRegistrar = new JButton("Registrar");
        form.add(buttonRegistrar);

        modeloTabla = new DefaultTableModel(new String[]{"Estudiante", "Curso", "Nota"}, 0);
        tabla = new JTable(modeloTabla);

        add(form, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }
}
