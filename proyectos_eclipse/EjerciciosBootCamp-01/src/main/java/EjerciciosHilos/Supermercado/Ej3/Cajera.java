package EjerciciosHilos.Supermercado.Ej3;

import java.util.concurrent.TimeUnit;

public class Cajera extends Thread {
	Caja caja;
	String nombre;

	public Cajera(Caja caja, String nombre) {
		this.caja = caja;
		this.nombre = nombre;
	}

	@Override
	public void run() {
		System.out.println("[" + nombre + "] atiende en caja " + caja.getNumeroCaja());
		do {
			try {
				while (!caja.getProductos().isEmpty()) {
					System.out.println("[" + nombre + " (Caja " + caja.getNumeroCaja() + ")]  Manipula producto");
					TimeUnit.SECONDS.sleep(caja.getProductos().poll());
					System.out.println("Caja " + caja.getNumeroCaja() + " >>> BeeeEEeppp");
				}
				TimeUnit.SECONDS.sleep(4); // tiempo de espera en que la caja esta vacia.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} while (!caja.getProductos().isEmpty());
		caja.cerrar();

	}

	public String getNombre() {
		return nombre;
	}
}
