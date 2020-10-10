package iespablopicasso.es;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class General extends SO {

	public General() {
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
	 * Método que sirve para crear un directorio en UNIX dada una ruta correcta, si
	 * no es correcta, el usuario tiene que volver a introducirla y lo mismo pasa si
	 * el nombre del directorio ya existe
	 */

	private void crearDir() {

		String dir = super.comprobarRuta();

		pb = new ProcessBuilder();

		pb.command("bash", "-c", "mkdir " + dir);

		try {
			Process process = pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Directorio creado con exito!!!" + "\n");

		reiniciar();

	}

	/**
	 * Método que sirve para crear un fichero en UNIX dada una ruta correcta, si no
	 * es correcta, el usuario tiene que volver a introducirla y lo mismo pasa si el
	 * nombre del fichero ya existe
	 */

	private void crearFile() {

		String fichero = super.comprobarRuta();

		pb = new ProcessBuilder();

		pb.command("bash", "-c", "touch " + fichero);

		try {
			Process process = pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Fichero creado con exito!!!" + "\n");

		reiniciar();

	}

	/**
	 * Método que devuelve las interfaces de red del ordenador donde esté trabajando
	 * el usuario
	 * 
	 * @return
	 */

	private StringBuilder getIpConfig() {

		pb = new ProcessBuilder();
		pb.command("bash", "-c", "ifconfig " + "-a");
		bd = new StringBuilder();

		try {

			Process process = pb.start();

			reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			// Guardamos en un buffer la salida del proceso
			String line;
			while ((line = reader.readLine()) != null) {
				bd.append(line + "\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return bd;

	}

	private void listarInter() {
		// TODO Auto-generated method stub

		System.out.println(getIpConfig());
		reiniciar();

	}

	/**
	 * Método que devuelve la ip del adpatador de red, del ordenador con el que el
	 * usuario está trabajando
	 */
	private void ipOrdenador() {
		// TODO Auto-generated method stub
		System.out.println("Introduce nombre del adaptador" + "\n");
		sc = new Scanner(System.in);
		String adaptador = sc.nextLine();

		pb = new ProcessBuilder();

		pb.command("bash", "-c", "ifconfig " + adaptador + " | " + "grep \"inet \" " + "|" + " cut -d \" \" -f 2");

		try {

			Process process = pb.start();

			reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			// Guardamos en un buffer la salida del proceso
			String line = reader.readLine();

			if (line != null)
				bd.append(line + "\n");
			else
				bd.append("Esta interfaz de red no tiene dirección IP asociada");

			System.out.println(bd);

		} catch (IOException e) {
			e.printStackTrace();
		}

		// System.out.println(bd.toString().substring(bd.lastIndexOf(" "),
		// bd.length()));
		reiniciar();

	}

	/**
	 * Método que devuelve la ip del adpatador de red, del ordenador con el que el
	 * usuario está trabajando
	 */

	private void macOrdenador() {
		// TODO Auto-generated method stub

		System.out.println("Introduce nombre del adaptador" + "\n");
		sc = new Scanner(System.in);
		String adaptador = sc.nextLine();

		pb = new ProcessBuilder();

		pb.command("bash", "-c", "ifconfig " + adaptador + " | " + "grep \"ether \" " + "|" + " cut -d \" \" -f 2");

		try {

			Process process = pb.start();

			reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line = reader.readLine();

			if (line != "" || line != null)
				bd.append(line + "\n");
			else
				bd.append("Esta interfaz de red no tiene dirección MAC asociada");

		} catch (IOException e) {
			e.printStackTrace();
		}

		if (bd.toString().equalsIgnoreCase("null")) {
			System.out.println("No existe ese adaptador");
		} else {
			System.out.println(bd.toString());
		}

		reiniciar();

	}

	/**
	 * Método que devuelve si la ip o dominio que le has pasado existe o no
	 */

	private void comprobarConectividad() {

		System.out.println("Introduce Ip para comprobar" + "\n");
		sc = new Scanner(System.in);
		String IP = sc.nextLine();

		pb = new ProcessBuilder();
		pb.command("bash", "-c", "ping " + IP);

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
		if (ConsolaHelper.getRespuesta() != "n") {
			seleccionar(ConsolaHelper.getRespuesta());
		}
	}
}