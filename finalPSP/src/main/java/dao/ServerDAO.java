package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import models.Empleado;

public class ServerDAO extends AbstractDao {

	CompraDAO compraDao = new CompraDAO();
	ProductoDAO productoDao = new ProductoDAO();

	public ServerDAO() {
		super.conectar();
	}
	
	/**
	 * Méotodo que devuelve un objeto empleado según su Id
	 * @param id
	 * @return
	 */

	public Empleado findEmpleadoById(int id) {

		Empleado empleado = null;

		try {
			String query = "SELECT * from empleado where ID_Empleado = ?";
			st = conexion.prepareStatement(query);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				empleado = new Empleado(rs.getInt("ID_Empleado"), rs.getDate("Ultima_Sesion"),
						rs.getDate("Fecha_Contratacion"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return empleado; // Esto devuelve el id del usuario y ya con esto podemos formar a los usuarios
	}
	
	
	/**
	 * Método que actualiza la sesión del empleado según el Id del empleado
	 * @param idEmpleado
	 */
	public void actualizarUltimaSesion(int idEmpleado) {
		Date ultimasesion = Date.valueOf(java.time.LocalDate.now());

		try {
			String query = "UPDATE empleado set ultima_sesion = ? where ID_Empleado = ?";
			st = conexion.prepareStatement(query);
			st.setDate(1, ultimasesion);
			st.setInt(2, idEmpleado);
			st.executeUpdate();
		} catch (SQLException e) {
			
		}
		
	}
	
	/**
	 * Método que permite cobrar la compra que ha hecho el empleado
	 * @param idProducto
	 * @param cantidadProducto
	 * @param idEmpleado
	 */

	public void hacerCompra(int idProducto, int cantidadProducto, int idEmpleado) {
		int idCompra = compraDao.insertarCompra(java.time.LocalDate.now());
		insertarPersonaCompra(idEmpleado, idCompra);
		insertarProductoCompra(idProducto, idCompra, cantidadProducto, java.time.LocalDate.now());
		productoDao.restarCantidadProducto(idProducto, cantidadProducto);
	}
	
	/**
	 * Método que te devuelve el total de beneficios que se ha producido en un día
	 * @return
	 */

	public int obtenerCaja() {
		int caja = 0;

		try {
			String query = "select SUM(t.unidades_vendidas * (p.precio_venta - p.precio_proveedor)) as 'suma' from tiene t left join producto p on t.producto_ID_Producto=p.ID_Producto left join compra c on t.compra_ID_Compra = c.ID_Compra WHERE c.fecha = curdate()";
			st = conexion.prepareStatement(query);
			rs = st.executeQuery();
			while (rs.next()) {
				caja = rs.getInt("suma");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return caja;
	}

	
	/**
	 * Método que te devulve el nombre del producto según su Id
	 * @param idProducto
	 * @return
	 */
	public String obtenerNombreProductoById(int idProducto) {
		return productoDao.obtenerNombreProductoById(idProducto);
	}
	
	
	/**
	 * Método que repone el stock de un producto concreto
	 * @param idProducto
	 */
	public void reponerStock(int idProducto) {
		productoDao.reponerStock(idProducto);
	}

	/**
	 * Método que inserta los datos de la compra y de quien ha hecho la compra
	 * @param idEmpleado
	 * @param idCompra
	 */
	private void insertarPersonaCompra(int idEmpleado, int idCompra) {

		try {

			String query = "INSERT INTO hace (compra_ID_Compra,Empleado_ID_Empleado) values (?,?) ";
			st = conexion.prepareStatement(query);
			st.setInt(1, idCompra);
			st.setInt(2, idEmpleado);
			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Método que inserta que producto/s había en la compra e inserta el id de la compra en la base de datos
	 * @param idProducto
	 * @param idCompra
	 * @param cantidadProducto
	 * @param date
	 */

	private void insertarProductoCompra(int idProducto, int idCompra, int cantidadProducto, LocalDate date) {

		try {

			String query = "INSERT INTO tiene (producto_ID_Producto,compra_ID_Compra,unidades_vendidas) values (?,?,?) ";
			st = conexion.prepareStatement(query);
			st.setInt(1, idProducto);
			st.setInt(2, idCompra);
			st.setInt(3, cantidadProducto);
			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Método que te devuelve el stockage de un producto
	 * @param idProducto
	 * @return
	 */
	public int obtenerStockProducto(int idProducto) {
		return productoDao.cogerCantidadStock(idProducto);
	}

}
