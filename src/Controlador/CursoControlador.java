package controlador;

import modelo.ModeloCurso;
import modelo.Docente;
import vista.VistaCurso;

import java.awt.event.*;
import java.util.List;
import javax.swing.JOptionPane;

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
        vista.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tabla.getSelectedRow();
                if (fila != -1) {
                    String codigo = vista.tabla.getValueAt(fila, 0).toString();
                    String nombre = vista.tabla.getValueAt(fila, 1).toString();
                    String docente = vista.tabla.getValueAt(fila, 2).toString();

                    vista.txtCodigo.setText(codigo);
                    vista.txtNombre.setText(nombre);
                    vista.cbDocente.setSelectedItem(docente);
                }
            }
        });
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
        try {
            if (vista.txtCodigo.getText().trim().isEmpty()
                    || vista.txtNombre.getText().trim().isEmpty()
                    || vista.cbDocente.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(
                        vista,
                        "Por favor, ingrese el código, nombre y seleccione un docente.",
                        "Campo Incompleto",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            int cod = Integer.parseInt(vista.txtCodigo.getText());
            String nombre = vista.txtNombre.getText();
            int codDoc = docentes.get(vista.cbDocente.getSelectedIndex()).getCodigo();

            ModeloCurso c = new ModeloCurso(cod, nombre, codDoc);
            if (c.actualizar()) {
                JOptionPane.showMessageDialog(
                        vista,
                        "Curso actualizado exitosamente.",
                        "Actualización Exitosa",
                        JOptionPane.INFORMATION_MESSAGE
                );
                cargarTabla();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(
                        vista,
                        "No se pudo actualizar el curso.",
                        "Error de Actualización",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    vista,
                    "El código del curso debe ser un número válido.",
                    "Error de Formato",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(
                    vista,
                    "Por favor, seleccione un docente.",
                    "Error de Selección",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    vista,
                    "Ocurrió un error inesperado: " + e.getMessage(),
                    "Error Inesperado",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void eliminar() {
        try {
            if (vista.txtCodigo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        vista,
                        "Por favor, ingrese el código del curso a eliminar.",
                        "Campo Vacío",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            int cod = Integer.parseInt(vista.txtCodigo.getText());
            ModeloCurso c = new ModeloCurso();
            c.setCodCurso(cod);
            if (c.eliminar()) {
                JOptionPane.showMessageDialog(
                        vista,
                        "Curso eliminado exitosamente.",
                        "Eliminación Exitosa",
                        JOptionPane.INFORMATION_MESSAGE
                );
                cargarTabla();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(
                        vista,
                        "No se pudo eliminar el curso. Es posible que no exista o esté relacionado con otros datos.",
                        "Error de Eliminación",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    vista,
                    "El código del curso debe ser un número válido.",
                    "Error de Formato",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    vista,
                    "Ocurrió un error inesperado al intentar eliminar: " + e.getMessage(),
                    "Error Inesperado",
                    JOptionPane.ERROR_MESSAGE
            );
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
