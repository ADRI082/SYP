package ejemplos;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Ejercicio3 {
	
	static Process process;

	public static void main(String args[]) throws InterruptedException {
		ProcessBuilder processBuilder = new ProcessBuilder();
		String IP = leerIP();
		

		// -- Linux / MacOS --

		// Run a shell command
		//processBuilder.command("bash", "-c", "ping -c 1 " + IP);

		// Run a shell script
		// processBuilder.command("path/to/hello.sh");

		// -- Windows --

		// Run a command
		processBuilder.command("cmd.exe", "/c", "ping " + IP);

		// Run a bat file
		// processBuilder.command("C:\\Users\\mkyong\\hello.bat");

		try {

			 process = processBuilder.start();
			

			StringBuilder buffer = new StringBuilder();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			// Guardamos en un buffer la salida del proceso
			String line;
			int contador = -1;
			while ((line = reader.readLine()) != null && contador == -1) {
				
				
				contador = comprobarLinea(line);
				
				buffer.append(line + "\n");
			}

			if(contador == -1) {
					System.out.println(process.exitValue());
					System.out.println("Ip Valida!");
			}else {
				System.out.println(process.exitValue());
				System.out.println("Ip no valida!");
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String leerIP() {
		// No cierren nunca el System.in si no quieren cargarse el flujo estandar
		// (teclado)
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce la IP a comprobar: ");
		return sc.nextLine();
	}
	
	private static int comprobarLinea(String line) {
		
		if(line.contains("agotado") || line.contains("vuelva a intentarlo.")) {
			return 1;
		}
		
		return -1;
	}
	

}