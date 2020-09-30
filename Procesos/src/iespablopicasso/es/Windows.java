package iespablopicasso.es;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Windows {

	private Process process;
	private ProcessBuilder pb;
	private Scanner sc;
	private StringBuilder bd;
	private BufferedReader reader;

	public Windows() {
		iniciar();
	}

	private void iniciar() {
		System.out.println("¿Que proceso quieres lanzar?" + "\n" + "1.Crear un directorio" + "\n" + "2.Crear fichero"
				+ "\n" + "3.Lista interfaces de red" + "\n" + "4.Mostrar ip del ordenador dada interfaz red" + "\n"
				+ "5.Mostrar la dirección MAC dado el nombre de la interfaz de red" + "\n"
				+ "6.Comprobar conectividad con internet" + "\n" + "7.Salir" + "\n");

		sc = new Scanner(System.in);
		String seleccion = sc.nextLine();
		seleccionar(seleccion);

	}

	public void seleccionar(String seleccion) {
		switch (seleccion) {

		case "1":
			crearDir();
			break;

		case "2":
			crearFile();
			break;

		case "3":
			listarInter();
			break;

		case "4":
			ipOrdenador();
			break;

		case "5":
			macOrdenador();
			break;

		case "6":
			comprobarConectividad();
			break;

		case "7":
			salir();
			break;

		}
	}

	private void crearDir() {

		System.out.println("porfavor escribe ruta'\'nombre" + "\n");
		sc = new Scanner(System.in);
		String dir = sc.nextLine();

		pb = new ProcessBuilder();

		pb.command("cmd.exe", "/c", "MD " + dir);

		try {
			process = pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 reiniciar();

	}

	private void crearFile() {

		System.out.println("porfavor escribe ruta\nombre" + "\n");
		sc = new Scanner(System.in);
		String dir = sc.nextLine();

		pb = new ProcessBuilder();

		pb.command("powershell", "ni -i file " + dir);
		try {
			process = pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 reiniciar();


	}

	private StringBuilder getIpConfig() {

		pb = new ProcessBuilder();
		pb.command("powershell", "ipconfig -all");

		try {
			bd = new StringBuilder();
			process = pb.start();

			reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line = "";

			while ((line = reader.readLine()) != null) {

				bd.append(line + "\n");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bd;
	}

	private void listarInter() {
		// TODO Auto-generated method stub

		System.out.println(getIpConfig());
		reiniciar();


	}

	private void ipOrdenador() {
		// TODO Auto-generated method stub

		System.out.println("Introduce nombre del adaptador" + "\n");
		sc = new Scanner(System.in);
		String adaptador = sc.nextLine();

		pb = new ProcessBuilder();
		pb.command("powershell", "Get-NetAdapter -Name " + adaptador + " | Get-NetIPAddress -AddressFamily IPv4");

		try {
			bd = new StringBuilder();
			process = pb.start();

			reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line = "";
			boolean encontrado = false;

			while ((line = reader.readLine()) != null && !encontrado) {

				if (line.contains("IPAddress")) {
					encontrado = true;
				}

				bd.append(line + "\n");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(bd.toString());
		 reiniciar();


	}

	private void macOrdenador() {
		// TODO Auto-generated method stub

		System.out.println("Introduce nombre del adaptador" + "\n");
		sc = new Scanner(System.in);
		String adaptador = sc.nextLine();

		pb = new ProcessBuilder();
		pb.command("powershell", "(Get-NetAdapter -Name " + adaptador + ").MacAddress");

		try {
			bd = new StringBuilder();
			process = pb.start();

			reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line = "";
			boolean encontrado = false;

			bd.append(line = reader.readLine());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Mac del adaptador : " + bd.toString());
		
		 reiniciar();


	}

	private void comprobarConectividad() {

		System.out.println("Introduce Ip para comprobar" + "\n");
		sc = new Scanner(System.in);
		String IP = sc.nextLine();

		pb = new ProcessBuilder();
		pb.command("powershell", "ping " + IP);

		try {
			bd = new StringBuilder();
			process = pb.start();

			reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line = "";
			int contador = -1;

			while ((line = reader.readLine()) != null && contador == -1) {

				contador = comprobarLinea(line);

				bd.append(line + "\n");
			}

			if (contador == -1) {
				System.out.println("Ip Valida!");
			} else {
				System.out.println("Ip no valida!");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 reiniciar();

	}

	private void salir() {
		// TODO Auto-generated method stub
		System.out.println("Hasta pronto!");
	}

	private int comprobarLinea(String line) {

		if (line.contains("agotado") || line.contains("intentarlo")) {
			return 1;
		}

		return -1;
	}
	
	private void reiniciar() {
		System.out.println("Deseas hacer otra cosa? y/n");
		sc = new Scanner(System.in);
		
		if(sc.nextLine().equals("y")) {
			iniciar();
		}else {
			salir();
		}
		
		
	}

}
