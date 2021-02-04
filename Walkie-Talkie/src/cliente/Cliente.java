package cliente;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		String Host = "localhost";
		int Puerto = 6000;// puerto remoto

		System.out.println("PROGRAMA CLIENTE INICIADO....");
		Socket Cliente = new Socket(Host, Puerto);

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

				if (mensaje.contains("cambio")) {
					puedeEscribir = false;
				}

				if (mensaje.contains("cambio y corto")) {
					contiene = true;
					puedeEscribir = false;
					puedeEscuchar = false;
					System.out.println("Desconectado");
				}

			}

			if (puedeEscuchar) {
				String mensajeServidor = flujoEntrada.readUTF();
				System.out.println(" Servidor: \n" + mensajeServidor);
				if (mensajeServidor.contains("cambio")) {
					puedeEscribir = true;
				}

				if (mensajeServidor.contains("cambio y corto")) {
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
