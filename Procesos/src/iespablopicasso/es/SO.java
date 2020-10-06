package iespablopicasso.es;

import java.io.BufferedReader;
import java.io.File;
import java.util.Scanner;

public abstract class SO {

	protected Process process;
	protected ProcessBuilder pb;
	protected Scanner sc;
	protected StringBuilder bd;
	protected BufferedReader reader;

	static ConsolaHelper miConsola;

	public SO() {
		miConsola = new ConsolaHelper();
	}

	private void iniciar() {		
		miConsola.iniciar();

	}

	public void reiniciar() {

		miConsola.reiniciar();
	}

	private void salir() {
		miConsola.salir();
	}

	private void comprobarRespuesta() {
		
		miConsola.comprobarRespuesta(miConsola.respuesta);

	}

	public String comprobarRuta() {
		return miConsola.comprobarRuta();
	}

	private static boolean checkRuta() {
		return miConsola.checkRuta(miConsola.ruta);
	}

}
