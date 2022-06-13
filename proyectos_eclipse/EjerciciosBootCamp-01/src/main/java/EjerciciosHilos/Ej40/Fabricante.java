package EjerciciosHilos.Ej40;

public class Fabricante extends Thread {
	Distribuidor distribuidor;

	public Fabricante(Distribuidor distribuidor) {
		this.distribuidor = distribuidor;
	}

	@Override
	public void run() {
		for (int i = 0; i < 8; i++) {
			try {
				distribuidor.ponerCocheALaVenta();				
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		distribuidor.cerrarTienda();
	}
}
