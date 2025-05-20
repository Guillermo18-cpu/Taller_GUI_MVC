package controlador;

import modelo.ModeloCurso;
import modelo.Docente;
import vista.VistaCurso;

import java.awt.event.*;
import java.util.List;

public class CursoControlador {
    private final VistaCurso vista;
    private List<Docente> docentes;

    public CursoControlador(VistaCurso vista) {
        this.vista = vista;
        cargarDocentes();
        initControladores();
        cargarTabla();
    }

    private void cargarDocentes() {
        docentes = Docente.obtenerTodos();
        for (Docente d : docentes) {
            vista.cbDocente.addItem(d.getNombre());
        }
    }

    private void initControladores() {
        vista.ButtonAgregar.addActionListener(e -> insertar());
        vista.ButtonActualizar.addActionListener(e -> actualizar());
        vista.ButtonEliminar.addActionListener(e -> eliminar());
        vista.ButtonLimpiar.addActionListener(e -> limpiar());

        vista.tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tabla.getSelectedRow();
                vista.txtCodigo.setText(vista.modeloTabla.getValueAt(fila, 0).toString());
                vista.txtNombre.setText(vista.modeloTabla.getValueAt(fila, 1).toString());
                vista.cbDocente.setSelectedItem(vista.modeloTabla.getValueAt(fila, 2).toString());
            }
        });
    }

    private void insertar() {
        int cod = Integer.parseInt(vista.txtCodigo.getText());
        String nombre = vista.txtNombre.getText();
        int codDoc = docentes.get(vista.cbDocente.getSelectedIndex()).getCodigo();

        ModeloCurso c = new ModeloCurso(cod, nombre, codDoc);
        if (c.insertar()) {
            cargarTabla();
            limpiar();
        }
    }

    private void actualizar() {
        int cod = Integer.parseInt(vista.txtCodigo.getText());
        String nombre = vista.txtNombre.getText();
        int codDoc = docentes.get(vista.cbDocente.getSelectedIndex()).getCodigo();

        ModeloCurso c = new ModeloCurso(cod, nombre, codDoc);
        if (c.actualizar()) {
            cargarTabla();
            limpiar();
        }
    }

    private void eliminar() {
        int cod = Integer.parseInt(vista.txtCodigo.getText());
        ModeloCurso c = new ModeloCurso();
        c.setCodCurso(cod);
        if (c.eliminar()) {
            cargarTabla();
            limpiar();
        }
    }

    private void limpiar() {
        vista.txtCodigo.setText("");
        vista.txtNombre.setText("");
        vista.cbDocente.setSelectedIndex(0);
        vista.tabla.clearSelection();
    }

    private void cargarTabla() {
        vista.modeloTabla.setRowCount(0);
        for (ModeloCurso c : ModeloCurso.obtenerTodos()) {
            String nombreDocente = docentes.stream()
                .filter(d -> d.getCodigo() == c.getCodDocente())
                .findFirst()
                .map(Docente::getNombre)
                .orElse("Desconocido");
            vista.modeloTabla.addRow(new Object[]{c.getCodCurso(), c.getNomCurso(), nombreDocente});
        }
    }
}
