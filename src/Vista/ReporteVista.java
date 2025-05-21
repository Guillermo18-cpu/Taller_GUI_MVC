package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReporteVista extends JPanel {

    public JTable tabla;
    public DefaultTableModel modeloTabla;

    public ReporteVista() {
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel(new String[]{"Estudiante", "Curso", "Docente", "Nota"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modeloTabla);

        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }
}
