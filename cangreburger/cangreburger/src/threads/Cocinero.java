package threads;

import java.util.Random;

import seccionCritica.bandeja;

public class Cocinero extends Thread {

	private bandeja bandeja;

	public Cocinero(bandeja bandeja) {
		this.bandeja = bandeja;
	}

	public void run() {

		while (true) {
			
			this.bandeja.reponer();

			try {
				Random random = new Random();
				Thread.sleep(random.nextInt(2000) + 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	
}
