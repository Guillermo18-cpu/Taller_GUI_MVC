package modelo;

import java.sql.*;
import java.util.*;

public class ModeloCurso {

    private int codCurso;
    private String nomCurso;
    private int codDocente;
    
    public ModeloCurso() {}

    public ModeloCurso(int codCurso, String nomCurso, int codDocente) {
        this.codCurso = codCurso;
        this.nomCurso = nomCurso;
        this.codDocente = codDocente;
    }

    public int getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(int codCurso) {
        this.codCurso = codCurso;
    }

    public String getNomCurso() {
        return nomCurso;
    }

    public void setNomCurso(String nomCurso) {
        this.nomCurso = nomCurso;
    }

    public int getCodDocente() {
        return codDocente;
    }

    public void setCodDocente(int codDocente) {
        this.codDocente = codDocente;
    }

    @Override
    public String toString() {
        return nomCurso;
    }

    public boolean insertar() {
        String sql = "INSERT INTO cursos VALUES (?, ?, ?)";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql)) {
            ps.setInt(1, codCurso);
            ps.setString(2, nomCurso);
            ps.setInt(3, codDocente);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar curso: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar() {
        String sql = "UPDATE cursos SET nom_curso = ?, cod_docente = ? WHERE cod_curso = ?";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql)) {
            ps.setString(1, nomCurso);
            ps.setInt(2, codDocente);
            ps.setInt(3, codCurso);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar curso: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar() {
        String sql = "DELETE FROM cursos WHERE cod_curso = ?";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql)) {
            ps.setInt(1, codCurso);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar curso: " + e.getMessage());
            return false;
        }
    }

    public static List<ModeloCurso> obtenerTodos() {
        List<ModeloCurso> lista = new ArrayList<>();
        String sql = "SELECT * FROM cursos";
        try (Statement st = Conexion.getConexion().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new ModeloCurso(
                        rs.getInt("cod_curso"),
                        rs.getString("nom_curso"),
                        rs.getInt("cod_docente")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cursos: " + e.getMessage());
        }
        return lista;
    }
}
