package threads;

import java.util.Random;

import seccionCritica.bandeja;

public class Clientes extends Thread {

	private bandeja bandeja;
	private int idCliente;
	
	/**
	 * Creas un clientes con un id y la misma bandeja donde los cocineros reponen las hamburguesas
	 * @param idCliente
	 * @param bandeja
	 */

	public Clientes(int idCliente,bandeja bandeja) {
		this.bandeja = bandeja;
		this.idCliente = idCliente;
	}
	
	/**
	 * Método que hace que el cliente consuma hamburguesas y no se muera de hambre
	 */

	public void run() {

		while (true) {
			this.bandeja.consumir(idCliente);

			try {
				Random random = new Random();
				Thread.sleep(random.nextInt(4000) + 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
