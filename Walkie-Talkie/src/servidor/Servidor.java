package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import menu.ConsolaHelper;


public class Servidor {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);

		ConsolaHelper consolilla = new ConsolaHelper();
		
		ArrayList<Object> datos = new ArrayList();
		
		datos = consolilla.elegirModo(2);

		//Creamos un servidor al cual le establecemos un socket para que cualquier usuario se pueda conectar al servidor
		ServerSocket servidor = new ServerSocket((int)datos.get(0)) ;

		
		System.out.println("Servidor iniciado...");

		Socket cliente = new Socket();
		cliente = servidor.accept(); //Creamos otro socket para el cliente que se conecta al servidor


		boolean contiene = false;

		//Creamos aqui el flujo de entrada para que se puedan leer los mensajes recibidos
		InputStream entrada = null;
		entrada = cliente.getInputStream();
		DataInputStream flujoEntrada = new DataInputStream(entrada);

		//Creamos aqui el flujo de salida para enviar mensajes
		OutputStream salida = null;
		salida = cliente.getOutputStream();
		DataOutputStream flujoSalida = new DataOutputStream(salida);

		boolean puedeEscribir = false;

		boolean puedeEscuchar = true;

		while (!contiene) { //Mientras el mensaje que envía o recibe no contenga el mensaje cambio y corto, el bucle sigue adelante

			while (puedeEscribir) {//Podrá escribir mientras el mensaje que escriba no contenga ni "cambio" ni "cambio y corto"
				
				String mensaje = sc.nextLine();

				flujoSalida.writeUTF(mensaje);

				if (mensaje.contains("c")) { //Si el mensaje contiene cambio, la persona no puede escribir
					puedeEscribir = false;
				}

				if (mensaje.contains("cc")) { //Si el mensaje contiene cambio y corto no puede ni leer ni escuchar
					contiene = true;
					puedeEscribir = false;
					puedeEscuchar = false;
					System.out.println("Desconectado");
				}

			}

			if (puedeEscuchar) { //Si el mensaje que recibe es cambio, la persona ya puede volver a hablar o a escribir en este caso
				String mensajeCliente = flujoEntrada.readUTF();
				System.out.println(" Cliente: \n" + mensajeCliente);
				if (mensajeCliente.contains("c")) {
					puedeEscribir = true;
				}

				if (mensajeCliente.contains("cc")) { //Si el mensaje que recibe es cambio y corto, ya no puede ni hablar ni escribir.
					//El programa se desconecta
					System.out.println("desconectado");
					contiene = true;
				}

			}

		}

		// CERRAR STREAMS Y SOCKETS
		entrada.close();
		flujoEntrada.close();
		salida.close();
		flujoSalida.close();
		cliente.close();

	}

}
