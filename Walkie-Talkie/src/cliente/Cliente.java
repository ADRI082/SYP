package cliente;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

import menu.ConsolaHelper;

public class Cliente {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		ConsolaHelper consolilla = new ConsolaHelper();
		
		Scanner sc = new Scanner(System.in);
		
		ArrayList<Object> datos = new ArrayList();
		
		Socket Cliente = null;
		
		while(true) {
			try {
				datos = consolilla.elegirModo(1);

				String Host = datos.get(0).toString();
				int Puerto = (int) datos.get(1);// puerto remoto
				
				Cliente = new Socket(Host, Puerto);
				
				break;
			}catch(Exception e) {
				System.out.println("Fallo de conexion");
			}
		}
		
		System.out.println("PROGRAMA CLIENTE INICIADO....");
		

		DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());

		DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());

		boolean contiene = false;

		boolean puedeEscribir = true;

		boolean puedeEscuchar = true;

		System.out.println("Empieza a escribir!");

		while (!contiene) {

			while (puedeEscribir) {

				String mensaje = sc.nextLine();

				flujoSalida.writeUTF(mensaje);

				if (mensaje.contains("c")) {
					puedeEscribir = false;
				}

				if (mensaje.contains("cc")) {
					contiene = true;
					puedeEscribir = false;
					puedeEscuchar = false;
					System.out.println("Desconectado");
				}

			}

			if (puedeEscuchar) {
				String mensajeServidor = flujoEntrada.readUTF();
				System.out.println(" Servidor: \n" + mensajeServidor);
				if (mensajeServidor.contains("c")) {
					puedeEscribir = true;
				}

				if (mensajeServidor.contains("cc")) {
					System.out.println("desconectado");
					contiene = true;
				}

			}

		}

		// CERRAR STREAMS Y SOCKETS
		flujoEntrada.close();
		flujoSalida.close();
		Cliente.close();

	}

}
