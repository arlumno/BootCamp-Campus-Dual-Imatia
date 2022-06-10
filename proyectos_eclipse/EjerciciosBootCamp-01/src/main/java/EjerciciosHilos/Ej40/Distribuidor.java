package EjerciciosHilos.Ej40;

public class Distribuidor {
	int cochesVendidos = 0;
	int cochesStock = 0;
	int cochesStockHistorial = 0;
	boolean tiendaAbierta = true;

//	public void ponerCocheALaVenta() {
	public synchronized void ponerCocheALaVenta() {
		cochesStock++;
		cochesStockHistorial++;
	}

//	public void venderCoche() {
	public synchronized void venderCoche() {
		if (isStock()) {
			cochesStock--;
			cochesVendidos++;
		}
	}

	public boolean isStock() {
		return cochesStock > 0;
	}

	public boolean isTiendaAbierta() {
		return tiendaAbierta;
	}

	public void cerrarTienda() {
		System.out.println("El fabricante no nos envía mas coches, tenemos que cerrar.");
		this.tiendaAbierta = false;
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
