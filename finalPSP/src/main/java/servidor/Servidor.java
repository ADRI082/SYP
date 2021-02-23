package servidor;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import hilos.HiloServidor;

public class Servidor {

	public static void main(String[] args) throws IOException {
		
		 ServerSocket servidor;
         servidor = new ServerSocket(6000);   
         System.out.println("Servidor iniciado...");
         while (true) {
               Socket cliente = new Socket();
               cliente=servidor.accept();//esperando cliente
               HiloServidor hilo = new HiloServidor(cliente);
               hilo.start(); //Se atiende al cliente
         }// Fin de while

	}

}