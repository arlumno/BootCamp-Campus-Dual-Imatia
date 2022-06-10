package EjerciciosHilos.Supermercado.Ej3;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Caja caja = new Caja(1);
		
		Thread c1 = new Thread( new Cliente(caja));
		c1.setName("Paco");
		Thread c2 = new Thread( new Cliente(caja));
		c2.setName("Lucia");
		Thread c3 = new Thread( new Cliente(caja));
		c3.setName("Silvia");
		
		
		Thread cajero = new Thread(new Cajera(caja,"Pedro"));

		Long inicio = System.currentTimeMillis();
		c1.start();
		c2.start();
		c3.start();
		cajera.start();
		
		cajera.join();
		System.out.println("Tiempo de trabajo: " + (System.currentTimeMillis() - inicio) + " ms");

	}
}
