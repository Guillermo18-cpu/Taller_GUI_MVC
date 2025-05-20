package controlador;

import modelo.Docente;
import vista.DocenteVista;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class DocenteControlador {
    private final DocenteVista vista;

    public DocenteControlador(DocenteVista vista) {
        this.vista = vista;
        initControladores();
        cargarTabla();
    }

    private void initControladores() {
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
        Docente d = new Docente(Integer.parseInt(vista.txtCodigo.getText()), vista.txtNombre.getText());
        if (d.insertar()) {
            cargarTabla();
            limpiar();
        }
    }

    private void actualizar() {
        Docente d = new Docente(Integer.parseInt(vista.txtCodigo.getText()), vista.txtNombre.getText());
        if (d.actualizar()) {
            cargarTabla();
            limpiar();
        }
    }

    private void eliminar() {
        Docente d = new Docente(Integer.parseInt(vista.txtCodigo.getText()), "");
        if (d.eliminar()) {
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
        List<Docente> lista = Docente.obtenerTodos();
        vista.modeloTabla.setRowCount(0);
        for (Docente d : lista) {
            vista.modeloTabla.addRow(new Object[]{d.getCodigo(), d.getNombre()});
        }
    }
}
