package controlador;

import modelo.*;
import vista.VistaMatricula;

import java.awt.event.*;
import java.util.List;

public class MatriculaControlador {
    private final VistaMatricula vista;
    private List<Estudiante> estudiantes;
    private List<ModeloCurso> cursos;

    public MatriculaControlador(VistaMatricula vista) {
        this.vista = vista;
        cargarCombos();
        cargarTabla();
        initEventos();
    }

    private void cargarCombos() {
        estudiantes = Estudiante.obtenerTodos();
        cursos = ModeloCurso.obtenerTodos();

        for (Estudiante e : estudiantes) vista.cbEstudiante.addItem(e.getNombre());
        for (ModeloCurso c : cursos) vista.cbCurso.addItem(c.getNomCurso());
    }

    private void cargarTabla() {
        vista.modeloTabla.setRowCount(0);
        for (ModeloMatricula m : ModeloMatricula.obtenerTodas()) {
            vista.modeloTabla.addRow(new Object[]{
                ModeloMatricula.nombreEstudiante(m.getCodEstudiante()),
                ModeloMatricula.nombreCurso(m.getCodCurso()),
                m.getNotaCurso()
            });
        }
    }

    private void initEventos() {
        vista.buttonRegistrar.addActionListener(e -> {
            try {
                int codEst = estudiantes.get(vista.cbEstudiante.getSelectedIndex()).getCodigo();
                int codCurso = cursos.get(vista.cbCurso.getSelectedIndex()).getCodCurso();
                double nota = Double.parseDouble(vista.txtNota.getText());

                ModeloMatricula m = new ModeloMatricula(codEst, codCurso, nota);
                if (m.insertar()) {
                    cargarTabla();
                    vista.txtNota.setText("");
                }
            } catch (Exception ex) {
                System.err.println("Error al registrar: " + ex.getMessage());
            }
        });
    }
}
