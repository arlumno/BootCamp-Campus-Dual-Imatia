package EjerciciosHilos.Ej40;

public class Distribuidor {
	int cochesVendidos = 0;
	int cochesStock = 0;
	int cochesStockHistorial = 0;
	boolean tiendaAbierta = true;

	public synchronized void ponerCocheALaVenta() {
		cochesStock++;
		cochesStockHistorial++;
		notifyAll();
	}

	public synchronized void venderCoche() throws InterruptedException {
		boolean continuar = true;
		while (continuar) {
			if (isStock()) {
				cochesStock--;
				cochesVendidos++;
				continuar = false;
//				notifyAll();
			} else if (isTiendaAbierta()) {
				wait();
			} else {
				continuar = false;
			}
		}
	}

	public synchronized boolean isStock() {
		return cochesStock > 0;
	}

	public synchronized boolean isTiendaAbierta() {
		return tiendaAbierta;
	}

	public synchronized void cerrarTienda() {
		System.out.println("El fabricante no nos envía mas coches, tenemos que cerrar tras vender todos.");
		this.tiendaAbierta = false;
		notifyAll();
	}

	@Override
	public String toString() {
		return "Distribuidor [cochesVendidos=" + cochesVendidos + ", cochesStock=" + getCochesStock()
				+ ", cochesStockHistorial=" + cochesStockHistorial + ", tiendaAbierta=" + tiendaAbierta + "]";
	}

	public int getCochesStock() {
		return cochesStock;
	}

}
