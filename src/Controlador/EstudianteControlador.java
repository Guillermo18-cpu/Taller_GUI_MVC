package controlador;

import modelo.Estudiante;
import vista.EstudianteVista;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Docente;

public class EstudianteControlador {

    private final EstudianteVista vista;

    public EstudianteControlador(EstudianteVista vista) {
        this.vista = vista;
        initControladores();
        cargarTabla();
    }

    private void initControladores() {
        vista.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tabla.getSelectedRow();
                if (fila != -1) {
                    String codigo = vista.tabla.getValueAt(fila, 0).toString();
                    String nombre = vista.tabla.getValueAt(fila, 1).toString();

                    vista.txtCodigo.setText(codigo);
                    vista.txtNombre.setText(nombre);
                }
            }
        });

        vista.btnAgregar.addActionListener(e -> insertar());
        vista.btnActualizar.addActionListener(e -> actualizar());
        vista.btnEliminar.addActionListener(e -> eliminar());
        vista.btnLimpiar.addActionListener(e -> limpiar());

        vista.tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tabla.getSelectedRow();
                vista.txtCodigo.setText(vista.modeloTabla.getValueAt(fila, 0).toString());
                vista.txtNombre.setText(vista.modeloTabla.getValueAt(fila, 1).toString());
            }
        });
    }

    private void insertar() {
        Estudiante e = new Estudiante(
                Integer.parseInt(vista.txtCodigo.getText()),
                vista.txtNombre.getText()
        );
        if (e.insertar()) {
            cargarTabla();
            limpiar();
        }
    }

    private void actualizar() {
        try {
            if (vista.txtCodigo.getText().trim().isEmpty()
                    || vista.txtNombre.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        vista,
                        "Por favor, complete el código y el nombre del estudiante.",
                        "Campos Incompletos",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            int codigo = Integer.parseInt(vista.txtCodigo.getText());
            String nombre = vista.txtNombre.getText();

            Estudiante e = new Estudiante(codigo, nombre);
            if (e.actualizar()) {
                JOptionPane.showMessageDialog(
                        vista,
                        "Estudiante actualizado exitosamente.",
                        "Actualización Exitosa",
                        JOptionPane.INFORMATION_MESSAGE
                );
                cargarTabla();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(
                        vista,
                        "No se pudo actualizar el estudiante.",
                        "Error de Actualización",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    vista,
                    "El código del estudiante debe ser un número válido.",
                    "Error de Formato",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    vista,
                    "Ocurrió un error inesperado al actualizar: " + ex.getMessage(),
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
                        "Por favor, ingrese el código del estudiante a eliminar.",
                        "Campo Vacío",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            int cod = Integer.parseInt(vista.txtCodigo.getText());
            Estudiante e = new Estudiante(cod, null);
            if (e.eliminar()) {
                JOptionPane.showMessageDialog(
                        vista,
                        "Estudiante eliminado exitosamente.",
                        "Eliminación Exitosa",
                        JOptionPane.INFORMATION_MESSAGE
                );
                cargarTabla();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(
                        vista,
                        "No se pudo eliminar el estudiante. Es posible que no exista o esté asociado a otros registros.",
                        "Error de Eliminación",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    vista,
                    "El código del estudiante debe ser un número válido.",
                    "Error de Formato",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    vista,
                    "Ocurrió un error inesperado al intentar eliminar: " + ex.getMessage(),
                    "Error Inesperado",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void limpiar() {
        vista.txtCodigo.setText("");
        vista.txtNombre.setText("");
        vista.tabla.clearSelection();
    }

    private void cargarTabla() {
        List<Estudiante> lista = Estudiante.obtenerTodos();
        vista.modeloTabla.setRowCount(0);
        for (Estudiante e : lista) {
            vista.modeloTabla.addRow(new Object[]{e.getCodigo(), e.getNombre()});
        }
    }
}
