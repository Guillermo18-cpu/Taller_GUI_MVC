package controlador;

import modelo.Reporte;
import vista.ReporteVista;

import java.util.List;

public class ReporteControlador {

    private final ReporteVista vista;

    public ReporteControlador(ReporteVista vista) {
        this.vista = vista;
        cargarTabla();
    }

    private void cargarTabla() {
        List<String[]> datos = Reporte.obtenerReporteNotas();
        vista.modeloTabla.setRowCount(0);
        for (String[] fila : datos) {
            vista.modeloTabla.addRow(fila);
        }
    }
}
