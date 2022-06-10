package EjerciciosHilos.Ej40;

public class Comprador extends Thread {
	Distribuidor distribuidor;

	public Comprador(Distribuidor distribuidor) {
		this.distribuidor = distribuidor;
	}

	@Override
	public void run() {
		while(distribuidor.isTiendaAbierta()) {
			distribuidor.venderCoche();
		}
	}
}
