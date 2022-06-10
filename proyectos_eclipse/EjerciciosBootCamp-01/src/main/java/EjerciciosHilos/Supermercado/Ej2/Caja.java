package EjerciciosHilos.Supermercado.Ej2;

import java.util.LinkedList;
import java.util.Queue;

public class Caja {
	private Queue<Integer> productos = new LinkedList<>();
	
	public synchronized void addProducto(Integer producto) {
		productos.add(producto);		
	}
	
	public synchronized Queue<Integer> getProductos() {
		return productos;
	}

}
