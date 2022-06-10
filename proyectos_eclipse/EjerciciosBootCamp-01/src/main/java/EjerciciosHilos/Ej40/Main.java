package EjerciciosHilos.Ej40;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Distribuidor distribuidor = new Distribuidor();
		Fabricante fabricante = new Fabricante(distribuidor);
		Comprador c1= new Comprador(distribuidor);
		Comprador c2= new Comprador(distribuidor);
		Comprador c3= new Comprador(distribuidor);
		Comprador c4= new Comprador(distribuidor);
		Comprador c5= new Comprador(distribuidor);

		fabricante.start();
		c1.start();

		while (fabricante.isAlive() || c1.isAlive()) {
			System.out.println(">>" + distribuidor.toString());

			if (!fabricante.isAlive() && !distribuidor.isStock()) {
				distribuidor.cerrarTienda();
			}
			Thread.sleep(1000);

		}

		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("Fin del programa");
		estado(c1);
		estado(fabricante);
	}

	protected static void estado(Thread thread) {
		System.err.println(">>> [" + thread.getName() + "]" + thread.getState());
	}

}
