package menu;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsolaHelper {
	
	public ConsolaHelper() {
		
	}
	
	
	public ArrayList<Object> elegirModo(int modo) {
		
		ArrayList<Object> resultadoSinValor = new ArrayList(); 
		
		if(modo == 1) {
			ArrayList<Object> resultado = new ArrayList();
			return resultado = consolaCliente();
		}else {
			ArrayList<Object> resultado = new ArrayList();
			return resultado = consolaServer();
		}
		
	}
	
	private ArrayList<Object> consolaCliente(){
		
		ArrayList<Object> resultados = new ArrayList();
		
		Scanner sc = new Scanner(System.in);
		boolean correcto = false;
		
		String Host = "";
		int Puerto = 0;// puerto remoto
		
		try {
			
		do {
			
			System.out.println("Por favor, introduce el host al que quieres conectarte");
			Host = sc.nextLine();
			
			System.out.println("Ahora introduce el puerto al que quieres conectarte ");
			Puerto = sc.nextInt();
			
			
			System.out.println("Estas seguro de has introducido los datos correctamente ? En el caso que no, reinicia el programa,"
					+ "Será más sencillo para todos. y/n para responder ");
			System.out.println("Host -> " + Host);
			System.out.println("Puerto -> " + Puerto);
			System.out.println("Es correcto? y/n");
			String decision = sc.next();
			
			if(decision.equalsIgnoreCase("y")) {
				correcto = true;
				resultados.add(Host);
				resultados.add(Puerto);
			}
			
		}while(!correcto);
		
		}catch(Exception e) {
			System.out.println("Has introducido mal los datos, por favor,vuelve a introducirlo todo correctamente");
			resultados = consolaCliente();
		}
		
		return resultados;
	}
	
	private ArrayList<Object> consolaServer(){
		
		ArrayList<Object> resultado = new ArrayList();
		
		Scanner sc = new Scanner(System.in);
		boolean correcto =  false;
		int puerto = 0;
		
		try {
			
		do {
			System.out.println("Tu eres más privilegiado, lo único que tienes que hacer es poner el puerto correcto, tu amigo te "
					+ "está esperando!");
			puerto = sc.nextInt();
			
			System.out.println("El dato que has introducido ha sido el siguiente : ");
			System.out.println("Puerto -> " + puerto);
			System.out.println("Es correcto? y/n");
			String decision = sc.next();
			
			
			if(decision.equalsIgnoreCase("y")) {
				correcto = true;
				resultado.add(puerto);
			}
				
			
		}while(!correcto);
		
		}catch(InputMismatchException e) {
			System.out.println("No se admiten letras en el puerto, por favor, vuelve a introducirlo");
			resultado = consolaServer();
		}
		
		return resultado;
	}

}
