package utils;

import java.util.Scanner;

import models.Empleado;

public class ConsoleHelper {
	
	
	private Scanner entrada = new Scanner(System.in);
	
	
	public ConsoleHelper() {
		
	}

	/**
	 * Método que inicia la caja para el cliente
	 */
	public void iniciarCaja() {
		System.out.println("Por favor, ingresa el comando de Login ");
	}
	
	/**
	 * Método que imprime los datos del empleado logueado
	 * @param empleado
	 */
	
	public void imprimirDatos(Empleado empleado) {
		System.out.println("Tus datos son el siguiente : ");
		System.out.println("Id : " + empleado.getIdEmpleado());
		System.out.println("Ultima Sesion : " + empleado.getUltimaSesion());
		System.out.println("Fecha Contratacion : " + empleado.getFechaContratacion() + "\n");
	}
	
	/**
	 * Método que despliega un menú precargado para el empleado
	 */
	
	public void menu() {
		System.out.println("¿Que operacion deseas realizar?");
		System.out.println("1. Cobrar compra");
		System.out.println("2. Obtener la caja del día");
		System.out.println("3. Salir");
	}
	
	/**
	 * Método que te devuelve la acción que el usuario quiere realizar según la entrada de teclado que haya recibido
	 * @param accion
	 * @return
	 */
	
	public String accion(String accion) {
		
		String palabra = "";
		
		switch(accion) {
		case "1" : 
			palabra = "Cobro" +";"+ cobrarCompra();
		break;
		
		case "2" : 
			palabra = "Caja";
			break;
		case "3" :
			palabra = "Salir";
			break;
		default :
			System.out.println("Lo siento, eso no existe en este menú, te habrás equivocado de aplicación");
		
		}
		
		return palabra;
	}
	
	/**
	 * Método que devuelve una respuesta según la acción que haya recibido el servidor
	 * @param mensaje
	 * @return
	 */
	
	public boolean procesarMensaje(String mensaje) {
		
		boolean respuesta = false;
		
		switch(mensaje) {
		
		case "Salir" :
			respuesta=true;
		break;
		
		default:
			System.out.println(mensaje);
		break;
		}
		
		return respuesta;
		
	}
	
	
	/**
	 * Método privado que nos carga un menú de objetos y nos permite elegir un producto y el número de unidades que queremos por el producto
	 * @return
	 */
	private String cobrarCompra() {
		
		String resultado = "";
		
		System.out.println("ARTÍCULOS DE LOS BUENOS : ");
		System.out.println("1. Disco Duro");
		System.out.println("2. USB");
		System.out.println("3. Monitor");
		System.out.println("4. Ratón");
		
		System.out.println("Seleccione el articulo que desea");
		String articulo = entrada.nextLine();
		System.out.println("¿ Cuántas unidades ?");
		String unidades = entrada.nextLine();
		
		resultado = articulo+";"+unidades;
		
		return resultado;
	}
	
	

}
