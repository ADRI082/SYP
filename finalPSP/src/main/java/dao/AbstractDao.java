package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDao {	

    private static final String URL = "jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private static final String USUARIO = "root";
    private static final String CLAVE = "1234";
	protected Connection conexion;
	protected ResultSet rs;
	protected PreparedStatement st;
	protected Statement stmt;

    
    public void conectar() {
		try {
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
        } catch (SQLException e) {
            System.out.println("Error en la conexión");
            e.printStackTrace();
        }

    }
}