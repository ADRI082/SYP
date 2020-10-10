package iespablopicasso.es;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Windows extends SO {

	public Windows() {
		super();
		seleccionar(ConsolaHelper.respuesta);
		
		
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
	
	/**
	 * Metodo que crea un directorio en la ruta especificada y en con el nombre que le hayas proporcionado.
	 * Si la ruta no existe, tienes que volver a reptirla hasta que sea correcta.
	 */

	private void crearDir() {

		String dir = super.comprobarRuta();

		pb = new ProcessBuilder();

		pb.command("cmd.exe", "/c", "MD " + dir);

		try {
			process = pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Directorio creado con exito!!!"+ "\n");

		reiniciar();

	}

	private void crearFile() {

		String dir = super.comprobarRuta();

		pb = new ProcessBuilder();

		pb.command("powershell", "ni -i file " + dir);
		try {
			process = pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Fichero creado con exito!!!" + "\n");

		reiniciar();

	}

	private StringBuilder getIpConfig() {

		pb = new ProcessBuilder();
		pb.command("powershell", "ipconfig -all");

		try {
			bd = new StringBuilder();
			process = pb.start();

			reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "Cp850"));

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
		String line = "";
		try {
			bd = new StringBuilder();
			process = pb.start();

			reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			
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
		
		if(line == null) {
			System.out.println("No existe ese adaptador, por favor, introduce un adaptador de red valido" + "\n");
			ipOrdenador();
		}else {
			System.out.println(bd.toString().substring(bd.lastIndexOf(" ") + 1, bd.length()));
			reiniciar();
		}

		

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

			bd.append(line = reader.readLine());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		if(bd.toString().equalsIgnoreCase("null")) {
			System.out.println("No existe ese adaptador");
		}else {
			System.out.println(bd.toString());
		}
		
			
		
		

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

	public void reiniciar() {
		super.reiniciar();
		if(ConsolaHelper.getRespuesta()!="n") {
			seleccionar(ConsolaHelper.getRespuesta());
		}
		
	}


}
