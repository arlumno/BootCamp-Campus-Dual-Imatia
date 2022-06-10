package EjerciciosHilos.Supermercado.Ej2;

import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Cliente implements Runnable {
	private Stack<Integer> productos = new Stack<>();
	private Caja caja;

	public Cliente(Caja caja) {
		this.caja = caja;
		llenarCarrito();
	}

	private void llenarCarrito() {
		int nProductos = ThreadLocalRandom.current().nextInt(1, 11);
		for (int i = 0; i < nProductos; i++) {
			// tiempo de procesado
			productos.push(ThreadLocalRandom.current().nextInt(1, 4));
		}
	}

	@Override
	public void run() {
		while (!productos.isEmpty()) {
			caja.addProducto(productos.pop());
			System.out.println("[" + Thread.currentThread().getName() + "] añade producto a caja");
			try {
				TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(0, 3));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("[" + Thread.currentThread().getName() + "] ha terminado de poner productos en caja");
	}

}
