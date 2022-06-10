package EjerciciosHilos.Supermercado.Ej3;

import java.util.LinkedList;
import java.util.Queue;

public class Caja {
	private Queue<Integer> productos = new LinkedList<>();
	private int numeroCaja;
	private boolean cajaAbierta;
	private boolean cajaOcupada;

	public Caja(int numeroCaja) {
		this.numeroCaja = numeroCaja;
		cajaAbierta = true;
		cajaOcupada = false;
		System.out.println("******* Caja " + numeroCaja + " ABIERTA *******");
	}

	public synchronized void cerrar() {
		cajaAbierta = false;
		System.out.println("******* Caja " + numeroCaja + " CERRADA *******");
	}

	public synchronized int getNumeroCaja() {
		return numeroCaja;
	}

	public synchronized void addProducto(Integer producto) {
		productos.add(producto);
	}

	public synchronized Queue<Integer> getProductos() {
		return productos;
	}

	public boolean isCajaAbierta() {
		return cajaAbierta;
	}

	public boolean isCajaOcupada() {
		return cajaOcupada;
	}

	public void setCajaOcupada(boolean cajaOcupada) {
		this.cajaOcupada = cajaOcupada;
	}
	
	
}
