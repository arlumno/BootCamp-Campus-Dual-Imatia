package EjerciciosHilos.Supermercado.Ej3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Cliente implements Runnable {
	private Stack<Integer> productos = new Stack<>();
	private List<Caja> cajas = new ArrayList<>();
	
	private Caja caja;

	public Cliente(List<Caja> cajas) {
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

	@Override
	public void run() {
		caja = buscaCajaLibre();
		if(caja != null) {			
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
		}else {
			System.out.println(Thread.currentThread().getName() + " No puede pasar productos, todas las cajas estan cerradas.");
		}
	}

	private synchronized Caja buscaCajaLibre() {
		List<Caja> cajasAbiertas = buscaCajasAbiertas();
		Caja eleccion = null;
		for(Caja caja: cajasAbiertas) {
			if(!caja.isCajaOcupada()) {
				caja.setCajaOcupada(true);
				eleccion = caja;
			}
		}
		if(eleccion == null && cajasAbiertas.size() > 0) {
			caja = buscaCajasAbiertas().get(ThreadLocalRandom.current().nextInt(0, cajasAbiertas.size()));
		}
		return eleccion;
	}

	public synchronized List<Caja> buscaCajasAbiertas() {
		List<Caja> cajasAbiertas = new ArrayList<>();
		cajasAbiertas.addAll(cajas);
		cajasAbiertas.removeIf((it)-> !it.isCajaAbierta());		
		return cajasAbiertas;
	}
}
