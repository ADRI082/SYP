package ejemplos;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Ejersisio1 {

	public static void main(String args[]) {
		
		ProcessBuilder processBuilder = new ProcessBuilder();
		String ruta = "/Users";
		
		//Validamos que la ruta exista.
		do {
			ruta = leerRuta();
			System.out.println();
		}while(!checkRuta(ruta));

		// -- Linux / MacOS --
		
		// Run a shell command
		processBuilder.command("bash", "-c", "ls -lsof " + ruta);

		// Run a shell script
		//processBuilder.command("path/to/hello.sh");

		// -- Windows --

		// Run a command
		//processBuilder.command("cmd.exe", "/c", "dir C:\\Users\\mkyong");

		// Run a bat file
		//processBuilder.command("C:\\Users\\mkyong\\hello.bat");
		
		
		try {

			Process process = processBuilder.start();

			StringBuilder buffer = new StringBuilder();

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));

			//Guardamos en un buffer la salida del proceso
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line + "\n");
			}

			if (process.waitFor() == 0) {
				System.out.println(buffer);
			} else {
				System.out.println("Se lio chiquita...");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private static String leerRuta() {	
		//No cierren nunca el System.in si no quieren cargarse el flujo estandar (teclado)
		Scanner sc = new Scanner(System.in);		
		System.out.println("Introduce la ruta a listar: ");		
		return sc.nextLine();
	}
	
	private static boolean checkRuta(String ruta) {
		 return new File(ruta).exists();
	}
	
}