package dao;

import java.sql.SQLException;

import models.Empleado;

public class ProductoDAO extends AbstractDao {
	
	public ProductoDAO() {
		super.conectar();
	}
	
	/**
	 * M�todo que actualiza las unidades que quedan en el stock de un producto
	 * @param idProducto
	 * @param cantidadProducto
	 */
	public void restarCantidadProducto(int idProducto, int cantidadProducto) {
		
		int cantidadStock = cogerCantidadStock(idProducto);
		
		int resultado = cantidadStock - cantidadProducto;
		
		try {
			String query = "UPDATE producto set cantidad_stock = ? where ID_Producto = ?";
			st = conexion.prepareStatement(query);
			st.setInt(1, resultado);
			st.setInt(2, idProducto);
			st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * M�todo que te devuelve el stockage de un producto
	 * @param idProducto
	 * @return
	 */
	public int cogerCantidadStock(int idProducto) {
		
		int cantidadStock = 0;
		
		try {
			String query = "SELECT cantidad_stock from producto where ID_Producto = ?";
			st = conexion.prepareStatement(query);
			st.setInt(1, idProducto);
			rs = st.executeQuery();
			if (rs.next()) {
				cantidadStock = rs.getInt("cantidad_stock");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cantidadStock;
	}
	
	/**
	 * M�todo que devuelve el nombre de un producto seg�n su Id
	 * @param idProducto
	 * @return
	 */
	public String obtenerNombreProductoById(int idProducto) {
		
		String producto = "";
		
		try {
			String query = "SELECT nombre_producto from producto where ID_Producto = ?";
			st = conexion.prepareStatement(query);
			st.setInt(1, idProducto);
			rs = st.executeQuery();
			if (rs.next()) {
				producto = rs.getString("nombre_producto");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return producto;
		
	}
	
	/**
	 * M�todo que repone el stock de un producto
	 * @param idProducto
	 */
	public void reponerStock(int idProducto) {
		try {
			String query = "UPDATE producto set cantidad_stock = 20 where ID_Producto = ?";
			st = conexion.prepareStatement(query);
			st.setInt(1, idProducto);
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
