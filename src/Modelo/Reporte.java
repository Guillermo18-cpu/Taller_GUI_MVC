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
                    rs.getString("nom_estudiante"),
                    rs.getString("nom_curso"),
                    rs.getString("nom_docente"),
                    rs.getString("nota_curso")
                });
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener reporte: " + e.getMessage());
        }

        return lista;
    }
}
