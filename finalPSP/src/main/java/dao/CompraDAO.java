package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class CompraDAO extends AbstractDao{
	
	public CompraDAO() {
		super.conectar();
	}
	
	/**
	 * Método que inserta la fecha de cuando se hizo la compra en la base de datos
	 * @param fechaCompra
	 * @return
	 */
	public int insertarCompra(LocalDate fechaCompra) {
		
		int idCompra = 0;
		
		try {
			
			Date fecha = Date.valueOf(fechaCompra);
			
			String query = "INSERT INTO compra (fecha) values (?) ";
			st = conexion.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			st.setDate(1, fecha);
			st.executeUpdate();
			rs = st.getGeneratedKeys();
			
			while(rs.next()) {
				idCompra = rs.getInt(1);
			}
			
			System.out.println(idCompra);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return idCompra;
	}
	

}
