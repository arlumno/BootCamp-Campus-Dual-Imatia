package EjerciciosHilos.Ej40;

public class Main {

	public static void main(String[] args) {
		Distribuidor distribuidor = new Distribuidor();
		Comprador comprador = new Comprador(distribuidor);
		Fabricante fabricante = new Fabricante(distribuidor);
		
		fabricante.start();
		comprador.start();

		while(fabricante.isAlive() || comprador.isAlive()) {
			System.out.println(">>" + distribuidor.toString());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("Fin del programa");
		estado(comprador);
		estado(fabricante);
	}

	protected static void estado(Thread thread) {
		System.err.println(">>> [" + thread.getName() + "]" + thread.getState() );
	}


}
