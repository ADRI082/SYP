package utils;

import java.util.Scanner;

import models.Empleado;

public class ConsoleHelper {
	
	
	private Scanner entrada = new Scanner(System.in);
	
	
	public ConsoleHelper() {
		
	}

	/**
	 * M�todo que inicia la caja para el cliente
	 */
	public void iniciarCaja() {
		System.out.println("Por favor, ingresa el comando de Login ");
	}
	
	/**
	 * M�todo que imprime los datos del empleado logueado
	 * @param empleado
	 */
	
	public void imprimirDatos(Empleado empleado) {
		System.out.println("Tus datos son el siguiente : ");
		System.out.println("Id : " + empleado.getIdEmpleado());
		System.out.println("Ultima Sesion : " + empleado.getUltimaSesion());
		System.out.println("Fecha Contratacion : " + empleado.getFechaContratacion() + "\n");
	}
	
	/**
	 * M�todo que despliega un men� precargado para el empleado
	 */
	
	public void menu() {
		System.out.println("�Que operacion deseas realizar?");
		System.out.println("1. Cobrar compra");
		System.out.println("2. Obtener la caja del d�a");
		System.out.println("3. Salir");
	}
	
	/**
	 * M�todo que te devuelve la acci�n que el usuario quiere realizar seg�n la entrada de teclado que haya recibido
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
			System.out.println("Lo siento, eso no existe en este men�, te habr�s equivocado de aplicaci�n");
		
		}
		
		return palabra;
	}
	
	/**
	 * M�todo que devuelve una respuesta seg�n la acci�n que haya recibido el servidor
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
	 * M�todo privado que nos carga un men� de objetos y nos permite elegir un producto y el n�mero de unidades que queremos por el producto
	 * @return
	 */
	private String cobrarCompra() {
		
		String resultado = "";
		
		System.out.println("ART�CULOS DE LOS BUENOS : ");
		System.out.println("1. Disco Duro");
		System.out.println("2. USB");
		System.out.println("3. Monitor");
		System.out.println("4. Rat�n");
		
		System.out.println("Seleccione el articulo que desea");
		String articulo = entrada.nextLine();
		System.out.println("� Cu�ntas unidades ?");
		String unidades = entrada.nextLine();
		
		resultado = articulo+";"+unidades;
		
		return resultado;
	}
	
	

}
