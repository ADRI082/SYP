package cajas;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import models.Empleado;
import utils.ConsoleHelper;

public class Caja2  {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		String Host = "localhost";
		int Puerto = 6000;// puerto remoto

		System.out.println("PROGRAMA CLIENTE 2 INICIADO....");
		Socket Cliente = new Socket(Host, Puerto);

		// CREO UN FLUJO DE SALIDA PARA ENVIAR OBJETOS CON EL CLIENTE
		ObjectOutputStream outObjeto = new ObjectOutputStream(Cliente.getOutputStream());

		// CREO UN FLUJO DE ENTRADA PARA RECIBIR OBJETOS CON EL CLIENTE
		ObjectInputStream inObjeto = new ObjectInputStream(Cliente.getInputStream());
		
		DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());
		
		DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());

		boolean correcto = false;
		
		boolean salida = false;
		
		ConsoleHelper consoleHelper =  new ConsoleHelper();

		Scanner sc = new Scanner(System.in);

		// A partir de aqui empiezo a trabajar

		do {

			consoleHelper.iniciarCaja();
			
			String login = sc.nextLine();
			
			flujoSalida.writeUTF(login);
			
			Empleado empleado = (Empleado)inObjeto.readObject();
			
			if(empleado != null) {
				consoleHelper.imprimirDatos(empleado);
				correcto = true;
			}else {
				System.out.println("Login incorrecto!");
			}
			
		} while (!correcto);
		
		while(!salida) {
			consoleHelper.menu();
			String accion = consoleHelper.accion(sc.nextLine());
			flujoSalida.writeUTF(accion);
			String mensaje = flujoEntrada.readUTF();
			salida = consoleHelper.procesarMensaje(mensaje);
		}

		System.out.println("Hasta pronto!");
		
		Cliente.close();
		flujoEntrada.close();
		flujoEntrada.close();
		outObjeto.close();
		inObjeto.close();

	}

}
