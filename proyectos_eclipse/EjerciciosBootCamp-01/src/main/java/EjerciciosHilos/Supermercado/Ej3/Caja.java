package EjerciciosHilos.Supermercado.Ej3;

import java.util.LinkedList;
import java.util.Queue;

public class Caja {
	private Queue<Integer> productos = new LinkedList<>();
	private int numeroCaja;
	private boolean cajaAbierta;
	private int colaCaja;
	private int clientesAtendidos;

	public Caja(int numeroCaja) {
		this.numeroCaja = numeroCaja;
		cajaAbierta = true;
		colaCaja = 0;
		clientesAtendidos = 0;
		System.out.println("******* Caja " + numeroCaja + " ABIERTA *******");
	}

	public synchronized void cerrar() {
		cajaAbierta = false;
		System.out.println("******* Caja " + numeroCaja + " CERRADA *******");
	}

	public synchronized int getNumeroCaja() {
		return numeroCaja;
	}

	public synchronized void addProducto(Integer producto, int puestoCola) throws InterruptedException {
		boolean continuar = true;
		while (continuar) {
			if ((puestoCola-clientesAtendidos) == 1) {
				productos.add(producto);
				continuar = false;
			} else {
				wait();
			}
		}
	}

	public synchronized Queue<Integer> getProductos() {
		return productos;
	}

	public synchronized boolean isCajaAbierta() {
		return cajaAbierta;
	}

	public synchronized boolean isCajaOcupada() {
		return colaCaja > 0;
	}

	public synchronized int ponerseEnCola() {
		colaCaja++;
		return colaCaja;
	}

	public synchronized int getColaCaja() {
		return colaCaja;
	}

	public synchronized void salirDeCaja() {
		clientesAtendidos++;
		notifyAll();
	}

}
