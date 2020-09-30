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

	}

	private void ipOrdenador() {
		// TODO Auto-generated method stub
		
	
		}
		
		
		

	}

	private void macOrdenador() {
		// TODO Auto-generated method stub

	}

	private void comprobarConectividad() {
		// TODO Auto-generated method stub

	}

	private void salir() {
		// TODO Auto-generated method stub

	}

}
