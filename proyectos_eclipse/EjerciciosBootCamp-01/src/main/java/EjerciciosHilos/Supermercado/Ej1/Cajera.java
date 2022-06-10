package EjerciciosHilos.Supermercado.Ej1;

import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class Cajera implements Runnable {
	Cliente cliente;

	public Cajera(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public void run() {
		int proceso;
		System.out.println("Cliente nuevo");
		while (!cliente.getProductos().empty()) {
			try {
				TimeUnit.SECONDS.sleep(cliente.getProductos().pop());
				System.out.println("Caja>> BeeeEEeppp");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
