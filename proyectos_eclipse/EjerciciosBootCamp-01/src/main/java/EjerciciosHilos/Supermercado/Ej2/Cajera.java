package EjerciciosHilos.Supermercado.Ej2;

import java.util.concurrent.TimeUnit;

public class Cajera implements Runnable {
	Caja caja;

	public Cajera(Caja caja) {
		this.caja = caja;
	}

	@Override
	public void run() {
		int proceso;

		System.out.println("Pasando productos...");
		while (!caja.getProductos().isEmpty()) {
			while (!caja.getProductos().isEmpty()) {
				try {
					System.out.println("[Cajera] Coje producto");
					TimeUnit.SECONDS.sleep(caja.getProductos().poll());
					System.out.println("Caja>> BeeeEEeppp");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				TimeUnit.SECONDS.sleep(5); // tiempo de espera en que la caja esta vacia.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		System.out.println("xxxxxxx Caja cerrada xxxxxxxx");

	}

}
