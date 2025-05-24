package modelo;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Conexion {
    
    private static Connection conexion;
   
    public static Connection getConexion() {
        if (conexion == null) {
            try {
                
                Properties props = new Properties();
                
                props.load(new FileInputStream("PropertiesMVC.properties"));
                
                String url = props.getProperty("db.url");
                String user = props.getProperty("db.user");
                String password = props.getProperty("db.password");
                        
                conexion = DriverManager.getConnection(url, user, password);
                System.out.println("Conexion exitosa a la base de datos.");
                
            } catch (SQLException | IOException e) {
                System.err.println("Error en la conexion: " + e);
                throw new RuntimeException("Fallo la conexion a la base de datos." + e);
            }
        }
        return conexion;
    }
    public static void closeConnection() {
        
        if (conexion != null){
            try{
                conexion.close();
                System.out.println("Conexion a la base de datos cerrada.");
              
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        
    }
}
