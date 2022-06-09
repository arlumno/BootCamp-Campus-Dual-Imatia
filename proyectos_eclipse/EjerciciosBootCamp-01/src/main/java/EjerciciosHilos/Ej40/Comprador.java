package EjerciciosHilos.Ej40;

public class Comprador extends Thread{
	Distribuidor distribuidor;

	public Comprador(Distribuidor distribuidor) {
		super();
		this.distribuidor = distribuidor;
	}
	@Override
	public void run() {
		for (int i = 0; i < 200; i++) {
			try {
				distribuidor.venderCoche();
				sleep(600);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
