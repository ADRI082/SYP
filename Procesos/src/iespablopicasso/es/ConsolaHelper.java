package iespablopicasso.es;

import java.io.File;
import java.util.Scanner;

public class ConsolaHelper {

	static Scanner sc;
	static String respuesta;
	static String ruta = "";

	public ConsolaHelper() {
		iniciar();
	}

	public static void iniciar() {
		System.out.println("¿Que proceso quieres lanzar?" + "\n" + "1.Crear un directorio" + "\n" + "2.Crear fichero"
				+ "\n" + "3.Lista interfaces de red" + "\n" + "4.Mostrar ip del ordenador dada interfaz red" + "\n"
				+ "5.Mostrar la dirección MAC dado el nombre de la interfaz de red" + "\n"
				+ "6.Comprobar conectividad con internet" + "\n" + "7.Salir" + "\n");

		sc = new Scanner(System.in);
		respuesta = sc.nextLine();
		comprobarRespuesta(respuesta);

	}

	public static String getRespuesta() {
		return respuesta;
	}

	public static void setRespuesta(String respuesta) {
		ConsolaHelper.respuesta = respuesta;
	}

	public static void comprobarRespuesta(String respuesta) {

		try {
			int numero = Integer.parseInt(respuesta);
			if (numero < 0 || numero > 7) {
				System.out.println("La función seleccionada no existe!" + "\n");
				iniciar();
			}
		} catch (NumberFormatException e) {
			System.out.println("Por favor introduce algo acorde con lo que te aparece en el menu!!" + "\n");

			iniciar();
		}

	}

	public static String comprobarRuta() {
		String dir = "";
		String nombre = "";
		String caracter = "";
		
		switch(System.getProperty("os.name")) {
		
		case "Windows 10" :
			caracter = "\\";
		break;
			
		default : 
			caracter = "/";
		
		}
		
		do {
			System.out.println("porfavor escribe una ruta correcta" + "\n");
			sc = new Scanner(System.in);
			ruta = sc.nextLine();
		} while (!checkRuta(ruta));

		do {
		System.out.println("ahora escribe un nombre de directorio\\fichero que no exista ya" + "\n");
		sc = new Scanner(System.in);
		nombre = sc.nextLine();
		}while(existNombre(nombre));

		
		dir = ruta + caracter + nombre;

		return dir;
	}

	public static boolean checkRuta(String ruta) {
		return new File(ruta).exists();
	}
	
	public static boolean existNombre(String nombreArchivo) {
		
		File [] contenido = new File(ruta).listFiles();
		boolean aux = false;
		
		for (File file : contenido) {
			 if(file.getName().equalsIgnoreCase(nombreArchivo)) {
				 aux = true;
				 System.out.println("DANGER DANGER, el nombre del directorio/fichero ya existe!" + "\n");
			 }
		}
		
		
		
		return aux;
		
	}
	

	public static void reiniciar() {
		System.out.println("Deseas hacer otra cosa? y/n");
		sc = new Scanner(System.in);

		if (sc.nextLine().equals("y")) {
			iniciar();
		} else {
			salir();
			setRespuesta("n");
		}

	}
	
	

	public static void salir() {
		System.out.println("Hasta pronto!");
	}

}
