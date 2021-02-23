package hilos;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import dao.ServerDAO;
import models.Empleado;
import utils.Email;

public class HiloServidor extends Thread {

	private Socket cliente;

	private ServerDAO servidorDao;

	private String[] trozos;

	public HiloServidor(Socket cliente) {
		this.cliente = cliente;
	}

	@Override
	public void run() {
		try {
			// CREO FLUJO DE ENTRADA DEL CLIENTE
			InputStream entrada = null;
			entrada = cliente.getInputStream();
			DataInputStream flujoEntrada = new DataInputStream(entrada);

			OutputStream salida = null;
			salida = cliente.getOutputStream();
			DataOutputStream flujoSalida = new DataOutputStream(salida);

			servidorDao = new ServerDAO();
			
			Email correo = new Email();

			boolean logueado = false;

			boolean cerrar = false;

			String accionEmpleado = "";

			int idProducto = 0;

			int cantidadProducto = 0;

			ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());

			ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());

			String idEmpleado = "";
			
			int stock = 0;

			// Aqui empieza el logueo

			while (!logueado) {

				try {

					String logueo = flujoEntrada.readUTF();

					Scanner sc = new Scanner(logueo);
					sc.useDelimiter(";");
					while (sc.hasNext()) {
						idEmpleado = sc.next();
					}

					Empleado empleado = servidorDao.findEmpleadoById(Integer.parseInt(idEmpleado));

					outObjeto.writeObject(empleado);

					if (empleado != null) {
						logueado = true;
					}
				} catch (NumberFormatException e) {
					outObjeto.writeObject(null);
				}

			}

			// Aqui termina el logueo

			// Aqui empiezan las acciones

			while (!cerrar) {

				String respuesta = "";

				String accion = flujoEntrada.readUTF();

				trozos = accion.split(";");
				accionEmpleado = trozos[0];

				switch (accionEmpleado) {
				case "Cobro":
					
					idProducto = Integer.parseInt(trozos[1]);
					cantidadProducto = Integer.parseInt(trozos[2]);
					stock = obtenerStockProducto(idProducto);
					
					if((idProducto >=1 && idProducto < 5) && (stock >= cantidadProducto && stock > 0)) {
					hacerCompra(idProducto, cantidadProducto, Integer.parseInt(idEmpleado));
					respuesta = "Compra Realizada con éxito";
					}else {
						if(stock == 0) {
							String producto = obtenerNombreProductoById(idProducto);
							respuesta = "No queda stock de este producto, hemos enviado un email para que repongan el stock";
							correo.sendEmail(producto);
							reponerStock(idProducto);
						}else {
							respuesta = "Has elegido un producto que no existe, o no hay suficiente stock (Quedan " + stock + " unidades del producto elegido), vuelve a introducir los datos correctamente";
						}
					}
					break;

				case "Caja":
					respuesta = "Hoy hemos recaudado " + obtenerCajaDia() + " €";
					break;

				case "Salir":
					respuesta = "Salir";
					cerrar = true;
					System.out.println(idEmpleado);
					actualizarUltimaSesion(Integer.valueOf(idEmpleado));
					break;

				}

				flujoSalida.writeUTF(respuesta);

			}

			cliente.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Método que permite que el cajero pueda cobrarle la compra al usuario
	 * @param idProducto
	 * @param cantidadProducto
	 * @param idEmpleado
	 */
	
	private void hacerCompra(int idProducto, int cantidadProducto, int idEmpleado) {
		servidorDao.hacerCompra(idProducto, cantidadProducto, idEmpleado);
	}
	
	
	/**
	 * Método que devuelve cuantos unidades quedan en el stock del producto seleccionado
	 * @param idProducto
	 * @return
	 */
	
	private int obtenerStockProducto(int idProducto) {
		return servidorDao.obtenerStockProducto(idProducto);
	}

	
	/**
	 * Método que nos devuelve cuanto dinero hemos recaudado a lo largo del día
	 * @return
	 */
	private String obtenerCajaDia() {
		return String.valueOf(servidorDao.obtenerCaja());
	}
	
	/**
	 * Método que nos devuelve el nombre del producto a través de su Id
	 * @param idProducto
	 * @return
	 */
	
	private String obtenerNombreProductoById(int idProducto) {
		return servidorDao.obtenerNombreProductoById(idProducto);
	}
	
	/**
	 * Método que repone el stock de un producto en 20 unidades
	 * @param idProducto
	 */
	
	private void reponerStock(int idProducto) {
		servidorDao.reponerStock(idProducto);
	}
	
	
	/**
	 * Método que actualiza la sesión del usuario 
	 * @param idEmpleado
	 */
	private void actualizarUltimaSesion(int idEmpleado) {
		servidorDao.actualizarUltimaSesion(idEmpleado);
	}
	
}