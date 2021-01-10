package seccionCritica;

import java.util.ArrayList;

public class bandeja {
	
	private int hamburguesas;
	
	private ArrayList<Integer> consumiciones;
	
	public bandeja() {
		this.hamburguesas = 0;
		consumiciones = new ArrayList<Integer>();
		iniciarConsumiciones();
	}
	
	
	/**
	 * Método que hace que un cliente consuma hamburguesas de la bandeja
	 * @param idCliente
	 */
	
	public synchronized void consumir(int idCliente) {
		
		try {
			while(getHamburguesas() == 0) {
				wait();
			}
			setHamburguesas();
			consumiciones.set(idCliente, consumiciones.get(idCliente)+1);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("El cliente " + idCliente + " ha comido " + consumiciones.get(idCliente) +
				" y quedan " + this.hamburguesas + " hamburguesas" );
	}
	
	/**
	 * Método que hace que el cocinero reponga hamburguesas en la bandeja 
	 */
	
	public synchronized void reponer() {
		cocinar();
		notify();
	}
	
	public void iniciarConsumiciones() {
		for (int i = 0; i < 80000; i++) {
			consumiciones.add(i,0);
		}
	}


	public int getHamburguesas() {
		return hamburguesas;
	}


	public void setHamburguesas() {
		this.hamburguesas = this.hamburguesas-1;
	}
	
	/**
	 * Aumenta las hamburguesas de la bandeja
	 */
	public void cocinar() {
		this.hamburguesas = this.hamburguesas+1;
	}

}
