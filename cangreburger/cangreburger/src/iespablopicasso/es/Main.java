package iespablopicasso.es;

import seccionCritica.bandeja;
import threads.Clientes;
import threads.Cocinero;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		bandeja bandeja = new bandeja();
		
		Clientes [] clientes = new Clientes[10000];
		
		Cocinero [] cocineros = new Cocinero[10000];
		
		for (int i = 0; i < clientes.length; i++) {
			clientes[i] = new Clientes(i,bandeja);
		}
		
		for (int i = 0; i < cocineros.length; i++) {
			cocineros[i] = new Cocinero(bandeja);
		}
		
		for (Cocinero cocinero : cocineros) {
			cocinero.start();
		}
		
		for (Clientes cliente : clientes) {
			cliente.start();
		}
		

	}

}
