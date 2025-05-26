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
    public JButton buttonActualizar;

    public VistaMatricula() {

        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("Registrar Matrícula"));

        form.add(new JLabel("Estudiante:"));
        cbEstudiante = new JComboBox<>();
        form.add(cbEstudiante);

        form.add(new JLabel("Curso:"));
        cbCurso = new JComboBox<>();
        form.add(cbCurso);

        form.add(new JLabel("Nota:"));
        txtNota = new JTextField();
        form.add(txtNota);

        // Panel para los botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonRegistrar = new JButton("Registrar");
        buttonRegistrar.setPreferredSize(new Dimension(100, 25));
        buttonActualizar = new JButton("Actualizar Nota");
        buttonActualizar.setPreferredSize(new Dimension(140, 25));
        panelBotones.add(buttonRegistrar);
        panelBotones.add(buttonActualizar);

        form.add(new JLabel(""));
        form.add(panelBotones);

        modeloTabla = new DefaultTableModel(new String[]{"Estudiante", "Curso", "Nota"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modeloTabla);

        add(form, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }
}
