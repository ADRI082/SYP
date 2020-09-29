package ejemplos;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Ejersisio2 {

	public static void main(String args[]) {
		ProcessBuilder processBuilder = new ProcessBuilder();
		String ruta = leerRuta();

		// -- Linux / MacOS --

		// Run a shell command
		processBuilder.command("bash", "-c", "touch " + ruta);

		// Run a shell script
		// processBuilder.command("path/to/hello.sh");

		// -- Windows --

		// Run a command
		// processBuilder.command("cmd.exe", "/c", "dir C:\\Users\\mkyong");

		// Run a bat file
		// processBuilder.command("C:\\Users\\mkyong\\hello.bat");

		try {
			if (!checkRuta(ruta)) {
				Process process = processBuilder.start();

				if (process.waitFor() == 0) {
					System.out.println("Directorio creado");
				} else {
					System.out.println("Se lio chiquita...");
				}
			} else {
				System.out.println("El fichero existe");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private static String leerRuta() {
		// No cierren nunca el System.in si no quieren cargarse el flujo estandar
		// (teclado)
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce la ruta del fichero a crear: ");
		return sc.nextLine();
	}

	private static boolean checkRuta(String ruta) {
		return new File(ruta).exists();
	}

}