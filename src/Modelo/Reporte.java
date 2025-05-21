package modelo;

import java.sql.*;
import java.util.*;

public class Reporte {

    public static List<String[]> obtenerReporteNotas() {
        List<String[]> lista = new ArrayList<>();
        String sql = "SELECT * FROM vista_notas_detalle_simple";

        try (Statement st = Conexion.getConexion().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new String[]{
                    rs.getString("nombre_estudiante"),
                    rs.getString("nombre_curso"),
                    rs.getString("nombre_docente_curso"),
                    rs.getString("nota_curso")
                });

            }
        } catch (SQLException e) {
            System.err.println("Error al obtener reporte: " + e.getMessage());
        }

        return lista;
    }
}
