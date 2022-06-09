package EjerciciosHilos.Ej40;

public class Fabricante extends Thread {
	Distribuidor distribuidor;

	public Fabricante(Distribuidor distribuidor) {
		this.distribuidor = distribuidor;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
//				distribuidor.setCochesStock(distribuidor.getCochesStock()+1);
				distribuidor.ponerCocheALaVenta();
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
