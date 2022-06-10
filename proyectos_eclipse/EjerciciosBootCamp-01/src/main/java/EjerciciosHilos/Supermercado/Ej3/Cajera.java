package EjerciciosHilos.Supermercado.Ej3;

import java.util.concurrent.TimeUnit;

public class Cajera implements Runnable {
	Caja caja;
	String nombre;
	public Cajera(Caja caja, String nombre) {
		this.caja = caja;
		this.nombre = nombre;
	}

	@Override
	public void run() {
		int proceso;

		System.out.println("Pasando productos...");
		while (!caja.getProductos().isEmpty()) {
			while (!caja.getProductos().isEmpty()) {
				try {
					System.out.println("[Cajera - "+nombre+"] Coje producto");
					TimeUnit.SECONDS.sleep(caja.getProductos().poll());
					System.out.println("Caja " + caja.getNumeroCaja()+ " >>> BeeeEEeppp");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				TimeUnit.SECONDS.sleep(2); // tiempo de espera en que la caja esta vacia.
				caja.cerrar();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		System.out.println("xxxxxxx Caja cerrada xxxxxxxx");

	}

}
