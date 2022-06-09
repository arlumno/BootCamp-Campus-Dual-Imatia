package EjerciciosHilos.Ej40;

public class Distribuidor {
	int cochesVendidos = 0;
	int cochesStock = 0;
	int cochesStockHistorial = 0;

	public void ponerCocheALaVenta() {
		cochesStock++;
		cochesStockHistorial++;
	}

	public void venderCoche() {
		if (cochesStock > 0) {
			cochesStock--;
			cochesVendidos++;
		}
	}

	@Override
	public String toString() {
		return "Distribuidor [cochesVendidos=" + cochesVendidos + ", cochesStock=" + cochesStock
				+ ", cochesStockHistorial=" + cochesStockHistorial + "]";
	}


}
