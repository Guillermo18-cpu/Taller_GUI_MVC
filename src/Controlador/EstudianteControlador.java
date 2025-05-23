
package controlador;

import modelo.Estudiante;
import vista.EstudianteVista;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;


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
        Estudiante e = new Estudiante(
                Integer.parseInt(vista.txtCodigo.getText()),
                vista.txtNombre.getText()
        );
        if (e.actualizar()) {
            cargarTabla();
            limpiar();
        }
    }

    private void eliminar() {
        int cod = Integer.parseInt(vista.txtCodigo.getText());
        Estudiante e = new Estudiante(cod, null);
        if (e.eliminar()) {
            cargarTabla();
            limpiar();
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
