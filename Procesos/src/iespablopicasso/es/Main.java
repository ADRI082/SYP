package iespablopicasso.es;

public class Main {

	static Windows windows;
	static General general;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Con esto cazamos el sistema operativo en el que estamos trabajando

		seleccionarSO(System.getProperty("os.name"));

		// System.out.println(System.getProperty("os.name")); Asi puedo coger que
		// sistema es

	}

	private static void seleccionarSO(String sistema) {

		// Aqui elijo con que sistema estoy trabajando para poder realizar unas tareas u
		// otras

		switch (sistema) {

		case "Windows 10":
			windows = new Windows();
			break;

		default:
			general = new General();
		}
	}

}
