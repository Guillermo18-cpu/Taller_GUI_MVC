package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Estudiante extends Persona {

    public Estudiante() {}

    public Estudiante(int codigo, String nombre) {
        super(codigo, nombre);
    }

    @Override
    public String getTipo() {
        return "Estudiante";
    }

    public boolean insertar() {
        String sql = "INSERT INTO estudiantes (cod_estudiante, nom_estudiante) VALUES (?, ?)";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql)) {
            ps.setInt(1, getCodigo());
            ps.setString(2, getNombre());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar estudiante: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar() {
        String sql = "UPDATE estudiantes SET nom_estudiante = ? WHERE cod_estudiante = ?";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql)) {
            ps.setString(1, getNombre());
            ps.setInt(2, getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar estudiante: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar() {
        String sql = "DELETE FROM estudiantes WHERE cod_estudiante = ?";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql)) {
            ps.setInt(1, getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar estudiante: " + e.getMessage());
            return false;
        }
    }

    public static List<Estudiante> obtenerTodos() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes";
        try (Statement st = Conexion.getConexion().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Estudiante(rs.getInt("cod_estudiante"), rs.getString("nom_estudiante")));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener estudiantes: " + e.getMessage());
        }
        return lista;
    }
}
