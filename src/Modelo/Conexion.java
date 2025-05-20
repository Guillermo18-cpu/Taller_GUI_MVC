package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que maneja la conexión a la base de datos MySQL usando JDBC.
 */
public class Conexion {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/escuela";
    private static final String USER = "root";
    private static final String PASSWORD = "OH{c<6H1#cQ%F69$i"; 

    private static Connection conexion;

    public static Connection getConexion() {
        if (conexion == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión exitosa a la base de datos.");
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println("Error en la conexión: " + e.getMessage());
            }
        }
        return conexion;
    }
}
