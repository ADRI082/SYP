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

	// HE CREADO UN CONSOLAHELPER PARA QUE ME CONTROLE TODAS LAS ENTRADAS Y SALIDAS
	// DE TECLADO Y ORIENTAR MAS A OBJETOS EL PROGRAMA

	/**
	 * Metodo que inicia el men� de elecci�n y har� una cosa u otra dependiendo de
	 * lo que introduzcamos por teclado
	 */
	public static void iniciar() {
		System.out.println("�Que proceso quieres lanzar?" + "\n" + "1.Crear un directorio" + "\n" + "2.Crear fichero"
				+ "\n" + "3.Lista interfaces de red" + "\n" + "4.Mostrar ip del ordenador dada interfaz red" + "\n"
				+ "5.Mostrar la direcci�n MAC dado el nombre de la interfaz de red" + "\n"
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

	/**
	 * Metodo que nos comprueba si lo que ha introducido por teclado es algo
	 * coherente con el programa o no, si no lo es, tiene que volver a introducir lo
	 * que quiere que haga el programa
	 * 
	 * @param respuesta
	 */

	public static void comprobarRespuesta(String respuesta) {

		try {
			int numero = Integer.parseInt(respuesta);
			if (numero < 0 || numero > 7) {
				System.out.println("La funci�n seleccionada no existe!" + "\n");
				iniciar();
			}
		} catch (NumberFormatException e) {
			System.out.println("Por favor introduce algo acorde con lo que te aparece en el menu!!" + "\n");

			iniciar();
		}

	}

	/**
	 * M�todo que nos devuelve si la ruta que le hemos introducido existe en el
	 * equipo en el que estamos trabajando o no, si no existe, tiene que volver a
	 * introducir la ruta de nuevo. Adem�s, diferencia de si estamos en linux o en
	 * windows para cromprobar la ruta correctamente.
	 * 
	 * @return
	 */

	public static String comprobarRuta() {
		String dir = "";
		String nombre = "";
		String caracter = "";

		switch (System.getProperty("os.name")) {

		case "Windows 10":
			caracter = "\\";
			break;

		default:
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
		} while (existNombre(nombre));

		dir = ruta + caracter + nombre;

		return dir;
	}

	public static boolean checkRuta(String ruta) {
		return new File(ruta).exists();
	}

	/**
	 * M�todo que comprueba si el nombre del archivo, ya sea directorio o fichero
	 * existe, si es as�, tiene que cambiar el nonbre
	 * 
	 * @param nombreArchivo
	 * @return
	 */

	public static boolean existNombre(String nombreArchivo) {

		File[] contenido = new File(ruta).listFiles();
		boolean aux = false;

		for (File file : contenido) {
			if (file.getName().equalsIgnoreCase(nombreArchivo)) {
				aux = true;
				System.out.println("DANGER DANGER, el nombre del directorio/fichero ya existe!" + "\n");
			}
		}

		return aux;

	}

	/**
	 * M�todo que reinicia el programa una vez que ha terminado de hacer una tarea
	 * en concreto, seg�n si el usuario quiere seguir o no trabajando con el
	 * programa
	 */
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

	/**
	 * M�todo que sale del programa si el usuario no quiere seguir trabajando con �l
	 */
	public static void salir() {
		System.out.println("Hasta pronto!");
	}

}
