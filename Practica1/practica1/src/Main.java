

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static final Scanner SC = new Scanner(System.in);
	private static String sistemOp;
	private static byte comando;
	private static ProcessBuilder processBuilder;
	
	public static void main(String[] args) {
		
		processBuilder = new ProcessBuilder();
		
		
		detectarSO();
			
		
		do {
			detectarComando();
			
		} while (comando == 0);


	}
	
	/**
	 * Detecta el sistema operativo y setea el estado de sistemOp para posteriores comandos
	 */
	private static void detectarSO() {
		
		
		String SO = System.getProperty("os.name");
		
		if      (SO.contains("Windows")) sistemOp = "w";
		else   sistemOp = "x";
	}
	
	/**
	 * Muestra el menu de opciones disponibles y capta el comando
	 * escodigo llamando a la funcion correspondiente
	 */
	private static void detectarComando() {
		
		comando = 1;
		
		System.out.println("Selecciona el número de lo que quieres hacer");
		System.out.println("[1] - Crear una carpeta");
		System.out.println("[2] - Crear un fichero");
		System.out.println("[3] - Listar todas las interfaces de red del ordenador");
		System.out.println("[4] - Mostrar la IP del ordenador dado el nombre de la interfaz de red");
		System.out.println("[5] - Mostrar la dirección MAC dado el nombre de la interfaz de red");
		System.out.println("[6] - Comprobar conectividad con internet");
		System.out.println("[7] - Salir");
		
		String entrada = SC.nextLine();
		
		
		switch (entrada) {
		
			case "1":
				crearCarpeta();
				break;
			case "2":
				crearFichero();
				break;
			case "3":
				listarInterfazRed();
				break;
			case "4":
				mostrarIP();
				break;
			case "5":
				mostrarMAC();
				break;
			case "6":
				comprobarConec();
				break;
			case "7":
				salir();
				break;
			default:
				System.out.println("Comando erróneo, por favor elige una de las opciones entre los corchetes (sólo un número entero)");
				comando = 0;
		}
		
	}
	
	/**
	 * Dada una ruta y un nombre, genera una carpeta en el directorio seleccionado por el usuario
	 */
	private static void crearCarpeta() {
		
		String ruta;
		String carpeta;
		File tester;
		
		do {
			System.out.println("Introduce la ruta de la carpeta a crear");
			ruta = SC.nextLine();
			tester = new File(ruta);
			
			System.out.println("Introduce el nombre de la carpeta");
			carpeta = SC.nextLine();
			
			
			
			if(!carpeta.equals("") && !ruta.equals("") && tester.exists()) {
				
				switch (sistemOp) {
				
				case "w":
					processBuilder.command("cmd.exe", "/c", "MD " + ruta);
					break;
				default:
					processBuilder.command("bash", "-c", "mkdir " + ruta + "/" + carpeta);
					
				}
				
					
				try {
					Process process = processBuilder.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Carpeta creada correctamente, ¿quieres hacer algo más? Y/N");
				finalizar();
				
			} else {
				System.out.println("Por favor, introduce una ruta o nombre de carpeta válidos \n");
			}
			
		} while (carpeta.equals("") || ruta.equals("") || carpeta.contains("/") || carpeta.contains("\\") || !tester.exists());
		
		
	}
	
	/**
	 * Dada una ruta y un nombre, genera un fichero en el directorio seleccionado por el usuario
	 */
	private static void crearFichero() {
		
		String ruta;
		String fichero;
		
		System.out.println("Introduce la ruta del archivo a crear");
		ruta = SC.nextLine();
		File tester = new File(ruta);
		
		System.out.println("Introduce el nombre del fichero");
		fichero = SC.nextLine();
		
		do {
			if(!ruta.equals("") && !fichero.equals("") && tester.exists()) {
				
				switch (sistemOp) {
				
				case "w":
					processBuilder.command("powershell", "ni -i file " + ruta + "/" + fichero);
					break;
				default:
					processBuilder.command("bash", "-c", "touch " + ruta + "/" + fichero);
					
				}
			
				
				try {
					Process process = processBuilder.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Fichero creado correctamente, ¿quieres hacer algo más? Y/N");
				finalizar();
			} 
			
			else {
				System.out.println("Por favor, introduce una ruta o nombre de archivo válidos \n");
			}
				
			
		} while (fichero.equals("") || ruta.equals("") || fichero.contains("/") || fichero.contains("\\") || !tester.exists());
			
	
		
	}
	
	
	/**
	 * Lista todas las interfaces de red y las muestra en pantalla
	 */
	private static void listarInterfazRed() {
		
		switch (sistemOp) {
		
		case "w":
			processBuilder.command("powershell", "ipconfig -all");

			break;
		default:
			processBuilder.command("bash", "-c", "ifconfig " + "-a");
			
		}
		
		try {

			Process process = processBuilder.start();

			StringBuilder buffer = new StringBuilder();

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream(),"Cp850"));

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

		System.out.println("¿Quieres hacer algo más? Y/N");
		finalizar();
		
	}
	
	/**
	 * Dada una interfaz de red, muestra en pantalla su direccion IP
	 */
	private static void mostrarIP() {
		
		String interfaz;
		
		System.out.println("Introduce la interfaz de red a buscar");
		interfaz = SC.nextLine();
		
		
		switch (sistemOp) {
		
		case "w":
			processBuilder.command("powershell", "Get-NetAdapter -Name " + interfaz + " | Get-NetIPAddress -AddressFamily IPv4"); // REVISAR

			break;
		default:
			processBuilder.command("bash", "-c", "ifconfig " + interfaz + " | " + "grep \"inet \" " + "|" + " cut -d \" \" -f 2");
			
		}
		
		try {

			Process process = processBuilder.start();

			StringBuilder buffer = new StringBuilder();

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));
			
			
			if (sistemOp.equals("w")) {
				
				String line = "";
			   boolean encontrado = false;

			   while ((line = reader.readLine()) != null && !encontrado) {

			    if (line.contains("IPAddress")) {
			     encontrado = true;
			    }

			    buffer.append(line + "\n");
			   }
			   
			   System.out.println(buffer.toString().substring(buffer.lastIndexOf(" "), buffer.length()));
			   
			} else {
				
				String line = reader.readLine();
				
				if (line != null) buffer.append(line + "\n");
				
				else buffer.append("Esta interfaz de red no tiene dirección IP asociada");
				
				System.out.println(buffer);
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("¿Quieres hacer algo más? Y/N");
		finalizar();
		
	}
	
	/**
	 * Dada una interfaz de red, muestra en pantalla su direccion MAC
	 */
	private static void mostrarMAC() {
		
		String interfaz;
		
		System.out.println("Introduce la interfaz de red a buscar");
		interfaz = SC.nextLine();
		
		
		switch (sistemOp) {
		
		case "w":
			processBuilder.command("powershell", "(Get-NetAdapter -Name " + interfaz + ").MacAddress");

			break;
		default:
			processBuilder.command("bash", "-c", "ifconfig " + interfaz + " | " + "grep \"ether \" " + "|" + " cut -d \" \" -f 2");
			
		}
		
		try {

			Process process = processBuilder.start();

			StringBuilder buffer = new StringBuilder();

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));

			String line = reader.readLine();
			
			if (line != null) {
				
				buffer.append(line + "\n");
			}
			
			else {
				
				buffer.append("Esta interfaz de red no tiene dirección MAC asociada");
			}
			

			System.out.println(buffer);
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("¿Quieres hacer algo más? Y/N");
		finalizar();
		
	}

	/**
	 * Dada una direccion IP seleccionada por el usuario comprueba la conexion
	 * si no se han perdido paquetes
	 */
	private static void comprobarConec() {
		
		
		System.out.println("Introduce una IP para comprobar conexión: ");
		String IP = SC.nextLine();
		
		switch (sistemOp) {
		
		case "w":
			processBuilder.command("powershell", "ping " + IP);
			break;
		default:
			processBuilder.command("bash", "-c", "ping -c 1 " + IP);
			
		}
		
		
		
		processBuilder.command();

		try {

			Process process = processBuilder.start();

			StringBuilder buffer = new StringBuilder();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			ArrayList<String> listaLineas = new ArrayList<String>();

			// Guardamos en un buffer la salida del proceso
			String line;
			while ((line = reader.readLine()) != null) {
				
				if(line.contains("%")) {
					
				
					if(line.contains("0.0")) 
						
						buffer.append("Conexión realizada con éxito");
					else {
							
						buffer.append("Ha ocurrido un problema con la conexión a la IP " + IP);
					}
				}
				
			}

			if (process.waitFor() == 0) {
				
				
				System.out.println(buffer);
				

			} else {
				System.out.println("Error, no existe la direccion " + IP);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("¿Quieres hacer algo más? Y/N");
		finalizar();
		
	}
	
	
	private static void salir() {
	
		System.out.println("Saliendo...");
		System.exit(0);
		
	}

	/**
	 * Pregunta al usuario para continuar con la aplicacion utlizando otro
	 * comando o para salir de la app
	 */
	private static void finalizar() {
		
		String opcion = "";
		do {
			opcion = SC.nextLine().toLowerCase();
			
			if      (opcion.equals("y")) detectarComando();
			
			else if (opcion.equals("n")) salir();
			
			else {
				
				System.out.println("Comando erróneo. Por favor, vuele a intentarlo");
				System.out.println("¿Quieres hacer algo más? Y/N");
			}
		} while (!opcion.equals("y") && !opcion.equals("n"));
		
		
	}
	
	

}
