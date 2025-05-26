package modelo;

import java.sql.*;
import java.util.*;

public class ModeloMatricula {

    private int codEstudiante;
    private int codCurso;
    private double notaCurso;

    public ModeloMatricula() {
    }

    public ModeloMatricula(int codEstudiante, int codCurso, double notaCurso) {
        this.codEstudiante = codEstudiante;
        this.codCurso = codCurso;
        this.notaCurso = notaCurso;
    }

    public int getCodEstudiante() {
        return codEstudiante;
    }

    public int getCodCurso() {
        return codCurso;
    }

    public double getNotaCurso() {
        return notaCurso;
    }

    public boolean insertar() {
        String sql = "INSERT INTO matricula VALUES (?, ?, ?)";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql)) {
            ps.setInt(1, codEstudiante);
            ps.setInt(2, codCurso);
            ps.setDouble(3, notaCurso);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar matrícula: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar() {
        String sql = "UPDATE matricula SET nota_curso = ? WHERE cod_estudiante = ? AND cod_curso = ?";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql)) {
            ps.setDouble(1, notaCurso);
            ps.setInt(2, codEstudiante);
            ps.setInt(3, codCurso);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar nota: " + e.getMessage());
            return false;
        }
    }

    public static List<ModeloMatricula> obtenerTodas() {
        List<ModeloMatricula> lista = new ArrayList<>();
        String sql = "SELECT * FROM matricula";
        try (Statement st = Conexion.getConexion().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new ModeloMatricula(
                        rs.getInt("cod_estudiante"),
                        rs.getInt("cod_curso"),
                        rs.getDouble("nota_curso")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener matrículas: " + e.getMessage());
        }
        return lista;
    }

    public static String nombreEstudiante(int codEstudiante) {
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(
                "SELECT nom_estudiante FROM estudiantes WHERE cod_estudiante = ?")) {
            ps.setInt(1, codEstudiante);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo nombre estudiante: " + e.getMessage());
        }
        return "";
    }

    public static String nombreCurso(int codCurso) {
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(
                "SELECT nom_curso FROM cursos WHERE cod_curso = ?")) {
            ps.setInt(1, codCurso);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo nombre curso: " + e.getMessage());
        }
        return "";
    }
}
