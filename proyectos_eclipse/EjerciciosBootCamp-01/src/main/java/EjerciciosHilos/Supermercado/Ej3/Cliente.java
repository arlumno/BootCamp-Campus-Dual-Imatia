package EjerciciosHilos.Supermercado.Ej3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Cliente implements Runnable {
	private Stack<Integer> productos = new Stack<>();
	private List<Caja> cajas = new ArrayList<>();
	private Caja caja = null;
	private int puestoCola;
	private String nombre;
	public Cliente(List<Caja> cajas, String nombre) {
		this.nombre = nombre;
		this.cajas = cajas;
		llenarCarrito();
	}

	private void llenarCarrito() {
		int nProductos = ThreadLocalRandom.current().nextInt(1, 11);
		for (int i = 0; i < nProductos; i++) {
			// tiempo de procesado
			productos.push(ThreadLocalRandom.current().nextInt(1, 4));
		}
	}

	private synchronized void elegirCaja() {
		List<Caja> cajasAbiertas = buscaCajasAbiertas();
		for (Caja cajaAbierta : cajasAbiertas) {
			if (!cajaAbierta.isCajaOcupada()) {
				caja = cajaAbierta;
			}
		}
		if (caja == null && cajasAbiertas.size() > 0) {
			caja = buscaCajasAbiertas().get(ThreadLocalRandom.current().nextInt(0, cajasAbiertas.size()));
		}
	}

	public synchronized List<Caja> buscaCajasAbiertas() {
		List<Caja> cajasAbiertas = new ArrayList<>();
		cajasAbiertas.addAll(cajas);
		cajasAbiertas.removeIf((it) -> !it.isCajaAbierta());
		return cajasAbiertas;
	}

	@Override
	public void run() {
		elegirCaja();
		if (caja != null) {
			puestoCola = caja.ponerseEnCola();
			while (!productos.isEmpty()) {
				try {
					caja.addProducto(productos.pop(), puestoCola);
					System.out.println("[" + nombre + "][Caja "+caja.getNumeroCaja()+"] añade producto a caja");
					TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(500, 3000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("[" + nombre + "][Caja "+caja.getNumeroCaja()+"]  ha terminado de poner productos en caja");
			caja.salirDeCaja();
			
		} else {
			System.out.println("["+nombre + "] Todas las cajas estan cerradas.");
		}
	}

}
