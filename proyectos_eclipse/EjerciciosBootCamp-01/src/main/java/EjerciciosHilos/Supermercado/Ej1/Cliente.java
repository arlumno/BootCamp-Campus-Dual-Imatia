package EjerciciosHilos.Supermercado.Ej1;

import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class Cliente {
	Stack<Integer> productos = new Stack<>();
	
	public Cliente() {
		llenarCarrito();
	}
	
	private void llenarCarrito() {
		int nProductos = ThreadLocalRandom.current().nextInt(1, 21);
		for(int i = 0; i < nProductos; i++) {
			//tiempo de procesado
			productos.push(ThreadLocalRandom.current().nextInt(1, 4)); 
		}
	}

	public Stack<Integer> getProductos() {
		return productos;
	}

	public void setProductos(Stack<Integer> productos) {
		this.productos = productos;
	}
	
}
