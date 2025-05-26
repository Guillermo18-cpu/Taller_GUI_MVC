package controlador;

import modelo.*;
import vista.VistaMatricula;

import java.awt.event.*;
import java.util.List;
import javax.swing.JOptionPane;

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

        for (Estudiante e : estudiantes) {
            vista.cbEstudiante.addItem(e.getNombre());
        }
        for (ModeloCurso c : cursos) {
            vista.cbCurso.addItem(c.getNomCurso());
        }
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
        vista.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tabla.getSelectedRow();
                if (fila != -1) {
                    String estudiante = vista.tabla.getValueAt(fila, 0).toString();
                    String curso = vista.tabla.getValueAt(fila, 1).toString();
                    String nota = vista.tabla.getValueAt(fila, 2).toString();

                    vista.cbEstudiante.setSelectedItem(estudiante);
                    vista.cbCurso.setSelectedItem(curso);
                    vista.txtNota.setText(nota);
                }
            }
        });
        vista.buttonRegistrar.addActionListener(e -> {
            try {
                if (vista.cbEstudiante.getSelectedIndex() == -1
                        || vista.cbCurso.getSelectedIndex() == -1
                        || vista.txtNota.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(
                            vista,
                            "Por favor, seleccione un estudiante, un curso y registre la nota.",
                            "Campo Incompleto",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                int codEst = estudiantes.get(vista.cbEstudiante.getSelectedIndex()).getCodigo();
                int codCurso = cursos.get(vista.cbCurso.getSelectedIndex()).getCodCurso();
                double nota = Double.parseDouble(vista.txtNota.getText());

                ModeloMatricula m = new ModeloMatricula(codEst, codCurso, nota);
                if (m.insertar()) {
                    JOptionPane.showMessageDialog(
                            vista,
                            "Matrícula registrada exitosamente.",
                            "Registro Exitoso",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    cargarTabla();
                    vista.txtNota.setText("");
                } else {
                    JOptionPane.showMessageDialog(
                            vista,
                            "No se pudo registrar la matrícula.",
                            "Error de Registro",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        vista,
                        "La nota debe ser un número válido.",
                        "Error de Formato",
                        JOptionPane.ERROR_MESSAGE
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        vista,
                        "Ocurrió un error al registrar la matrícula: " + ex.getMessage(),
                        "Error Inesperado",
                        JOptionPane.ERROR_MESSAGE
                );

            }
        });

        vista.buttonActualizar.addActionListener(e -> {
            try {
                if (vista.cbEstudiante.getSelectedIndex() == -1
                        || vista.cbCurso.getSelectedIndex() == -1
                        || vista.txtNota.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(
                            vista,
                            "Por favor, seleccione un estudiante, un curso y la nota a actualizar.",
                            "Campo Incompleto",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                int codEst = estudiantes.get(vista.cbEstudiante.getSelectedIndex()).getCodigo();
                int codCurso = cursos.get(vista.cbCurso.getSelectedIndex()).getCodCurso();
                double nota = Double.parseDouble(vista.txtNota.getText());

                ModeloMatricula m = new ModeloMatricula(codEst, codCurso, nota);
                if (m.actualizar()) {
                    JOptionPane.showMessageDialog(
                            vista,
                            "Nota actualizada exitosamente.",
                            "Actualización Exitosa",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    cargarTabla();
                    vista.txtNota.setText("");
                } else {
                    JOptionPane.showMessageDialog(
                            vista,
                            "No se encontró una matrícula para actualizar.",
                            "Error de Actualización",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        vista,
                        "La nota debe ser un número válido.",
                        "Error de Formato",
                        JOptionPane.ERROR_MESSAGE
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        vista,
                        "Ocurrió un error al actualizar la nota: " + ex.getMessage(),
                        "Error Inesperado",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}
